/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ChapterPageDto
 * Author:   111
 * Date:     2021/3/15 23:44
 * Description: 继承chapterDto，补充courseId属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.dto;

/**
 * 〈一句话功能简述〉<br> 
 * 〈继承PageDto，补充courseId属性,为了pageDto能接收到courseId〉
 *
 * @author 111
 * @create 2021/3/15
 * @since 1.0.0
 */
public class ChapterPageDto extends PageDto {

    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
