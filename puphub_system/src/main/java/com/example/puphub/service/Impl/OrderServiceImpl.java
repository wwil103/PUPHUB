package com.example.puphub.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.example.puphub.entity.Order;
import com.example.puphub.service.OrderService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OrderServiceImpl extends OrderService {
    @Override
    public boolean save(Order entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<Order> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Order> entityList) {
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
    public boolean remove(Wrapper<Order> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(Order entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean update(Wrapper<Order> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    public boolean update(Order entity, Wrapper<Order> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateBatchById(Collection<Order> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    public Order getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public List<Order> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    @Override
    public List<Order> listByMap(Map<String, Object> columnMap) {
        return super.listByMap(columnMap);
    }

    @Override
    public Order getOne(Wrapper<Order> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public int count(Wrapper<Order> queryWrapper) {
        return super.count(queryWrapper);
    }

    @Override
    public List<Order> list(Wrapper<Order> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Override
    public List<Order> list() {
        return super.list();
    }

    @Override
    public <E extends IPage<Order>> E page(E page, Wrapper<Order> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Order>> E page(E page) {
        return super.page(page);
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Order> queryWrapper) {
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
    public List<Object> listObjs(Wrapper<Order> queryWrapper) {
        return super.listObjs(queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Order> queryWrapper, Function<? super Object, V> mapper) {
        return super.listObjs(queryWrapper, mapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<Order> queryWrapper) {
        return super.pageMaps(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return super.pageMaps(page);
    }

    @Override
    public QueryChainWrapper<Order> query() {
        return super.query();
    }

    @Override
    public LambdaQueryChainWrapper<Order> lambdaQuery() {
        return super.lambdaQuery();
    }

    @Override
    public KtQueryChainWrapper<Order> ktQuery() {
        return super.ktQuery();
    }

    @Override
    public KtUpdateChainWrapper<Order> ktUpdate() {
        return super.ktUpdate();
    }

    @Override
    public UpdateChainWrapper<Order> update() {
        return super.update();
    }

    @Override
    public LambdaUpdateChainWrapper<Order> lambdaUpdate() {
        return super.lambdaUpdate();
    }

    @Override
    public boolean saveOrUpdate(Order entity, Wrapper<Order> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }
}
