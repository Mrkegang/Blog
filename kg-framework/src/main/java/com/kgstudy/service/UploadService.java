package com.kgstudy.service;

import com.kgstudy.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/12 15:32
 */
public interface UploadService {

    /**
     * 图片上传
     * @param img
     * @return
     */
    ResponseResult uploadImg(MultipartFile img);
}
