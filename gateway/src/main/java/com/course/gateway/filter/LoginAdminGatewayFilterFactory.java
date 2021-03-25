/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: LoginAdminGatewayFilterFactory
 * Author:   111
 * Date:     2021/3/25 16:24
 * Description: 给系统添加自定义拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈给系统添加自定义拦截器,注入拦截器类〉
 *
 * @author 111
 * @create 2021/3/25
 * @since 1.0.0
 */
@Component
public class LoginAdminGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Resource
    LoginAdminGatewayFilter loginAdminGatewayFilter;

    @Override
    public GatewayFilter apply(Object config) {
        return loginAdminGatewayFilter;
    }
}
