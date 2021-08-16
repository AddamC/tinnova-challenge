public class App {
    public static void main(String[] args) throws Exception {
        int[] v = {5, 3, 2, 4, 7, 1, 0, 6};
        BubbleSort.sort(v);
        
        System.out.print("Ordem final do arranjo: [");
        for (int i = 0; i < v.length; i++) {
            if (i > 0) {
            	System.out.print(", ");
            }
        	System.out.print(v[i]);
        }
        System.out.print("]");
    }
}
