public class DigitLogic {
    public static void main(String[] args) {
        String s = "115566544332215544332554433211556654433221";
//        System.out.println(Integer.toBinaryString(Integer.parseInt(s.charAt(0)+"")));
        for (int i = 0; i < s.length(); i++) {
            String a = null;
            switch (s.charAt(i)) {
                case '0' -> a = "00000";
                case '1' -> a = "00001";
                case '2' -> a = "00010";
                case '3' -> a = "00011";
                case '4' -> a = "00100";
                case '5' -> a = "00101";
                case '6' -> a = "00110";
                case '7' -> a = "00111";
            }
            System.out.println("assign song4[" + i + "]=5'b" + a+";");
        }
    }
}