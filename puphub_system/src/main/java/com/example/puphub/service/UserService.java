package com.example.puphub.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.puphub.entity.User;
import com.example.puphub.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
}
