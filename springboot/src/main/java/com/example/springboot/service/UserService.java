package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;

/**
 * @Author
 * @Date 2021/10/20 20:21
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    /**
     * 验证输入的用户名和密码是否已存在
     * @param username String类型的值
     * @param password String类型的值
     * @return 是否存在户名和密码同时匹配的数据
     */
    boolean ifExistsUser(String username,String password);

    /**
     * 验证是否存在用户名
     * @param username String类型的值
     * @return 是否存在用户名
     */
    boolean ifExistsUser(String username);
}
