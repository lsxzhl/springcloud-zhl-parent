package com.zhl.springbootexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/18 14:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "密码", index = 1)
    private String password;

    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;


}
