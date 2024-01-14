import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encryption {
    public static void main(String[] args) {
        System.out.println(encryption("vdfvdv"));
    }

    public static String encryption(String input){
        StringBuilder hexString = new StringBuilder();
        try {
            // 创建SHA-256哈希对象
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将字符串转换为字节数组
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 将字节数组转换为十六进制字符串
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // 输出SHA-256加密后的字符串
            System.out.println("SHA-256加密结果：" + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(hexString);
    }
}
