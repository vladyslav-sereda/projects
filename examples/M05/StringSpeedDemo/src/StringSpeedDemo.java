/**
 * ����� ��� ������������ �������� ������ ������� String, StringBuilder,
 * StringBuffer � ��������� ������������� ����������������.
 * 
 * @author Dmitry Kolesnikov
 */
public class StringSpeedDemo {

	/**
	 * ������, ������� ����� ���������.
	 */
	private static String str = "abcd";

	/**
	 * ���������� �������� ������������
	 */
	private static int count;

	/**
	 * ������������� String � ������� �������� +
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
	 * ������������� StringBuilder � ������� append
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
	 * ������������� StringBuffer � ������� append
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
		// ��� String �� ������������, �.�. �����
		count = 1_000_000;
		testStringBuilder();
		testStringBuffer();
	}
}
