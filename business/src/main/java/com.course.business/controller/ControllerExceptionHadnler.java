/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ControllerExceptionHadnler
 * Author:   111
 * Date:     2021/3/12 19:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.business.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 111
 * @create 2021/3/12
 * @since 1.0.0
 */
@ControllerAdvice
public class ControllerExceptionHadnler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHadnler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validationExceptionHandler(ValidatorException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());
        responseDto.setMessage("请求参数异常");// 如果开发过程中直接抛出请求参数异常说明未进行前端校验进行补充
        //responseDto.setMessage(e.getMessage());// 校验里抛出异常的文本
        return responseDto;
    }
}
