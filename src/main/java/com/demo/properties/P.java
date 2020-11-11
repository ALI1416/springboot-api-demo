package com.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class P {
    public static MyProperty myProperty;
    public static MailProperty mailProperty;

    @Autowired
    private P(MyProperty myProperty, MailProperty mailProperty) {
        P.myProperty = myProperty;
        P.mailProperty = mailProperty;
    }
}
