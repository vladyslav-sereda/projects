import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������������� ������ � ����������� �����������.<br/>
 * ������: ������� �� ����� ���������� ����� text.txt, ������� ������� ���
 * ������ ����� � ������ ������.
 * 
 * @author Dmitry Kolesnikov
 */
public class RegexpDemo {

	/**
	 * ���������� ������, ������� �������� ����� �� �����.
	 * 
	 * @param fileName
	 *            ��� �����.
	 * @return ���������� ����� � ���� ������.
	 * @throws Exception
	 *             ���� �� ������ ���� � �.�. ������ �����/������.
	 */
	private static String load(String fileName) throws Exception {
		Scanner scanner = new Scanner(new File(fileName));
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNextLine())
			sb.append(scanner.nextLine()).append("\n");
		scanner.close();
		return sb.toString().trim();
	}

	public static void main(String[] args) throws Exception {
		// ��������� �����
		String s = load("text.txt");
		System.out.println(s);
		System.out.println("~~~~~~~~~~~~~");

		// ��������� ���������� ���������
		String regexp = load("regexp");

		// ����������� ���. ���. � �������� Matcher
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);

		// �������� �� ���� ���������, ���. ������ ���. ���.
		while (matcher.find()) {
			// ������������ ������� ������ � ������ �����

			// ������ �����
			System.out.print(matcher.group(3));
			// �� ��� ����� ������ � ������ ������
			System.out.print(matcher.group(2));
			// ������ �����
			System.out.print(matcher.group(1));

			// ����� (��� ���������)
			System.out.println(matcher.group(4));
		}
	}
}