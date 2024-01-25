package com.example.puphub.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.puphub.common.Result;
import com.example.puphub.entity.Order;
import com.example.puphub.mapper.OrderMapper;
import com.example.puphub.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.puphub.utils.DateUtils.getBeforeSevenDays2;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @ApiOperation("查询全部订单")
    @GetMapping("/info/all")
    public Result findAll(){
        List<Order> list =orderMapper.selectList(null);
        if (list != null){
            return Result.success("查询成功",list);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("查询订单数据")
    @GetMapping("/info/data")
    public Result findData(){
        //订单时间数据
        List<Order> orderList = orderMapper.selectList(null);
        List<String> beforeSevenDays2 = getBeforeSevenDays2();
        JSONObject object = new JSONObject();
        for (int i = 0; i < beforeSevenDays2.size(); i++) {
            int num = 0;
            for (Order item: orderList) {
                String formattedDate =  item.getOOrderTime().split(" ")[0];
                if(beforeSevenDays2.get(i).equals(formattedDate)) num++;
            }
            object.put(beforeSevenDays2.get(i),num);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderTime",object);
        Integer orderTotal = orderMapper.selectCount(null);
        //进行中订单
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.lt("o_status",2);
        Integer orderActive = orderMapper.selectCount(wrapper);
        //订单待开始
        QueryWrapper wrapper1 = new QueryWrapper<>();
        wrapper1.eq("o_status",0);
        Integer orderStart = orderMapper.selectCount(wrapper1);
        //订单进行中
        QueryWrapper wrapper2 = new QueryWrapper<>();
        wrapper2.eq("o_status",1);
        Integer orderMiddle = orderMapper.selectCount(wrapper2);
        //订单已完成
        QueryWrapper wrapper3 = new QueryWrapper<>();
        wrapper3.eq("o_status",2);
        Integer orderEnd = orderMapper.selectCount(wrapper3);
        jsonObject.put("orderTotal",orderTotal);
        jsonObject.put("orderActive",orderActive);
        jsonObject.put("orderStart",orderStart);
        jsonObject.put("orderMiddle",orderMiddle);
        jsonObject.put("orderEnd",orderEnd);
        if (jsonObject != null){
            return Result.success("查询成功",jsonObject);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("用户Id查询订单信息")
    @GetMapping("/info/{id}")
    public Result getOrderById(@PathVariable int id ){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("o_uid",id);
        List<Order> order = orderMapper.selectList(wrapper);
        if (order != null){
            return Result.success("查询成功",order);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("新增订单")
    @PostMapping("/add")
    public Result insert(@RequestBody Order order){
        int num = orderMapper.insert(order);
        if(num > 0){
            return Result.success("新增成功");
        }else {
            return Result.error("新增失败");
        }
    }

    @ApiOperation("更新订单")
    @PostMapping("/update")
    public Result update(@RequestBody Order order){
        int num = orderMapper.updateById(order);
        if(num > 0){
            return Result.success("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }

    @ApiOperation("删除订单")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        int num = orderMapper.deleteById(id);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("批量删除订单")
    @PostMapping("/delete")
    public Result deleteMany(@RequestBody List<Integer> idList){
        int num = orderMapper.deleteBatchIds(idList);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("分页查询订单信息")
    @GetMapping("/info")
    public IPage<Order> orderIPage(Integer pageNum,Integer pageSize,Order order){
        IPage<Order> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if(!("").equals(order.getOId()) && order.getOId() != null)wrapper.like("o_id",order.getOId());
        if(!("").equals(order.getOUid()) && order.getOUid() != null)wrapper.like("o_uid",order.getOUid());
        if(!("").equals(order.getOService()) && order.getOService() != null)wrapper.like("o_service",order.getOService());
        if(!("").equals(order.getODate()) && order.getODate() != null)wrapper.like("o_date",order.getODate());
        if(!("").equals(order.getOTime()) && order.getOTime() != null)wrapper.like("o_time",order.getOTime());
        if(!("").equals(order.getOOrderTime()) && order.getOOrderTime() != null)wrapper.like("o_orderTime",order.getOOrderTime());
        if(!("").equals(order.getOAge()) && order.getOAge() != null)wrapper.like("o_age",order.getOAge());
        if(!("").equals(order.getOSize()) && order.getOSize() != null)wrapper.like("o_size",order.getOSize());
        if(!("").equals(order.getOAddress()) && order.getOAddress() != null)wrapper.like("o_address",order.getOAddress());
        if(!("").equals(order.getOContact()) && order.getOContact() != null)wrapper.like("o_contact",order.getOContact());
        if(!("").equals(order.getOStatus()) && order.getOStatus() != null)wrapper.like("o_status",order.getOStatus());
        return orderMapper.selectPage(page,wrapper);
    }

    @ApiOperation("订单数据导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Order> list = orderMapper.selectList(null);
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("oId","订单编号");
        writer.addHeaderAlias("oUid","用户ID");
        writer.addHeaderAlias("oService","服务名称");
        writer.addHeaderAlias("oDate","服务日期");
        writer.addHeaderAlias("oTime","时间段");
        writer.addHeaderAlias("oOrderTime","下单时间");
        writer.addHeaderAlias("oAge","狗狗年龄");
        writer.addHeaderAlias("oSize","狗狗大小");
        writer.addHeaderAlias("oAddress","地址");
        writer.addHeaderAlias("oContact","联系方式");
        writer.addHeaderAlias("oStatus","订单状态");
        // 一次性写出内容，使用默认样式
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("订单信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @ApiOperation("订单数据导入")
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<Order> list = reader.readAll(Order.class);
        List<List<Object>> list = reader.read(1);
        List<Order> orders = CollUtil.newArrayList();
        for (List<Object> row : list){
            Order order = new Order();
            order.setOService(row.get(0).toString());
            order.setOUid(row.get(1).toString());
            order.setODate(row.get(2).toString());
            order.setOTime(row.get(3).toString());
            order.setOOrderTime(row.get(4).toString());
            order.setOAge(row.get(5).toString());
            order.setOSize(row.get(6).toString());
            order.setOAddress(row.get(7).toString());
            order.setOContact(row.get(8).toString());
            order.setOStatus(row.get(9).toString());
            orders.add(order);
        }
        Boolean res = orderService.saveBatch(orders);
        if(res == true){
            return Result.success("导入成功",list.size());
        }else {
            return Result.error("导出失败");
        }
    }
}
