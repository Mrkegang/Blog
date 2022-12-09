package com.kgstudy.service;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 柯刚
* @description 针对表【kg_link(友链)】的数据库操作Service
* @createDate 2022-12-07 13:48:39
*/
public interface LinkService extends IService<Link> {

    /**
     * 查询所有通过审核的友链
     * @return
     */
    ResponseResult getAllLink();
}
