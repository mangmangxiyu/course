/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: TestController
 * Author:   111
 * Date:     2021/3/3 13:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.system.controller;/**
 * Created by 111 on 2021/3/3.
 */

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public List<Test> test() {
        return testService.list();
    }
}
