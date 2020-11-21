package com.demo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.property.yml.AnsjDefaultYml;
import com.demo.property.yml.Ip2RegionDefaultYml;
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
    public static Ip2RegionDefaultYml ip2RegionDefaultYml;

    @Autowired
    private Yml(MyYml myYml, MailDefaultYml mailDefaultYml, AnsjDefaultYml ansjDefaultYml, Ip2RegionDefaultYml ip2RegionDefaultYml) {
        Yml.myYml = myYml;
        Yml.mailDefaultYml = mailDefaultYml;
        Yml.ansjDefaultYml = ansjDefaultYml;
        Yml.ip2RegionDefaultYml = ip2RegionDefaultYml;
    }
}
