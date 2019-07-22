package com.zhl.springbootelasticsearch.controller;


import com.zhl.springbootelasticsearch.dao.EmployeeRepository;
import com.zhl.springbootelasticsearch.entity.Employee;
import org.elasticsearch.common.recycler.Recycler;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //增加
    @PostMapping
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
    @DeleteMapping
    public String delete(){
        Employee employee=new Employee();
        employee.setId("1");
        employeeRepository.delete(employee);

        return "success";
    }

    //局部更新
    @PutMapping
    public String update(){

        Employee employee=employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);

        System.err.println("update a obj");

        return "success";
    }

    //查询
    @GetMapping
    public Employee query(){

        Employee accountInfo=employeeRepository.queryEmployeeById("1");
        System.err.println(accountInfo.toString());

        return accountInfo;
    }





}
