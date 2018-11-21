package com.zhl.springbootelasticsearch.dao;

import com.zhl.springbootelasticsearch.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository extends EmployeeRepository<Customer,String> {

}
