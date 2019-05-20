package cn.nuist_bjxy.zhangzheng.encryption;

import java.security.MessageDigest;

/**
 * @author ZhengZheng
 * @description 为密码加密存储
 * @date 下午2:45:28
 */

public class Encryption {
	// 16进制下数字到字符的映射数组
    private static String[] hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // 将input_str加密的方法 ，返回加密后的数据
    public  String createPassword(String input_str) {
        return encodeByMD5(input_str); 
    }

    // 验证密码是否正确
    public  boolean authenticatePassword(String pass, String input_str) {
        if (pass.equals((encodeByMD5(input_str)))) {
            return true;
        } else {
            return false;
        }
    }

    // 对字符串进行MD5编码加密
    private  static String encodeByMD5(String originstr) {
        if (originstr != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
                byte[] results = md.digest(originstr.getBytes());
                // 将得到的字节数组转为字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    // 转换字节数组为十六进制字符串
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultsb = new StringBuffer();
        int i = 0;
        for (i = 0; i < b.length; i++) {
            resultsb.append(byteToHexString(b[i]));
        }
        return resultsb.toString();
    }

    // 将字节转化成十六进制的字符串
    private static  String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n / 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
