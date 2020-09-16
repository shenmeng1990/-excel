package cn.com.yitong.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
@Data
@Builder
public class User extends BaseRowModel {

    @ExcelProperty(value = {"主键ID"}, index = 0)
    private Long id;

    @ExcelProperty(value = {"用户年龄"}, index = 1)
    private Integer age;

    @ExcelProperty(value = {"用户姓名"}, index = 2)
    private String name;

    @ExcelProperty(value = {"email"}, index = 3)
    private String email;

    public void User(Long id,Integer age,String name ,String email){
        this.id=id;
        this.age=age;
        this.name=name;
        this.email=email;
    }

}
