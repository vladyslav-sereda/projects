import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ������������� �������� ������� � ����� �� ��� ������� � ������� �������
 * ���������.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {

	/**
	 * ���������� ����� (generic).
	 * 
	 * @param clazz
	 *            �������������� � ������ ������.
	 * @param elements
	 *            ������ ���������, ������� ����� �������� � ������.
	 * @return ������ � ������������ ����������.
	 */
	private <T> List<T> test(Class<?> clazz, T... elements) throws Exception {
		// ������� ��������� ������
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) clazz.newInstance();

		// �������� ������ Method, ������� ������������ ����� List#add
		Method method = clazz.getMethod("add", Object.class);

		// ��������� ��� �������� � ������
		for (T element : elements) {
			method.invoke(list, element);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		Demo t = new Demo();

		// � ������ ������ ��������� �������� ��������� generic �����������
		// �� ����� ������� ���������� � String
		List<String> list = t.test(ArrayList.class, "A", "B");
		System.out.println(list);

		// � ������ ������ ��������� �������� ��������� ���� �����������
		// �.�. �� ���������� (1, 2 � 3.2) ���� ��� ���������� ����:
		// Number � Comparable
		List<Number> list2 = t.<Number> test(ArrayList.class, 1, 2, 3.2);
		System.out.println(list2);
	}

}