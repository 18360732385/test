/**
 * Copyright (C), 2019
 * FileName: UserMapper
 * Author:   zhangjian
 * Date:     2019/7/17 15:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.redissondemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(null,#{name},#{age});")
    int addUser(String name, Integer age);
}
