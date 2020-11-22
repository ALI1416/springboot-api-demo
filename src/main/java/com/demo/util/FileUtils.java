package com.demo.util;

import com.demo.source.EncodingDetect;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <h1>文件工具类</h1>
 *
 * <p>
 * createDate 2020/11/20 15:13:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class FileUtils {

    public static void main(String[] args) {
        System.out.println(resource2String("file/ansj/ambiguity.dic"));
    }

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * 加载资源文件到InputStream
     *
     * @param path 资源文件路径
     * @return 请手动关闭InputStream
     */
    public static InputStream resource2InputStream(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return resource.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载资源文件到File
     *
     * @param path     资源文件路径
     * @param filePath 输出文件路径
     */
    public static void resource2File(String path, String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            // 不能使用resource.getFilename()，如果打包成jar，会报错
            InputStream inputStream = resource.getInputStream();
            inputStream2File(inputStream, filePath);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载资源文件到String
     *
     * @param path 资源文件路径
     */
    public static String resource2String(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream inputStream = resource.getInputStream();
            String string = inputStream2String(inputStream);
            inputStream.close();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载资源文件到bytes
     *
     * @param path 资源文件路径
     */
    public static byte[] resource2Bytes(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream2Bytes(inputStream);
            inputStream.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转InputStream
     *
     * @param filePath 文件路径
     */
    public static InputStream file2InputStream(String filePath) {
        try {
            org.apache.commons.io.FileUtils.openInputStream(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转String
     *
     * @param filePath 文件路径
     */
    public static String file2String(String filePath) {
        try {
            org.apache.commons.io.FileUtils.readFileToString(new File(filePath), UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转bytes
     *
     * @param filePath 文件路径
     */
    public static byte[] file2Bytes(String filePath) {
        try {
            return org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * InputStream转File
     *
     * @param inputStream 请手动关闭InputStream
     * @param filePath    输出文件路径
     */
    public static void inputStream2File(InputStream inputStream, String filePath) {
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * InputStream转String
     *
     * @param inputStream 请手动关闭InputStream
     */
    public static String inputStream2String(InputStream inputStream) {
        try {
            return org.apache.commons.io.IOUtils.toString(inputStream, UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * InputStream转bytes
     *
     * @param inputStream 请手动关闭InputStream
     */
    public static byte[] inputStream2Bytes(InputStream inputStream) {
        try {
            return org.apache.commons.io.IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bytes转InputStream
     *
     * @param bytes bytes
     */
    public static InputStream bytes2InputStream(byte[] bytes) {
        try {
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bytes转File
     *
     * @param bytes    bytes
     * @param filePath 输出文件路径
     */
    public static void bytes2File(byte[] bytes, String filePath) {
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(filePath), bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bytes转String
     *
     * @param bytes bytes
     */
    public static String bytes2String(byte[] bytes) {
        try {
            return new String(bytes, UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String转InputStream
     *
     * @param string 字符串
     */
    public static InputStream string2InputStream(String string) {
        try {
            return new ByteArrayInputStream(string.getBytes(UTF8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String转File
     *
     * @param string   字符串
     * @param filePath 输出文件路径
     */
    public static void string2File(String string, String filePath) {
        try {
            org.apache.commons.io.FileUtils.write(new File(filePath), string, UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String转bytes
     *
     * @param string 字符串
     */
    public static byte[] string2Bytes(String string) {
        try {
            return string.getBytes(UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveAsUTF8LimitSize(String inputFilePath, String outputFilePath, int size) {
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFilePath);
            int fileSize = fileInputStream.available();
            if (fileSize <= size) {
                saveAsUTF8(inputFilePath, outputFilePath);
                fileInputStream.close();
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath + ".temp");
                byte[] b = new byte[size];
                fileInputStream.read(b);
                fileOutputStream.write(b);
                fileOutputStream.flush();
                fileOutputStream.close();
                fileInputStream.close();
                saveAsUTF8(outputFilePath + ".temp", outputFilePath);
                File file = new File(outputFilePath + ".temp");
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveAsUTF8(String inputFilePath, String outputFilePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), getJavaEncode(inputFilePath)));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), UTF8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line + "\r\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJavaEncode(String filePath) {
        EncodingDetect s = new EncodingDetect();
        String fileCode = EncodingDetect.javaname[s.detectEncoding(new File(filePath))];
        return fileCode;
    }
}
