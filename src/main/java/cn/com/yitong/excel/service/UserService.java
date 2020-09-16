package cn.com.yitong.excel.service;

import cn.com.yitong.excel.model.User;

import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
public interface UserService {

    List<User> queryUserList();

    void insertUsers(List<User> userList);
}
