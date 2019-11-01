package cc.readcode.java.concurrency.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author javajiao
 */
public class ThreadLocalConcurrentTest {

    public static final ThreadLocal threadLocalRequest = new ThreadLocal();
        @Override
        protected Connection initialValue() {
            return HiveClient.getConnection();
        }
    };

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,20,20, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100));
        for (int i = 0; i< 10 ;i ++){
            threadPoolExecutor.execute(new Handle(threadLocal));
        }
    }

}




public class Handle implements  Runnable {

    private ThreadLocal<Connection> threadLocal;
    private Connection connection;

    public Handle(ThreadLocal<Connection> threadLocal){
        this.threadLocal = threadLocal;
    }

    public void handle(String sql){
        connection = threadLocal.get();
        try{
            PreparedStatement prepareStatement  =   connection.prepareStatement(sql);
            prepareStatement.execute();
            //处理数据

        }catch (Exception e){

        }finally {
            try {
                connection.close(); //此处不能关闭连接，否则会抛出异常。
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        String sql = "xxxxxx";
        handle(sql);
    }
}
