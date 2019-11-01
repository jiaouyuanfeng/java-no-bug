package cc.readcode.java.concurrency.servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "pwServlet", urlPatterns = "/pwServlet")
public class PrintWriterConcurrentTest extends HttpServlet {
    /**
     * 定义为实例变量，并发情况下会串值
     * 可以是用 test/resources.servlet下jmeter脚本进行性能测试
     */
    PrintWriter wrt;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");
        String username = request.getParameter("username");
        int k = 1;
        wrt = response.getWriter();
        try {
            JSONObject jsObject = new JSONObject();
            jsObject.put("url", "http://www." + username + ".com");
            jsObject.put("message", "出错：");
            jsObject.put("datetime", System.currentTimeMillis());


//            Thread.sleep(1000);
            for(int i =0; i < 1000000000; i++) {
                k++;
            }
            jsObject.put("returnCode", k);
            wrt.write(jsObject.toString());
            wrt.flush();
            if (wrt != null) {
                wrt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}