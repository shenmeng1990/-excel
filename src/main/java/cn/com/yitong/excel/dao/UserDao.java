package cn.com.yitong.excel.dao;

import cn.com.yitong.excel.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
@Mapper
public interface UserDao {

    List<User> queryUserList();

    void insertUsers(@Param("userList") List<User> userList);

}
