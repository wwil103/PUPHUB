package com.example.puphub.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.puphub.common.Result;
import com.example.puphub.entity.Services;
import com.example.puphub.entity.User;
import com.example.puphub.mapper.ServicesMapper;
import com.example.puphub.service.ServicesService;
import com.example.puphub.utils.ossStorageUploadUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import static com.example.puphub.utils.DateUtils.getBeforeSevenDays2;

@RestController
@RequestMapping("/service")
public class ServicesController {
    @Autowired
    private ServicesMapper servicesMapper;

    @Autowired
    private ServicesService servicesService;

    @ApiOperation("查询全部服务")
    @GetMapping("/info/all")
    public Result findAll(){
        List<Services> list =servicesMapper.selectList(null);
        if (list != null){
            return Result.success("查询成功",list);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("查询服务数据")
    @GetMapping("/info/data")
    public Result findData(){
        //服务时间数据
        List<Services> orderList = servicesMapper.selectList(null);
        List<String> beforeSevenDays2 = getBeforeSevenDays2();
        JSONObject object = new JSONObject();
        for (int i = 0; i < beforeSevenDays2.size(); i++) {
            int num = 0;
            for (Services item: orderList) {
                String formattedDate =  item.getSServiceTime().split(" ")[0];
                if(beforeSevenDays2.get(i).equals(formattedDate)) num++;
            }
            object.put(beforeSevenDays2.get(i),num);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceTime",object);
        if (jsonObject != null){
            return Result.success("查询成功",jsonObject);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("Id查询服务信息")
    @GetMapping("/info/{id}")
    public Result getUserById(@PathVariable int id ){
        Services services = servicesMapper.selectById(id);
        if (services != null){
            return Result.success("查询成功",services);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("新增服务")
    @PostMapping("/add")
    public Result insert(@RequestBody Services services){
        int num = servicesMapper.insert(services);
        if(num > 0){
            return Result.success("新增成功");
        }else {
            return Result.error("新增失败");
        }
    }

    @ApiOperation("更新服务")
    @PostMapping("/update")
    public Result update(@RequestBody Services services){
        int num = servicesMapper.updateById(services);
        if(num > 0){
            return Result.success("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }

    @ApiOperation("删除服务")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        int num = servicesMapper.deleteById(id);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("批量删除服务")
    @PostMapping("/delete")
    public Result deleteMany(@RequestBody List<Integer> idList){
        int num = servicesMapper.deleteBatchIds(idList);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("分页查询服务信息")
    @GetMapping("/info")
    public IPage<Services> userIPage(Integer pageNum,Integer pageSize,Services services){
        IPage<Services> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Services> wrapper = new QueryWrapper<>();
        if(!("").equals(services.getSId()) && services.getSId() != null)wrapper.like("s_id",services.getSId());
        if(!("").equals(services.getSName()) && services.getSName() != null)wrapper.like("s_name",services.getSName());
        if(!("").equals(services.getSIntro()) && services.getSIntro() != null)wrapper.like("s_intro",services.getSIntro());
        if(!("").equals(services.getSPrice()) && services.getSPrice() != null)wrapper.like("s_price",services.getSPrice());
        if(!("").equals(services.getSPoster()) && services.getSPoster() != null)wrapper.like("s_poster",services.getSPoster());
        return servicesMapper.selectPage(page,wrapper);
    }

    @ApiOperation("服务数据导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Services> list = servicesMapper.selectList(null);
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("sId","服务编号");
        writer.addHeaderAlias("sName","服务名称");
        writer.addHeaderAlias("sIntro","服务简介");
        writer.addHeaderAlias("sPrice","服务价格");
        writer.addHeaderAlias("sPoster","服务海报");
        // 一次性写出内容，使用默认样式
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("服务信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @ApiOperation("服务数据导入")
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> list = reader.readAll(User.class);
        List<List<Object>> list = reader.read(1);
        List<Services> servicess = CollUtil.newArrayList();
        for (List<Object> row : list){
            Services services = new Services();
            services.setSName(row.get(0).toString());
            services.setSIntro(row.get(1).toString());
            services.setSPrice(row.get(2).toString());
            services.setSPoster(row.get(3).toString());
            servicess.add(services);
        }
        Boolean res = servicesService.saveBatch(servicess);
        if(res == true){
            return Result.success("导入成功",list.size());
        }else {
            return Result.error("导出失败");
        }
    }

    @ApiOperation("服务海报上传")
    @PostMapping("/upload")
    public Result up(MultipartFile file) throws Exception {
        return ossStorageUploadUtil.uploadImg(file);
    }

//    上传到static
//    public Result up(MultipartFile file) {
//        if (file.isEmpty()) {
//            return Result.error("上传失败");
//        }
//
//        String originalFilename = file.getOriginalFilename();
//        String ext = "." + originalFilename.split("\\.")[1];
//        String uuid = UUID.randomUUID().toString().replace("-","");
//        String fileName = uuid + ext;
//
//        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
//        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
//                "\\src\\main\\resources\\static\\images\\service\\";
//        String path = pre + fileName;
//        File dir = new File(path);
//        if(!dir.exists()) dir.mkdirs();
//        try {
//            file.transferTo(new File(path));
//            return Result.success("上传成功",fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Result.error("上传失败");
//    }

    @ApiOperation("服务海报删除")
    @PostMapping("/upload/delete")
    public Result upDelete(@RequestBody String fileName) {
        int lastIndexOf = fileName.lastIndexOf('"');
        String fName = fileName.substring(1,lastIndexOf);
        if (StrUtil.isBlank(fName)) {
            return Result.error("参数错误");
        }
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\service\\";
        String path = pre + fName;
        File dir = new File(path);
        if(!dir.exists()) return Result.error("文件不存在");
        if(dir.delete()){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
}
