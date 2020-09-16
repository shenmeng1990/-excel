package cn.com.yitong.excel.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author shenmeng
 * @Date 2020/9/16
 **/
public class ResponseUtils {

    public static HttpServletResponse exportFile(HttpServletResponse response, String bookName, HttpServletRequest request) throws IOException {
        response.setContentType("application/force-download");
        /*
        告诉浏览器用什么软件可以打开此文件
         */
        response.setHeader("content-Type", "application/vnd.ms-excel");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            /*
            firefox浏览器
             */
            bookName = new String(bookName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0 ||
                request.getHeader("User-Agent").toLowerCase().contains("like gecko") ||
                request.getHeader("User-Agent").toUpperCase().indexOf("EDGE") > 0) {
            bookName = URLEncoder.encode(bookName, "UTF-8");
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
            /*
            谷歌
             */
            bookName = new String(bookName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        } else {
            /*
            firefox浏览器
             */
            bookName = new String(bookName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + bookName + ".xlsx");
        return response;
    }

}
