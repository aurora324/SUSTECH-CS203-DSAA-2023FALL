public class lab4b {
    public static void main(String[] args) {
        int n=50;
        int p=30;
        int q=40;
        int []arr=new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }

        int length = arr.length;
        for(int i=0;i<length;i++){
            int iRandNum = (int)(Math.random() * length);
            int temp = arr[iRandNum];
            arr[iRandNum] = arr[i];
            arr[i] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }


    }
}
