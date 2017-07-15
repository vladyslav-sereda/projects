import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Демонстрирует работу с регулярными выражениями.<br/>
 * Задача: вывести на экран содержимое файла text.txt, поменяв местами два
 * первых слова в каждой строке.
 * 
 * @author Dmitry Kolesnikov
 */
public class RegexpDemo {

	/**
	 * Возвращает строку, которая содержит текст из файла.
	 * 
	 * @param fileName
	 *            имя файла.
	 * @return содержимое файла в виде строки.
	 * @throws Exception
	 *             если не найден файл и т.п. ошибки ввода/вывода.
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
		// загружаем текст
		String s = load("text.txt");
		System.out.println(s);
		System.out.println("~~~~~~~~~~~~~");

		// загружаем регулярное выражение
		String regexp = load("regexp");

		// компилируем рег. выр. и получаем Matcher
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);

		// проходим по всем вариантам, кот. найдет рег. выр.
		while (matcher.find()) {
			// переставляем местами первое и второе слово

			// второе слово
			System.out.print(matcher.group(3));
			// то что между первым и вторым словом
			System.out.print(matcher.group(2));
			// первое слово
			System.out.print(matcher.group(1));

			// хвост (все остальное)
			System.out.println(matcher.group(4));
		}
	}
}