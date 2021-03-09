/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ChapterService
 * Author:   111
 * Date:     2021/3/7 18:15
 * Description: 大章的service业务逻辑代码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈大章的service业务逻辑代码〉
 *
 * @author 111
 * @create 2021/3/7
 * @since 1.0.0
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        PageHelper.startPage(2, 1);
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        ChapterDto chapterDto = new ChapterDto();
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        // 遍历chapterList结果copy新的chaperDtoList
        for (int i = 0, l = chapterList.size(); i < l; i++) {
            Chapter chapter = chapterList.get(i);

            // 实体间的复制；后期对BeanUtils做闭环处理简化使用提高效率
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
