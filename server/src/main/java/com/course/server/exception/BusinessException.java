/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: BusinessException
 * Author:   111
 * Date:     2021/3/23 14:35
 * Description: 业务异常（自定义异常）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.exception;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务异常（自定义异常）〉
 *
 * @author 111
 * @create 2021/3/23
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {

    private BusinessExceptionCode code;

    public BusinessException(BusinessExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息提高性能
     * 抛出业务异常时，不打印堆栈信息，一方面是提高性能，另一方面是业务异常没有必要看堆栈信息
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
