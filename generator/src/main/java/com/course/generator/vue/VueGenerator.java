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
package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成server模块的dto，service，controller的生成类〉
 *
 * @author 111
 * @create 2021/3/13
 * @since 1.0.0
 */
public class VueGenerator {

    static String MODULE = "system";
    static String toVuePath = "admin\\src\\views\\admin\\";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        // 只生成配置文件中的第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc=reader.read(file);
        //获取xml文件的根节点
        Element rootElement=doc.getRootElement();
        //读取context节点
        Element contextElement = rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //取第一个“table”的节点
        tableElement=contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("表："+tableElement.attributeValue("tableName"));
        System.out.println("Domain："+tableElement.attributeValue("domainObjectName"));

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", module);
        map.put("tableNameCn", tableNameCn);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // 生成dto
        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.generator(toVuePath + domain + ".vue", map);


    }

    /**
     * 获取所有的Java类型，使用Set去重
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
