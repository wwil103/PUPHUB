package com.example.puphub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.puphub.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
