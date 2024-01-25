package com.example.puphub.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.example.puphub.entity.Services;
import com.example.puphub.service.ServicesService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ServicesServiceImpl extends ServicesService {
    @Override
    public boolean save(Services entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Services> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Services> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    public boolean remove(Wrapper<Services> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(Services entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean update(Wrapper<Services> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    public boolean update(Services entity, Wrapper<Services> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<Services> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public Services getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public List<Services> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    @Override
    public List<Services> listByMap(Map<String, Object> columnMap) {
        return super.listByMap(columnMap);
    }

    @Override
    public Services getOne(Wrapper<Services> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public int count(Wrapper<Services> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<Services> list(Wrapper<Services> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public List<Services> list() {
        return super.list();
    }

    @Override
    public <E extends IPage<Services>> E page(E page, Wrapper<Services> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Services>> E page(E page) {
        return super.page(page);
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Services> queryWrapper) {
        return super.listMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return super.listMaps();
    }

    @Override
    public List<Object> listObjs() {
        return super.listObjs();
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return super.listObjs(mapper);
    }

    @Override
    public List<Object> listObjs(Wrapper<Services> queryWrapper) {
        return super.listObjs(queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Services> queryWrapper, Function<? super Object, V> mapper) {
        return super.listObjs(queryWrapper, mapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<Services> queryWrapper) {
        return super.pageMaps(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return super.pageMaps(page);
    }

    @Override
    public QueryChainWrapper<Services> query() {
        return super.query();
    }

    @Override
    public LambdaQueryChainWrapper<Services> lambdaQuery() {
        return super.lambdaQuery();
    }

    @Override
    public KtQueryChainWrapper<Services> ktQuery() {
        return super.ktQuery();
    }

    @Override
    public KtUpdateChainWrapper<Services> ktUpdate() {
        return super.ktUpdate();
    }

    @Override
    public UpdateChainWrapper<Services> update() {
        return super.update();
    }

    @Override
    public LambdaUpdateChainWrapper<Services> lambdaUpdate() {
        return super.lambdaUpdate();
    }

    @Override
    public boolean saveOrUpdate(Services entity, Wrapper<Services> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }
}
