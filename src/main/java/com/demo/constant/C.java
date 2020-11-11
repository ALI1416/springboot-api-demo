package com.demo.constant;

import com.demo.properties.MailProperty;
import com.demo.properties.MyProperty;
import com.demo.properties.P;

public class C {
	private static final MyProperty myProperty = P.myProperty;
	private static final MailProperty mailProperty = P.mailProperty;

	public static final int A = myProperty.getIntType();
	public static final String B = myProperty.getStringType();
	public static final String C = mailProperty.getUsername();
}
