import java.lang.reflect.Constructor;

public class Demo {
	public static void main(String[] args) {
		// ������ �� ���� ���������:
		for (Season s : Season.values())
			System.out.println(s);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// ������������� � swtich
		Season s = Season.valueOf("SPRING");
		switch (s) {
		case SPRING:
		case SUMMER:
			System.out.println("It's ok!)");
			break;
		default:
			System.out.println("It's cold..");
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// '��������' ������������ Season
		for (Constructor<?> c : Season.class.getDeclaredConstructors()) {
			System.out.println(c);
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// ���������� �����
		System.out.println(Season.WINTER.ordinal());
	}
}
