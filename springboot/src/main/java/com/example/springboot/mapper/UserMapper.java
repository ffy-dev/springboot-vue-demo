package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author
 * @Date 2021/10/20 20:17
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
