/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: BusinessExceptionCode
 * Author:   111
 * Date:     2021/3/23 14:41
 * Description: 业务异常代码类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.exception;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务异常代码类〉
 *
 * @author 111
 * @create 2021/3/23
 * @since 1.0.0
 */
public enum  BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在");

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
