package pa02;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Recur {

	static int[] path;
	static int[] data;
	static double sum = 0;
	static double min_distance;
	static int n;
	static int[] x;
	static int[] y;

	public static void main(String[] args) throws FileNotFoundException {

		// int[] path;
		for (int i = 0; i < 7; i++) {
			long start = System.currentTimeMillis();
			Scanner fileScanner = new Scanner(new File("input" + i + ".txt"));
			n = fileScanner.nextInt();
			x = new int[n];
			y = new int[n];
			path = new int[n];
			data = new int[n];
			min_distance = 987654321;

			for (int j = 0; j < n; j++) {
				x[j] = fileScanner.nextInt();
				y[j] = fileScanner.nextInt();
				data[j] = j;
			}
			Perm(1, 0);
			System.out.println("#input" + i + "\nanswer : " + min_distance);
			System.out.println(Arrays.toString(path));

			long end = System.currentTimeMillis();
			System.out.println("실행 시간 : " + (end - start) / 1000.0 + "\n-----------------------------");
			fileScanner.close();
		}

	}

	static double getDistance(int x, int y, int x1, int y1) {
		return Math.sqrt(Math.pow((x1 - x), 2) + Math.pow((y1 - y), 2));
	}

	static void swap(int index, int change) {
		int tmp = data[index];
		data[index] = data[change];
		data[change] = tmp;

	}

	static void Perm(int s, double cur_dis) {

		if (min_distance < cur_dis)
			// System.out.println("min : " + Arrays.toString(data));
			return;
		else if (s == n) {
			double tmp = cur_dis;
			tmp += getDistance(x[data[0]], y[data[0]], x[data[n - 1]], y[data[n - 1]]);
			// System.out.println("path : " + Arrays.toString(data));
			if (min_distance > tmp) {
				min_distance = tmp;
				System.arraycopy(data, 0, path, 0, n);
			}
			return;
		} else {
			for (int j = s; j < n; j++) {
				double dis = cur_dis;
				swap(s, j);
				dis += getDistance(x[data[s]], y[data[s]], x[data[s - 1]], y[data[s - 1]]);
				Perm(s + 1, dis);
				swap(s, j);
			}
			return;
		}

	}
}
