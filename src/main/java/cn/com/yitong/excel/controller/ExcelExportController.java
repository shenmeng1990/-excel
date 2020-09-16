package cn.com.yitong.excel.controller;

import cn.com.yitong.excel.data.DemoData;
import cn.com.yitong.excel.util.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
@RestController
public class ExcelExportController {

    @RequestMapping("/export1")
    @ResponseBody
    public String export1(){
        Long begin = System.currentTimeMillis()/1000;
        // 写法1
        String fileName = FileUtil.getPath() + "test" + System.currentTimeMillis() + ".xlsx";
        System.out.println(fileName);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
        Long end = System.currentTimeMillis()/1000;
        return "导出"+data().size()+"条数据，花费时间："+(end - begin)+"秒";
    }

    @RequestMapping("/export2")
    @ResponseBody
    public String export2(){
        Long begin = System.currentTimeMillis()/1000;
        String fileName = FileUtil.getPath() + "test" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        Long end = System.currentTimeMillis()/1000;
        return "导出"+data().size()+"条数据，花费时间："+(end - begin)+"秒";
    }

    @GetMapping("/syncDownLoad")
    public void syncDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ServletOutputStream out = response.getOutputStream();
        String fileName = FileUtil.getPath() + "test" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
        out.flush();
    }



    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 100000; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

}
