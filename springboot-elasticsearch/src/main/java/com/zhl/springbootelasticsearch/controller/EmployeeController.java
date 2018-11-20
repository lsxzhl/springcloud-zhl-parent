package com.zhl.springbootelasticsearch.controller;

import com.zhl.springbootelasticsearch.dao.EmployeeRepository;
import com.zhl.springbootelasticsearch.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //增加
    @RequestMapping("/add")
    public String add(){

        Employee employee=new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeRepository.save(employee);

        System.err.println("add a obj");

        return "success";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(){
        Employee employee=new Employee();
        employee.setId("1");
        employeeRepository.delete(employee);

        return "success";
    }

    //局部更新
    @RequestMapping("/update")
    public String update(){

        Employee employee=employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);

        System.err.println("update a obj");

        return "success";
    }

    //查询
    @RequestMapping("/query")
    public Employee query(){

        Employee accountInfo=employeeRepository.queryEmployeeById("1");
        System.err.println(accountInfo.toString());

        return accountInfo;
    }



}
