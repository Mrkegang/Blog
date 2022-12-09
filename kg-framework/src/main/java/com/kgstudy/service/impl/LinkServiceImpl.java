package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgstudy.constants.SystemConstants;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Link;
import com.kgstudy.domain.vo.AllLinkVo;
import com.kgstudy.mapper.LinkMapper;
import com.kgstudy.service.LinkService;
import com.kgstudy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 柯刚
* @description 针对表【kg_link(友链)】的数据库操作Service实现
* @createDate 2022-12-07 13:48:39
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult getAllLink() {
        // 查询审核状态为通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        // 封装VO
        List<AllLinkVo> allLinkVos = BeanCopyUtils.copyBeanList(links, AllLinkVo.class);

        // 返回封装后的结果
        return ResponseResult.okResult(allLinkVos);
    }
}




