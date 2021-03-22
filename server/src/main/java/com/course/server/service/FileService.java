/**
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: FileService
 * Author:   111
 * Date:     2021/3/7 18:15
 * Description: 大章的service业务逻辑代码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.course.server.service;

import com.course.server.domain.File;
import com.course.server.domain.FileExample;
import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.FileMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
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
public class FileService {

    @Resource
    private FileMapper fileMapper;

    // 前端传递过来的类再重新获取返回void关键字

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<FileDto> fileDtoList = CopyUtil.copyList(fileList, FileDto.class);
        pageDto.setList(fileDtoList);
    }


    /**
     * 保存，有值时更新，无值是新增
     * @param fileDto
     */
    public void save(FileDto fileDto) {
        File file = CopyUtil.copy(fileDto, File.class);
        File fileDb = selectByKey(fileDto.getKey());
        if (fileDb == null) {
            this.insert(file);
        } else {
            fileDb.setShardIndex(fileDto.getShardIndex());
            this.update(fileDb);
        }
    }

    /**
     * 新增
     * @param file
     */
    private void insert(File file) {

        Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

    /**
     * 更新
     * @param file
     */
    private void update(File file) {
        file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);
    }

    /**
     *  通过key查询文件
     * @param key
     * @return
     */
    public File selectByKey(String key) {
        FileExample example = new FileExample();
        example.createCriteria().andKeyEqualTo(key);
        List<File> fileList = fileMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(fileList)) {
            return null;
        }
        return fileList.get(0);
    }

    /**
     * 根据文件标识查询数据库
     * @param key
     * @return fileDto
     */
    public FileDto findByKey(String key) {
        return CopyUtil.copy(selectByKey(key), FileDto.class);
    }
}
