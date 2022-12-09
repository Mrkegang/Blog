package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgstudy.constants.SystemConstants;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Article;
import com.kgstudy.domain.entity.Category;
import com.kgstudy.domain.vo.CategoryListVo;
import com.kgstudy.mapper.CategoryMapper;
import com.kgstudy.service.ArticleService;
import com.kgstudy.service.CategoryService;
import com.kgstudy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 柯刚
* @description 针对表【kg_category(分类表)】的数据库操作Service实现
* @createDate 2022-12-06 22:23:38
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        // 展示有发布正式文章的分类：先查询文章表，状态为已发布
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleQueryWrapper);
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查询分类表
        List<Category> categories = listByIds(categoryIds);
        // 展示是正常状态的分类
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        // 封装vo
        List<CategoryListVo> categoryListVos = BeanCopyUtils.copyBeanList(categories, CategoryListVo.class);
        return ResponseResult.okResult(categoryListVos);
    }
}




