public abstract class BubbleSort {

	public static void sort(int[] array) {
		for (int lastArrayIndex = array.length - 1; lastArrayIndex > 0; lastArrayIndex--) {
            for (int i = 0; i < lastArrayIndex; i++) {
                if (array[i+1] > array[i]) {
                    swap(array, i, i + 1);
                }
            }
        }
	}

	// Método para realizar a troca de posição dos valores
	private static void swap(int[] array, int pos1, int pos2) {

		if (pos1 == pos2) {
			return;
		}

		int tmp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = tmp;
	}
}