package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgstudy.constants.SystemConstants;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Article;
import com.kgstudy.domain.entity.Category;
import com.kgstudy.domain.vo.ArticleDetailVo;
import com.kgstudy.domain.vo.ArticleListVo;
import com.kgstudy.domain.vo.HotArticleVo;
import com.kgstudy.domain.vo.PageVo;
import com.kgstudy.mapper.ArticleMapper;
import com.kgstudy.service.ArticleService;
import com.kgstudy.service.CategoryService;
import com.kgstudy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author 柯刚
* @description 针对表【kg_article(文章表)】的数据库操作Service实现
* @createDate 2022-12-05 22:48:24
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条文章
        Page<Article> page = new Page<>(1,10);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();
        // bean拷贝
//        ArrayList<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article, vo);
//            articleVos.add(vo);
//        }
//        return ResponseResult.okResult(articleVos);
        // 改进：把bean拷贝的方法封装到工具类里，方便复用
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有 categoryId 就要查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 查询文章的访问量（暂未实现）

        // 对 isTop 进行降序排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        // 查询 categoryName
        List<Article> articles = page.getRecords();
        // articleId 去查询 articleName 进行设置
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }

        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        // 根据 id 查询文章
        Article article = getById(id);
        // 转换成 VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类 id 查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category categoryServiceById = categoryService.getById(categoryId);
        if (categoryServiceById != null) {
            articleDetailVo.setCategoryName(categoryServiceById.getName());
        }

        // 封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }


}




