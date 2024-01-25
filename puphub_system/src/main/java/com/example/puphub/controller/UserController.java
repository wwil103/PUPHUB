package com.example.puphub.controller;


import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.puphub.common.Result;
import com.example.puphub.entity.Order;
import com.example.puphub.entity.User;
import com.example.puphub.mapper.UserMapper;
import com.example.puphub.service.UserService;
import com.example.puphub.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.puphub.utils.DateUtils.getBeforeSevenDays2;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @ApiOperation("查询全部用户")
    @GetMapping("/user/info/all")
    public Result findAll(){
        List<User> list =userMapper.selectList(null);
        if (list != null){
            return Result.success("查询成功",list);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("查询用户数据")
    @GetMapping("/user/info/data")
    public Result findData(){
        //用户时间数据
        List<User> orderList = userMapper.selectList(null);
        List<String> beforeSevenDays2 = getBeforeSevenDays2();
        JSONObject object = new JSONObject();
        for (int i = 0; i < beforeSevenDays2.size(); i++) {
            int num = 0;
            for (User item: orderList) {
                String formattedDate =  item.getUUserTime().split(" ")[0];
                if(beforeSevenDays2.get(i).equals(formattedDate)) num++;
            }
            object.put(beforeSevenDays2.get(i),num);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userTime",object);
        if (jsonObject != null){
            return Result.success("查询成功",jsonObject);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("Id查询用户信息")
    @GetMapping("/user/info/{id}")
    public Result getUserById(@PathVariable int id ){
        JSONObject jsonObject = new JSONObject();
        User user = userMapper.selectById(id);
        if (user != null){
            jsonObject.put("id",user.getUId());
            jsonObject.put("name",user.getUName());
            jsonObject.put("email",user.getUEmail());
            jsonObject.put("telephone",user.getUTelephone());
            jsonObject.put("birth",user.getUBirth());
            jsonObject.put("gender",user.getUGender());
            jsonObject.put("address",user.getUAddress());
            return Result.success("查询成功",jsonObject);
        }else {
            return Result.error("查询失败");
        }
    }

    @ApiOperation("邮箱登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("u_email",user.getUEmail());
        wrapper.eq("u_password",user.getUPassword());
        User res = userMapper.selectOne(wrapper);
        if(res != null){
            if (user.getUEmail() != null){
                String token = TokenUtils.genToken(res.getUId(),res.getUPassword());
                jsonObject.put("id",res.getUId());
                jsonObject.put("name",res.getUName());
                jsonObject.put("email",res.getUEmail());
                return Result.login("登录成功",jsonObject,token);
            }else {
                return Result.error("邮箱不能为空");
            }
        }else {
            return Result.error("邮箱或密码错误");
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("u_email",user.getUEmail());
        Integer check = userMapper.selectCount(wrapper);
        if(check == 0){
            int num = userMapper.insert(user);
            if(num > 0){
                return Result.success("注册成功");
            }else {
                return Result.error("注册失败");
            }
        }else {
            return Result.error("该邮箱已被绑定");
        }

    }

    @ApiOperation("修改密码")
    @PostMapping("/user/pass")
    public Result pwd(@RequestBody User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("u_id",user.getUId());
        User check = userMapper.selectOne(wrapper);
        if(check != null){
            if(check.getUPassword().equals(user.getOldPassword())){
                if(!user.getUPassword().equals(check.getUPassword())){
                    wrapper.eq("u_password",user.getOldPassword());
                    int num = userMapper.update(user,wrapper);
                    if(num > 0){
                        return Result.success("修改成功,请重新登录");
                    }else {
                        return Result.error("修改失败");
                    }
                }else {
                    return Result.error("新密码不能与旧密码一致");
                }
            }else{
                return Result.error("旧密码错误");
            }
        }else {
            return Result.error("用户不存在");
        }

    }

    @ApiOperation("新增用户")
    @PostMapping("/user/add")
    public Result insert(@RequestBody User user){
        int num = userMapper.insert(user);
        if(num > 0){
            return Result.success("新增成功");
        }else {
            return Result.error("新增失败");
        }
    }

    @ApiOperation("更新用户")
    @PostMapping("/user/update")
    public Result update(@RequestBody User user){
        int num = userMapper.updateById(user);
        if(num > 0){
            return Result.success("更新成功");
        }else {
            return Result.error("更新失败");
        }
    }

    @ApiOperation("删除用户")
    @GetMapping("/user/delete/{id}")
    public Result delete(@PathVariable int id){
        int num = userMapper.deleteById(id);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("批量删除用户")
    @PostMapping("/user/delete")
    public Result deleteMany(@RequestBody List<Integer> idList){
        int num = userMapper.deleteBatchIds(idList);
        if(num > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("分页查询用户信息")
    @GetMapping("/user/info")
    public IPage<User> userIPage(Integer pageNum,Integer pageSize,User user){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!("").equals(user.getUId()) && user.getUId() != null)wrapper.like("u_id",user.getUId());
        if(!("").equals(user.getUName()) && user.getUName() != null)wrapper.like("u_name",user.getUName());
        if(!("").equals(user.getUPassword()) && user.getUPassword() != null)wrapper.like("u_password",user.getUPassword());
        if(!("").equals(user.getUEmail()) && user.getUEmail() != null)wrapper.like("u_email",user.getUEmail());
        if(!("").equals(user.getUTelephone()) && user.getUTelephone() != null)wrapper.like("u_telephone",user.getUTelephone());
        if(!("").equals(user.getUGender()) && user.getUGender() != null)wrapper.like("u_gender",user.getUGender());
        if(!("").equals(user.getUBirth()) && user.getUBirth() != null)wrapper.like("u_birth",user.getUBirth());
        if(!("").equals(user.getUAddress()) && user.getUAddress() != null)wrapper.like("u_address",user.getUAddress());
        return userMapper.selectPage(page,wrapper);
    }

    @ApiOperation("用户数据导出")
    @GetMapping("/user/export")
    public void export(HttpServletResponse response) throws Exception {
        List<User> list = userMapper.selectList(null);
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("uId","用户编号");
        writer.addHeaderAlias("uName","用户名");
        writer.addHeaderAlias("uGender","性别");
        writer.addHeaderAlias("uTelephone","手机号");
        writer.addHeaderAlias("uEmail","邮箱");
        writer.addHeaderAlias("uBirth","出生日期");
        writer.addHeaderAlias("uPassword","密码");
        writer.addHeaderAlias("uAddress","地址");
        // 一次性写出内容，使用默认样式
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @ApiOperation("用户数据导入")
    @PostMapping("/user/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> list = reader.readAll(User.class);
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list){
            User user = new User();
            user.setUName(row.get(0).toString());
            user.setUGender(row.get(1).toString());
            user.setUTelephone(row.get(2).toString());
            user.setUEmail(row.get(3).toString());
            user.setUBirth(row.get(4).toString());
            user.setUPassword(row.get(5).toString());
            user.setUAddress(row.get(6).toString());
            users.add(user);
        }
        Boolean res = userService.saveBatch(users);
        if(res == true){
            return Result.success("导入成功",list.size());
        }else {
            return Result.error("导出失败");
        }
    }
}
