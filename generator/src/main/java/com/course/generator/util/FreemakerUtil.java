/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: FreemakerUtil
 * Author:   111
 * Date:     2021/3/13 17:35
 * Description: freemarker代码生成生成器抽象成工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈freemarker代码生成生成器抽象成工具类〉
 *
 * @author 111
 * @create 2021/3/13
 * @since 1.0.0
 */
public class FreemakerUtil {

    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";

    static Template temp;
    public static void initConfig(String ftlName) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        temp = cfg.getTemplate(ftlName);
    }

    public static void generator(String fileName) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(null, bw);
        bw.flush();
        fw.close();
    }

}
