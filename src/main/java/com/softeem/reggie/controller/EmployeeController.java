package com.softeem.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.softeem.reggie.common.Result;
import com.softeem.reggie.entity.Employee;
import com.softeem.reggie.service.EmplyeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmplyeeService emplyeeService;

    //登录
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = emplyeeService.getOne(queryWrapper);

        if (emp == null){
            return Result.error("登陆失败");
        }
        if (!emp.getPassword().equals(password)){
            return Result.error("登陆失败");
        }
        if (emp.getStatus() == 0){
            return Result.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }
}
