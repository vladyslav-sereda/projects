import java.lang.reflect.Constructor;

public class Demo {
	public static void main(String[] args) {
		// проход по всем элементам:
		for (Season s : Season.values())
			System.out.println(s);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// использование в swtich
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
		
		// 'реальные' конструкторы Season
		for (Constructor<?> c : Season.class.getDeclaredConstructors()) {
			System.out.println(c);
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// порядковый номер
		System.out.println(Season.WINTER.ordinal());
	}
}
