/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TestService
 * Author:   111
 * Date:     2021/3/3 21:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 111
 * @create 2021/3/3
 * @since 1.0.0
 */
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;


    public List<Test> list() {
        TestExample testExample = new TestExample();

        // createCriteria()=>where,后续条件用and;setOrderByClause()=>order
        testExample.createCriteria().andIdEqualTo("1");
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
