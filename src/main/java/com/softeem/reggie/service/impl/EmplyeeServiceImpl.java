package com.softeem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.reggie.entity.Employee;
import com.softeem.reggie.mapper.EmployeeMapper;
import com.softeem.reggie.service.EmplyeeService;
import org.springframework.stereotype.Service;

@Service
public class EmplyeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmplyeeService{

}
