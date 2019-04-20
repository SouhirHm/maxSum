package maxsum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class findMaxSum {

	static int maxPathSum(List<int[]> list, int[][] mat) {
		for (int i = list.size() - 2; i >= 0; i--) {
			for (int j = 0; j < list.get(i).length; j++) {
				if (isPrime(list.get(i)[j]))
					continue;
				if (j == 0) {

					list.get(i)[j] += max(list, mat, i + 1, j, 2);

				} else {
					list.get(i)[j] += max(list, mat, i + 1, j-1, 3);
				}
			}
		}

		return list.get(0)[0];
	}

	public static boolean isPrime(int a) {
		BigInteger b = BigInteger.valueOf(a);
		return b.isProbablePrime(100);
	}

	public static int max(List<int[]> list, int[][] mat, int i, int j, int size) {
		int max = list.get(i)[j];
		for (int k = j; k < j + size; k++) {
			if (list.get(i)[k] > max) {
				if (!isPrime(mat[i][k]))
					max = list.get(i)[k];
			}
		}
		if (isPrime(max) && isPrime(mat[i][j]))
			return 0;
		return max;

	}

	/* Driver program to test above functions */
	public static void main(String[] args) {
		ArrayList<int[]> list = new ArrayList<>();

		try {

			InputStream inputStream = new FileInputStream("test.txt");

			Scanner scanner = new Scanner(inputStream);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] numbers = line.split("\\s+");
				int[] a = new int[numbers.length];
				for (int i = 0; i < numbers.length; i++) {
					a[i] = Integer.parseInt(numbers[i]);
				}
				list.add(a);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			java.lang.System.exit(1);
		} catch (IOException e) {
			System.out.println("File input/output problem occurred!");
			java.lang.System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please check your input file or row and column sizes!");
			java.lang.System.exit(1);
		}
		int[][] mat = new int[list.size()][list.size()];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j <= i; j++) {
				mat[i][j] = list.get(i)[j];
			}
		}

		System.out.println(maxPathSum(list, mat));
	}

}