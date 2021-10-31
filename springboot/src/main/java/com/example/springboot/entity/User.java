package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author
 * @Date 2021/10/20 20:19
 * @Version 1.0
 */
@Data
public class User {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private String sex;
    private String address;
}
