/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: PageDto
 * Author:   111
 * Date:     2021/3/9 23:14
 * Description: 接收前端分页请求数据的Dto，再传递给后端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.dto;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈接收前端分页请求数据的Dto，再传递给后端〉
 *
 * @author 111
 * @create 2021/3/9
 * @since 1.0.0
 */
public class PageDto<T> {

    /*
    前端传递过来的当前页码，页面大小
     */
    protected int page;
    protected int size;

    /*
    后端处理请求查询数据库后的结果
     */
    protected long total;
    protected List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);

        sb.append('}');
        return sb.toString();
    }
}
