import java.util.Random;

public class in {
    public static void main(String[] args) {
        int []arr=new int[100];
        Random r=new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }
        for (int i = 0; i < arr.length; i++) {
            int random=r.nextInt(i,arr.length);
            int temp=arr[i];
            arr[i]=arr[random];
            arr[random]=temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
