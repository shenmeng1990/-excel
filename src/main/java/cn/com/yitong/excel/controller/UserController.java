package cn.com.yitong.excel.controller;

import cn.com.yitong.excel.model.User;
import cn.com.yitong.excel.service.UserService;
import cn.com.yitong.excel.util.ExportExcelUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/export")
    @ResponseBody
    public String  exportUserList(@RequestParam("fileName") String fileName){
        Long begin = System.currentTimeMillis()/1000;
        List<User> users = userService.queryUserList();
        ExportExcelUtil.export(fileName, users,User.class);
        Long end = System.currentTimeMillis()/1000;
        return "导出"+users.size()+"条数据，花费时间："+(end - begin)+"秒";
    }

    @GetMapping("/downLoad")
    @ResponseBody
    public String  downLoad(@RequestParam("fileName") String fileName,  HttpServletResponse response) throws Exception {
        Long begin = System.currentTimeMillis()/1000;
        List<User> users = userService.queryUserList();
        ExportExcelUtil.downLoad(fileName, users,User.class,response);
        Long end = System.currentTimeMillis()/1000;
        return "导出"+users.size()+"条数据，花费时间："+(end - begin)+"秒";
    }



    @GetMapping("/insert")
    public void insertUsers(){
        List<User> userList = Lists.newArrayList();
        for (int i = 10000; i <50000 ; i++) {
            User user = User.builder().id(Long.valueOf(i+1)).name("name" + i).age(i + 10).email("email" + i).build();
            userList.add(user);
        }
        userService.insertUsers(userList);

    }

}
