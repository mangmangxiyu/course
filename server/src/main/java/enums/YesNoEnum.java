/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: YesNoEnum
 * Author:   111
 * Date:     2021/3/15 14:56
 * Description: 演示生成器用枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈演示生成器用枚举〉
 *
 * @author 111
 * @create 2021/3/15
 * @since 1.0.0
 */
public enum YesNoEnum {

    YES("1", "是"),
    NO("0", "否");

    private String code;
    private String desc;

    YesNoEnum(String code, String desc) {
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
