/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TestMapper
 * Author:   111
 * Date:     2021/3/3 21:28
 * Description: 测试表test的Mapper.class
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.mapper;

import com.course.server.domain.Test;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试表test的Mapper.class〉
 *
 * @author 111
 * @create 2021/3/3
 * @since 1.0.0
 */

public interface TestMapper {

    public List<Test> list();
}
