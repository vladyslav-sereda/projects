import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Demonstrates how to load the content of a file into a string variable.
 * 
 * @author Dmytry Kolesnikov
 */
public class Demo1 {
	
	private static final String FILE_NAME = "demo1.txt";

	private static final String EOL = System.lineSeparator();
	
	private static final String ENCODING = "Cp1251";
	
	public static void main(String[] args) throws FileNotFoundException {
		String content = null;
		StringBuilder sb = new StringBuilder();
		Scanner s = new Scanner(new File(FILE_NAME), ENCODING);
		while (s.hasNextLine()) {
			sb.append(s.nextLine()).append(EOL);
		}
		content = sb.substring(0, sb.length() - EOL.length());
		s.close();
		System.out.println(content);
	}

}
