import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ƒемонстрирует создание объекта и вызов на нем методов с помощью средств
 * рефлексии.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {

	/**
	 * ќбобщенный метод (generic).
	 * 
	 * @param clazz
	 *            метаинформаци€ о классе списка.
	 * @param elements
	 *            массив элементов, которые нужно добавить в список.
	 * @return список с добавленными элементами.
	 */
	private <T> List<T> test(Class<?> clazz, T... elements) throws Exception {
		// создаем экземпл€р списка
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) clazz.newInstance();

		// получаем объект Method, который представл€ет метод List#add
		Method method = clazz.getMethod("add", Object.class);

		// добавл€ем все элементы в список
		for (T element : elements) {
			method.invoke(list, element);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		Demo t = new Demo();

		// в данном случае указывать значение параметра generic об€зательно
		// он будет положен однозначно в String
		List<String> list = t.test(ArrayList.class, "A", "B");
		System.out.println(list);

		// в данном случае указывать значение параметра типа об€зательно
		// т.к. по аргументам (1, 2 и 3.2) есть два подход€щих типа:
		// Number и Comparable
		List<Number> list2 = t.<Number> test(ArrayList.class, 1, 2, 3.2);
		System.out.println(list2);
	}

}