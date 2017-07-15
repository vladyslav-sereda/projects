/**
 * Класс для демонстрации скорости работы классов String, StringBuilder,
 * StringBuffer в контексте многократного конкатенирования.
 * 
 * @author Dmitry Kolesnikov
 */
public class StringSpeedDemo {

	/**
	 * Строка, которую будет добавлять.
	 */
	private static String str = "abcd";

	/**
	 * Количество итераций конкатенаций
	 */
	private static int count;

	/**
	 * Конкатенирует String с помощью операции +
	 */
	public static void testString() {
		long before = System.currentTimeMillis();
		String s = "";
		for (int j = 0; j < count; j++)
			s += str;
		long after = System.currentTimeMillis();
		System.out.println(after - before);
	}

	/**
	 * Конкатенирует StringBuilder с помощью append
	 */
	public static void testStringBuilder() {
		long before = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < count; j++)
			sb.append(str);
		long after = System.currentTimeMillis();
		System.out.println(after - before);
	}

	/**
	 * Конкатенирует StringBuffer с помощью append
	 */
	public static void testStringBuffer() {
		long before = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < count; j++)
			sb.append(str);
		long after = System.currentTimeMillis();
		System.out.println(after - before);
	}

	public static void main(String[] args) {
		count = 10_000;
		testStringBuilder();
		testStringBuffer();
		testString();

		System.out.println("~~~~~~~~~~~~~");
		// для String не подсчитываем, т.к. долго
		count = 1_000_000;
		testStringBuilder();
		testStringBuffer();
	}
}
