/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SpringMvcConfig
 * Author:   111
 * Date:     2021/3/18 20:12
 * Description: 图片的资源映射
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片的资源映射〉
 *
 * @author 111
 * @create 2021/3/18
 * @since 1.0.0
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:D:/ideacore/file/course/course/");
    }
    //http://127.0.0.1:9003/file/f/teacher/YNGqXJEO-profile-pic.jpg
}
