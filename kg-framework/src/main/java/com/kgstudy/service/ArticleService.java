package com.kgstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Article;

/**
* @author 柯刚
* @description 针对表【kg_article(文章表)】的数据库操作Service
* @createDate 2022-12-05 22:48:24
*/
public interface ArticleService extends IService<Article> {

    /**
     * 查询热门文章，封装成ResponseResult返回
     * @return
     */
    ResponseResult hotArticleList();

    /**
     * 查询分类文章下的所有文章
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    ResponseResult getArticleDetail(Long id);
}
