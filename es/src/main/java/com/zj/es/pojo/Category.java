/**
 * Copyright (C), 2019
 * FileName: Category
 * Author:   zhangjian
 * Date:     2019/6/20 17:30
 * Description: 分类类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.es.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "how2java",type = "category")
public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
