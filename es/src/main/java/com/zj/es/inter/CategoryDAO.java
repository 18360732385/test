/**
 * Copyright (C), 2019
 * FileName: CategoryDAO
 * Author:   zhangjian
 * Date:     2019/6/20 17:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.es.inter;

import com.zj.es.pojo.Category;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public class CategoryDAO implements ElasticsearchRepository<Category,Integer> {

    @Override
    public <S extends Category> S index(S s) {
        return null;
    }

    @Override
    public Iterable<Category> search(QueryBuilder queryBuilder) {
        return null;
    }

    @Override
    public Page<Category> search(QueryBuilder queryBuilder, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Category> search(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Page<Category> searchSimilar(Category category, String[] strings, Pageable pageable) {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Class<Category> getEntityClass() {
        return null;
    }

    @Override
    public Iterable<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> S save(S s) {
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    public Category findOne(int id) {
        return null;
    }
}
