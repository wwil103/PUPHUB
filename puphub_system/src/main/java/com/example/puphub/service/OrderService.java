package com.example.puphub.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.puphub.entity.Order;
import com.example.puphub.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
}
