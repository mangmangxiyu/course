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
import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成server模块的dto，service，controller的生成类〉
 *
 * @author 111
 * @create 2021/3/13
 * @since 1.0.0
 */
public class ServerGenerator {

    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE + "\\src\\main\\java\\com\\course\\" + MODULE + "\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        String module = MODULE;
        String tableNameCn = "小节";
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", module);
        map.put("tableNameCn", tableNameCn);

        // 生成service的代码
        FreemakerUtil.initConfig("service.ftl");
        FreemakerUtil.generator(toServicePath + Domain + "Service.java", map);

        // 生成controller的代码
        FreemakerUtil.initConfig("controller.ftl");
        FreemakerUtil.generator(toControllerPath + Domain + "Controller.java", map);
    }
}
