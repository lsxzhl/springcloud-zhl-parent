package com.zhl.springbootelasticsearch.dao;

import com.zhl.springbootelasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository<C, S> extends ElasticsearchRepository<Employee,String> {

    Employee queryEmployeeById(@Param("id") String id);

}
