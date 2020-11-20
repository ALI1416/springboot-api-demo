package com.demo.util;

import com.demo.source.EncodingDetect;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * <h1>文件工具类</h1>
 *
 * <p>
 * createDate 2020/11/20 15:13:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
public class FileUtils {

    public static void main(String argc[]) {
        //		try {
        ////			FileUtils.saveAsUTF8("D:/7.txt", "D:/11.txt");
        //			FileUtils.saveAsUTF8LimitSize("D:/7.txt", "D:/11.txt", 100);
        //		} catch (Exception e) {
        //			e.printStackTrace();
        //		}
        System.out.println(loadResourceFile2String("file/ansj/ambiguity.dic"));
    }

    /**
     * 加载资源文件到InputStream
     *
     * @param path 资源文件路径
     */
    public static InputStream loadResourceFile2InputStream(String path) {
        try {
            ClassPathResource resourceFile = new ClassPathResource(path);
            return resourceFile.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加载资源文件到File
     *
     * @param path     资源文件路径
     * @param filePath 输出文件路径
     */
    public static void loadResourceFile2File(String path, String filePath) {
        try {
            ClassPathResource resourceFile = new ClassPathResource(path);
            InputStream inputStream = resourceFile.getInputStream();
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
    public static String loadResourceFile2String(String path) {
        try {
            ClassPathResource resourceFile = new ClassPathResource(path);
            InputStream inputStream = resourceFile.getInputStream();
            String str = inputStream2String(inputStream);
            inputStream.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * InputStream转String
     *
     * @param inputStream inputStream
     */
    public static String inputStream2String(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String str = new String(bytes);
            inputStream.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * InputStream转File
     *
     * @param inputStream         inputStream
     * @param destinationFilePath 文件路径
     */
    public static void inputStream2File(InputStream inputStream, String destinationFilePath) {
        try {
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream destinationFile;
            destinationFile = new FileOutputStream(destinationFilePath);
            while ((index = inputStream.read(bytes)) != -1) {
                destinationFile.write(bytes, 0, index);
                destinationFile.flush();
            }
            destinationFile.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * bytes转File
     *
     * @param bytes               bytes
     * @param destinationFilePath 文件路径
     */
    public static void bytes2File(byte[] bytes, String destinationFilePath) {
        try {
            FileOutputStream destinationFile = new FileOutputStream(destinationFilePath);
            destinationFile.write(bytes);
            destinationFile.flush();
            destinationFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static byte[] inputStream2Bytes(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (true) {
            try {
                if (!(-1 != (n = input.read(buffer))))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
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
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8"));
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
