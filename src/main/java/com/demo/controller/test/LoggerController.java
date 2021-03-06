package com.demo.controller.test;

import com.demo.tool.Log;
import com.demo.tool.ThreadPool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>日志api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/log")
@RestController
public class LoggerController {

    @GetMapping("")
    public String index(String msg) {
        Runnable test1 = () -> {
            Log.i("aaaaaaaaaaaa", msg + "111111111");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("bbbbbbbbbbbb", msg + "1");
        };
        Runnable test2 = () -> Log.e(msg + "2222222222");
        ThreadPool.execute(test1);
        ThreadPool.execute(test1);
        ThreadPool.execute(test1);
        ThreadPool.execute(test1);
        ThreadPool.execute(test1);
        ThreadPool.execute(test1);
        ThreadPool.execute(test2);
        ThreadPool.execute(test2);
        ThreadPool.execute(test2);
        ThreadPool.execute(test2);
        ThreadPool.execute(test2);
        ThreadPool.execute(test2);
        return msg;
    }

    @GetMapping("/t")
    public String trace(String msg) {
        Log.t(msg);
        return msg;
    }

    @GetMapping("/d")
    public String debug(String msg) {
        Log.d(msg);
        return msg;
    }

    @GetMapping("/i")
    public String info(String msg) {
        Log.i(msg);
        return msg;
    }

    @GetMapping("/w")
    public String warning(String msg) {
        Log.w(msg);
        return msg;
    }

    @GetMapping("/e")
    public String error(String msg) {
        Log.e("eee", msg);
        return msg;
    }
}
