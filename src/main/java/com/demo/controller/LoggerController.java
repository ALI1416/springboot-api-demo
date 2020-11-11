package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.util.L;

@RequestMapping("/l")
@RestController
public class LoggerController {

	@GetMapping("/t")
	public String t(String msg) {
		L.t(msg);
		return msg;
	}

	@GetMapping("/d")
	public String d(String msg) {
		L.d(msg);
		return msg;
	}

	@GetMapping("/i")
	public String i(String msg) {
		L.i(msg);
		return msg;
	}

	@GetMapping("/w")
	public String w(String msg) {
		L.w("bbb", msg);
		return msg;
	}

	@GetMapping("/e")
	public String e(String msg) {
		L.e("aaaaaa", msg);
		return msg;
	}
}
