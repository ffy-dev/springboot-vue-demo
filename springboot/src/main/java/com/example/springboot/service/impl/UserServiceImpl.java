package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author
 * @Date 2021/10/20 20:22
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {
    @Resource
    UserMapper userMapper;

    public boolean ifExistsUser(String username, String password) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        User user = userMapper.selectOne(qw);
        if (user==null){
            return false;
        }else {
            return true;
        }
    }
    public boolean ifExistsUser(String username){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        User user = userMapper.selectOne(qw);
        return user != null;

    }


}
