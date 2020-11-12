package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tool.Log;

/**
 * <h1>日志测试api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
@RequestMapping("/log")
@RestController
public class LoggerController {

	@GetMapping("")
	public String index(String msg) {
		Thread test1 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Log.i("aaaaaaaaaaaa", msg + "111111111");
			}
		}, "Thread1");
		Thread test2 = new Thread(new Runnable() {
			public void run() {
				Log.e(msg + "2222222222");
			}
		}, "Thread2");
		test1.start();
		test2.start();
		return msg;
	}

	@GetMapping("/t")
	public String t(String msg) {
		Log.t(msg);
		return msg;
	}

	@GetMapping("/d")
	public String d(String msg) {
		Log.d(msg);
		return msg;
	}

	@GetMapping("/i")
	public String i(String msg) {
		Log.i(msg);
		return msg;
	}

	@GetMapping("/w")
	public String w(String msg) {
		Log.w(msg);
		return msg;
	}

	@GetMapping("/e")
	public String e(String msg) {
		Log.e("aaaaaa", msg);
		return msg;
	}
}
