package com.demo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.property.yml.AnsjDefaultYml;
import com.demo.property.yml.MailDefaultYml;

/**
 * <h1>Yml总配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@Component
public class Yml {

    public static MyYml myYml;
    public static MailDefaultYml mailDefaultYml;
    public static AnsjDefaultYml ansjDefaultYml;

    @Autowired
    private Yml(MyYml myYml, MailDefaultYml mailDefaultYml, AnsjDefaultYml ansjDefaultYml) {
        Yml.myYml = myYml;
        Yml.mailDefaultYml = mailDefaultYml;
        Yml.ansjDefaultYml = ansjDefaultYml;
    }
}
