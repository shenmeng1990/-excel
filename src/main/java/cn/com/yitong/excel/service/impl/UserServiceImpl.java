package cn.com.yitong.excel.service.impl;

import cn.com.yitong.excel.dao.UserDao;
import cn.com.yitong.excel.model.User;
import cn.com.yitong.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> queryUserList() {
        return userDao.queryUserList();
    }

    @Override
    public void insertUsers(List<User> userList) {
        userDao.insertUsers(userList);
    }
}
