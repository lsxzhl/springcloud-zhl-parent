package com.zhl.springbootexcel.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/23 16:06
 */
public class ProductVo {

    private int id;

    private BigDecimal groupPrice;

    private boolean isActivity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(BigDecimal groupPrice) {
        this.groupPrice = groupPrice;
    }

    public boolean isActivity() {
        return isActivity;
    }

    public void setActivity(boolean activity) {
        isActivity = activity;
    }

    public void Test(){
        //商品列表
        List<ProductVo> list = new ArrayList<>();
        //参加活动的商品ID集合 要排重  一个商品可能参加多个活动
        //要查询活动正在进行中 参加活动的商品ID
        List<Integer> strings = new ArrayList<>();
        for (ProductVo productVo : list){
            if(strings.contains(productVo.getId())){
                productVo.setActivity(true);
            }else{
                productVo.setActivity(false);
            }
        }
    }



}
