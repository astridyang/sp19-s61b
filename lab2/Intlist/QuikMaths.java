public class QuikMaths {
    public static void mulitplyBy3(int[] A){
        for(int x : A){
            x = x * 3;
        }
    }
    public static void multiplyBy2(int[] A){
        int B[] = A;
        for(int i = 0; i<B.length; i++){
            B[i] *= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2,3,3,4};
        mulitplyBy3(arr);
        System.out.println(arr[0]);
        arr = new int[]{2,3,3,4};
        multiplyBy2(arr);
        System.out.println(arr[0]);
    }
}
