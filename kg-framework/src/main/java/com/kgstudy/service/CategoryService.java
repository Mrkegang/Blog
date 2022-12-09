package com.kgstudy.service;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 柯刚
* @description 针对表【kg_category(分类表)】的数据库操作Service
* @createDate 2022-12-06 22:23:38
*/
public interface CategoryService extends IService<Category> {

    /**
     * 查询所有分类列表
     * @return
     */
    ResponseResult getCategoryList();
}
