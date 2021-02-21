package com.demo.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <h1>文件工具</h1>
 *
 * <p>
 * createDate 2020/11/20 15:13:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class FileUtils {

    /**
     * UTF8字符集编码
     */
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        // resource2String
        System.out.println("resource2String");
        System.out.println(resource2String("banner.txt"));
        // resource2Bytes
        // bytes2String
        System.out.println("resource2Bytes-bytes2String");
        System.out.println(bytes2String(resource2Bytes("banner.txt")));
        // resource2InputStream
        // inputStream2String
        System.out.println("resource2InputStream-inputStream2String");
        System.out.println(inputStream2String(resource2InputStream("banner.txt")));
        // resource2File
        // file2String
        System.out.println("resource2File-file2String");
        String filePath = "D:/temp/banner.txt";
        resource2File("banner.txt", filePath);
        System.out.println(file2String(filePath));

        String string = "string";
        // string2Bytes
        // bytes2InputStream
        // inputStream2String
        System.out.println("string2Bytes-bytes2InputStream-inputStream2String");
        System.out.println(inputStream2String(bytes2InputStream(string2Bytes(string))));
        // string2InputStream
        // inputStream2String
        System.out.println("string2InputStream-inputStream2String");
        System.out.println(inputStream2String(string2InputStream(string)));
        // string2File
        // file2String
        System.out.println("string2File-file2String");
        String filePath2 = "D:/temp/banner2.txt";
        string2File(string, filePath2);
        System.out.println(file2String(filePath2));

        byte[] bytes = string.getBytes();
        // bytes2String
        System.out.println("bytes2String");
        System.out.println(bytes2String(bytes));
        // bytes2InputStream
        // inputStream2String
        System.out.println("bytes2InputStream-inputStream2String");
        System.out.println(inputStream2String(bytes2InputStream(bytes)));
        // bytes2File
        // file2String
        System.out.println("bytes2File-file2String");
        String filePath3 = "D:/temp/banner3.txt";
        bytes2File(bytes, filePath3);
        System.out.println(file2String(filePath3));

        InputStream inputStream;
        // string2InputStream
        // inputStream2String
        System.out.println("string2InputStream-inputStream2String");
        inputStream = string2InputStream(string);
        System.out.println(inputStream2String(inputStream));
        // string2InputStream
        // inputStream2Bytes
        // bytes2String
        System.out.println("string2InputStream-inputStream2Bytes-bytes2String");
        inputStream = string2InputStream(string);
        System.out.println(bytes2String(inputStream2Bytes(inputStream)));
        // string2InputStream
        // inputStream2File
        // file2String
        System.out.println("string2InputStream-inputStream2File-file2String");
        inputStream = string2InputStream(string);
        String filePath4 = "D:/temp/banner4.txt";
        inputStream2File(inputStream, filePath4);
        System.out.println(file2String(filePath4));

        // file2InputStream
        // inputStream2String
        System.out.println("file2InputStream-inputStream2String");
        System.out.println(inputStream2String(file2InputStream(filePath4)));
        // file2Bytes
        // bytes2String
        System.out.println("file2Bytes-bytes2String");
        System.out.println(bytes2String(file2Bytes(filePath4)));
        // file2String
        System.out.println("file2String");
        System.out.println(file2String(filePath4));
    }

    /**
     * 加载资源文件到InputStream
     *
     * @param path 资源文件路径
     * @see org.springframework.core.io.ClassPathResource#getInputStream()
     */
    public static InputStream resource2InputStream(String path) {
        try {
            return new ClassPathResource(path).getInputStream();
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
     * @see org.springframework.core.io.ClassPathResource#getInputStream()
     * @see #inputStream2File(InputStream, String)
     */
    public static void resource2File(String path, String filePath) {
        try {
            // 不能使用new ClassPathResource(path).getFilename()，如果打包成jar，会报错
            inputStream2File(new ClassPathResource(path).getInputStream(), filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载资源文件到String
     *
     * @param path 资源文件路径
     * @see org.springframework.core.io.ClassPathResource#getInputStream()
     * @see #inputStream2String(InputStream)
     */
    public static String resource2String(String path) {
        try {
            return inputStream2String(new ClassPathResource(path).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载资源文件到bytes
     *
     * @param path 资源文件路径
     * @see org.springframework.core.io.ClassPathResource#getInputStream()
     * @see #inputStream2Bytes(InputStream)
     */
    public static byte[] resource2Bytes(String path) {
        try {
            return inputStream2Bytes(new ClassPathResource(path).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转InputStream
     *
     * @param filePath 文件路径
     * @see org.apache.commons.io.FileUtils#openInputStream(File file)
     */
    public static InputStream file2InputStream(String filePath) {
        try {
            return org.apache.commons.io.FileUtils.openInputStream(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转String
     *
     * @param filePath 文件路径
     * @see org.apache.commons.io.FileUtils#readFileToString(File file, Charset
     * charsetName)
     */
    public static String file2String(String filePath) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(new File(filePath), UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * File转bytes
     *
     * @param filePath 文件路径
     * @see org.apache.commons.io.FileUtils#readFileToByteArray(File file)
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
     * @param inputStream 会被关闭
     * @param filePath    输出文件路径
     * @see org.apache.commons.io.FileUtils#copyInputStreamToFile(InputStream
     * source, File destination)
     */
    public static void inputStream2File(InputStream inputStream, String filePath) {
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * InputStream转String
     *
     * @param inputStream 会被关闭
     * @see org.apache.commons.io.IOUtils#toString(InputStream input, Charset
     * charset)
     */
    public static String inputStream2String(InputStream inputStream) {
        try {
            return org.apache.commons.io.IOUtils.toString(inputStream, UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * InputStream转bytes
     *
     * @param inputStream 会被关闭
     * @see org.apache.commons.io.IOUtils#toByteArray(InputStream input)
     */
    public static byte[] inputStream2Bytes(InputStream inputStream) {
        try {
            return org.apache.commons.io.IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * bytes转InputStream
     *
     * @param bytes bytes
     * @see java.io.ByteArrayInputStream#ByteArrayInputStream(byte[] buf)
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
     * @see org.apache.commons.io.FileUtils#writeByteArrayToFile(File file, byte[]
     * data)
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
     * @see java.lang.String#String(byte[] bytes, Charset charset)
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
     * @see java.io.ByteArrayInputStream#ByteArrayInputStream(byte[] buf)
     * @see java.lang.String#getBytes(Charset charset)
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
     * @see org.apache.commons.io.FileUtils#write(File file, CharSequence data,
     * Charset charset)
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
     * @see java.lang.String#getBytes(Charset charset)
     */
    public static byte[] string2Bytes(String string) {
        try {
            return string.getBytes(UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void saveAsUTF8LimitSize(String inputFilePath, String outputFilePath, int size) {
    //     try {
    //         FileInputStream fileInputStream = new FileInputStream(inputFilePath);
    //         int fileSize = fileInputStream.available();
    //         if (fileSize <= size) {
    //             saveAsUTF8(inputFilePath, outputFilePath);
    //             fileInputStream.close();
    //         } else {
    //             FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath + ".temp");
    //             byte[] b = new byte[size];
    //             fileInputStream.read(b);
    //             fileOutputStream.write(b);
    //             fileOutputStream.flush();
    //             fileOutputStream.close();
    //             fileInputStream.close();
    //             saveAsUTF8(outputFilePath + ".temp", outputFilePath);
    //             File file = new File(outputFilePath + ".temp");
    //             file.delete();
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void saveAsUTF8(String inputFilePath, String outputFilePath) {
    //     try {
    //         BufferedReader bufferedReader =
    //                 new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath),
    //                         getJavaEncode(inputFilePath)));
    //         BufferedWriter bufferedWriter =
    //                 new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), UTF8));
    //         String line;
    //         while ((line = bufferedReader.readLine()) != null) {
    //             bufferedWriter.write(line + "\r\n");
    //         }
    //         bufferedWriter.flush();
    //         bufferedWriter.close();
    //         bufferedReader.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static String getJavaEncode(String filePath) {
    //     EncodingDetect s = new EncodingDetect();
    //     return EncodingDetect.javaname[s.detectEncoding(new File(filePath))];
    // }
}
