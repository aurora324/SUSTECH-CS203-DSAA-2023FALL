import java.util.ArrayList;
import java.util.Scanner;

public class BonusC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 256; j++) {
                s.append(scanner.nextInt());
            }
            System.out.println(query(s.toString(), arrayList, k) ? "hit" : "miss");
        }
    }
    public static boolean query(String s, ArrayList<String> arrayList, int k) {
        boolean find = false;
        int i;
        for (i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(s)) {
                find = true;
                break;
            }
        }
        if (find) {
            String string = arrayList.remove(i);
            arrayList.add(string);
            return true;
        } else {
            if (k == 0) {
                return false;
            }
            if (arrayList.size() >= k) {
                arrayList.remove(0);
            }
            arrayList.add(s);
            return false;
        }
    }
}