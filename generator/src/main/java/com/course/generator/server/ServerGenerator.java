/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ServerGenerator
 * Author:   111
 * Date:     2021/3/13 17:43
 * Description: 生成server模块的dto，service，controller的生成类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.generator.server;

import com.course.generator.util.FreemakerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成server模块的dto，service，controller的生成类〉
 *
 * @author 111
 * @create 2021/3/13
 * @since 1.0.0
 */
public class ServerGenerator {

    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\server\\";

    public static void main(String[] args) throws IOException, TemplateException {
        FreemakerUtil.initConfig("test.ftl");
        FreemakerUtil.generator(toPath + "Test.java");
    }
}
