package com.demo.util;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

/**
 * 读取properties文件和yml文件工具类
 * 
 * @author ALI
 *
 */
public class ReadConfigUtils {

    public static void main(String[] s) {
        Properties p = ReadConfigUtils.getProperties("library.properties");
        Map<String, Object> y0 = ReadConfigUtils.getYaml("application.yml");
        Map<String, Object> y = (y0.get("spring.profiles.active") == null) ? y0
                : ReadConfigUtils.getYaml("application-" + y0.get("spring.profiles.active") + ".yml");
        System.out.println(p);
        System.out.println(y);
    }

    /**
     * 获取properties文件
     * 
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
        try {
            InputStream inputStream = FileUtils.resource2InputStream(fileName);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取yml文件
     * 
     * @param fileName
     * @return
     */
    public static Map<String, Object> getYaml(String fileName) {
        try {
            InputStream inputStream = FileUtils.resource2InputStream(fileName);
            Yaml yaml = new Yaml();
            Map<String, Object> source = yaml.load(inputStream);
            inputStream.close();
            System.out.println(source);
            Map<String, Object> result = new LinkedHashMap<>();
            buildFlattenedMap(result, source, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * yml样式转properties样式
     * 
     * @param result
     * @param source
     * @param path
     */
    @SuppressWarnings("unchecked")
    public static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String path) {
        source.forEach((key, value) -> {
            if (StringUtils.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection) {
                Collection<Object> collection = (Collection<Object>) value;
                if (collection.isEmpty()) {
                    result.put(key, "");
                } else {
                    int count = 0;
                    for (Object object : collection) {
                        buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), key);
                    }
                }
            } else {
                result.put(key, (value != null ? value : ""));
            }
        });
    }

}
