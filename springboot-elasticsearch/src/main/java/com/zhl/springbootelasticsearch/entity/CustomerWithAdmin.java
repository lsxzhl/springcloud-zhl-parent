package com.zhl.springbootelasticsearch.entity;

import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/6 18:11
 */
public class CustomerWithAdmin {

    private boolean isAdmin = false;

    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isAdmin(){
        isAdmin = true;
        return isAdmin;
    }

    public void setAdmin(boolean admin){
        isAdmin = admin;
    }
}
