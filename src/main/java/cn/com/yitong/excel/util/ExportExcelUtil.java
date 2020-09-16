package cn.com.yitong.excel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
public class ExportExcelUtil {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30, 30, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(60), new ThreadPoolExecutor.AbortPolicy());

    public  static <T> void    export(String fileNamePrefix,  List<T>  data, Class<T> clazz){
        String exportFileName = FileUtil.getPath() + fileNamePrefix  + "_" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(exportFileName, clazz).sheet("sheet1").doWrite(data);
    }

    public static <T> void downLoad(String fileName ,List<T>  data, Class<T> clazz, HttpServletResponse response) throws Exception{
        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet("test").doWrite(data);
    }

    private static  OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8")+"_"+System.currentTimeMillis();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }





}
