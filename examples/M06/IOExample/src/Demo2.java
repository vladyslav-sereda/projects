import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Demonstrates how to write/read numbers to/from a file.
 * 
 * @author Dmytry Kolesnikov
 */
public class Demo2 {

	private static final String FILE_NAME = "demo2.txt";

	public static void main(String[] args) throws IOException {
		// IF NUMBERS ARE OF THE RANGE [0..255]
		// in this case you can use File(Input|Output)Stream classes

		// write
		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		fos.write(1);
		fos.write(22);
		fos.write(255);
		fos.close();

		// read
		FileInputStream fis = new FileInputStream(FILE_NAME);
		int x;
		while ((x = fis.read()) != -1) {
			System.out.print(x + " ");
		}
		System.out.println();
		fis.close();
		System.out.println("~~~~~~~~~~~~~~~~~~~");

		// ========================================

		// IF NUMBERS ARE OF THE int RANGE
		// [Integer.MIN_VALUE..Integer.MAX_VALUE]
		// in this case you can use Data(Input|Output)Stream classes

		// write
		DataOutputStream dos = new DataOutputStream(
				new FileOutputStream(FILE_NAME));
		dos.writeInt(-22);
		dos.writeInt(333);
		dos.writeInt(4444);
		// it is not necessary to close underlying FileOutputStream!
		// see the contract of the DataOutputStream#close method
		dos.close();

		// read
		DataInputStream dis = new DataInputStream(
				new FileInputStream(FILE_NAME));
		while (dis.available() > 0) {
			System.out.print(dis.readInt() + " ");
		}
		System.out.println();
		dis.close();
		System.out.println("~~~~~~~~~~~~~~~~~~~");

		// ========================================

		// IF NUMBERS ARE OF THE int RANGE
		// [Integer.MIN_VALUE..Integer.MAX_VALUE]
		// you can use FileWriter to write
		// and Scanner to read

		// write
		FileWriter fw = new FileWriter(FILE_NAME);
		fw.write(String.valueOf(-22));
		fw.write(' ');
		fw.write(String.valueOf(333));
		fw.write(' ');
		fw.write(String.valueOf(4444));
		fw.close();

		// read
		Scanner s = new Scanner(new File(FILE_NAME));
		while (s.hasNextInt()) {
			System.out.print(s.nextInt() + " ");
		}
		s.close();
	}
}
