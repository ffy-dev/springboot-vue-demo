package com.example.springboot.Controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.springboot.common.Result.success;

/**
 * @Author
 * @Date 2021/10/20 19:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserServiceImpl userServiceimpl;

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userServiceimpl.save(user);
        return success();
    }

    /**
     * 分页查询 借助mybatis-plus的分页插件
     *
     * @param pageNum  当前页码
     * @param pageSize 每页几条
     * @param search   查询的name
     * @return 返回一个result给浏览器 有查询成功或者失败
     */
    @GetMapping
    public Result findPage(Integer pageNum,
                           Integer pageSize,
                           String search) {
        //无关的测试
//        System.out.println(request.getParam("pageSize"));
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (StrUtil.isNotBlank(search)) {
            qw.like("nickname", search);
        }
        IPage<User> pageResult = userServiceimpl.page(new Page<>(pageNum, pageSize), qw);
//        List<User> records = pageResult.getRecords();
        return Result.success(pageResult);
    }

    @PutMapping
    public Result updateUser(@RequestBody User user) {
        userServiceimpl.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        userServiceimpl.removeById(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        if (userServiceimpl.ifExistsUser(user.getUsername(),user.getPassword())){
            return Result.success();
        }else{
            return Result.error("1","用户名或密码错误");
        }
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if (!userServiceimpl.ifExistsUser(user.getUsername())){
            save(user);
            return Result.success();
        }else{
            return Result.error("1","用户名已存在");
        }
    }

}
