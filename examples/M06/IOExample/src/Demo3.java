import java.io.IOException;
import java.util.Scanner;

/**
 * Demonstrates how to read from the console until the "stop" word will be entered.
 * 
 * @author Dmytry Kolesnikov
 */
public class Demo3 {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		String line;
		while (s.hasNextLine() && !"stop".equals(line = s.nextLine())) {
			System.out.println(line);
		}

		// WARNING!!! If you close the scanner, underlying System.in stream also
		// will be closed, so don't do this:
		// s.close();
	}

}
