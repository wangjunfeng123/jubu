package com.ninep.jubu.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建目录
     *
     * @param filePath 文件路径
     */
    public static void createDir(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return;
        } else {
            file.mkdirs();
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     */
    public static void deleteDir(File dir) {

        if (!dir.exists()) {
            return;
        }

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                deleteDir(file);
            }
        }
        // 目录此时为空，可以删除
        dir.delete();
    }

    /**
     * 根据文件路径创建文件
     */
    public static File creatFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            File fileParent = new File(file.getParent());
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 获取文件的MD5
     *
     * @param filePath 需要提取Md5的文件
     * @return 正常情况下返回md5 全大写 获取失败返回null
     */
    public static String getMd5ByFile(String filePath) {
        String md5 = null;
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            md5 = bigInt.toString(16);
//            System.out.println("文件md5值：" + bigInt.toString(16));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return md5.toUpperCase();
    }

    /**
     * 获取文件二进制
     *
     * @param filePath 文件路径
     * @return byte[]
     */
    public static byte[] getFileByte(String filePath) {
        byte[] buffer = null;
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            logger.error("" + e);
        } catch (IOException e1) {
            logger.error("" + e1);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

}
