/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: SectionChargeEnum
 * Author:   111
 * Date:     2021/3/15 14:36
 * Description: 枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈枚举类〉
 *
 * @author 111
 * @create 2021/3/15
 * @since 1.0.0
 */
public enum SectionChargeEnum {

    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;
    private String desc;

    SectionChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
