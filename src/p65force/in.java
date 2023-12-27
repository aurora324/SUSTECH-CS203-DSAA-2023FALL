package p65force;

import java.util.Random;

public class in {
    public static void main(String[] args) {
        Random yzm = new Random();                          //定义一个随机生成数技术，用来生成随机数
//2，用String常用API-charAit生成验证码
        String yzm1 = "1234567890abcdefghijklmnopqrstuvwxwzABCDEFGHIJKLMNOPQRSTUVWXYZ,./;'[]()";//定义一个String变量存放需要的数据，一共58位
        StringBuilder yzm2 = new StringBuilder();
        StringBuilder yzm3 = new StringBuilder();
        for (int i = 0; i < yzm.nextInt(100000); i++) {
            int a = yzm.nextInt(67);//随机生成0-57之间的数，提供索引位置
            yzm2.append(yzm1.charAt(a));//用get 和提供的索引找到相应位置的数据给变量
        }
        for (int i = 0; i < yzm.nextInt(100000); i++) {
            int a = yzm.nextInt(67);//随机生成0-57之间的数，提供索引位置
            yzm3.append(yzm1.charAt(a));//用get 和提供的索引找到相应位置的数据给变量
        }
        System.out.println(yzm2);
        System.out.println(yzm3);
    }
}
