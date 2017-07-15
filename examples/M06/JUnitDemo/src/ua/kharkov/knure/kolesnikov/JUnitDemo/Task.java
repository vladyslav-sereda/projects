package ua.kharkov.knure.kolesnikov.JUnitDemo;

/**
 * ����� � ������ �������.
 * 
 * @author Dmitry Kolesnkov
 * 
 */
public class Task {

	/**
	 * ����� ������ ������.<br/>
	 * � ������ ������ �������� ���� �����.
	 * 
	 * @param x
	 *            ������ ���������.
	 * @param y
	 *            ������ ���������.
	 * @return ����� ���������.
	 * 
	 */
	public int add(int x, int y) {
		int result = x + y;
		// result = x + y + 1; // <== try to uncomment this line!!!
		return result;
	}

	/**
	 * ����� ������ ������.<br/>
	 * � ������ ������ ������� ���� <i>�����</i> �����.<br/>
	 * ������ ����� ������������� �����������, ��������, ���������� ����������:
	 * (6, 3); (7, 3); (7, 0)
	 * 
	 * @param x
	 *            �������.
	 * @param y
	 *            ��������.
	 * @return ��������� ������� �������� �� ��������
	 * @throws ArithmeticException
	 *             ���� ������� 0.
	 */
	public int div(int x, int y) {
		return x / y;
	}

	/**
	 * ����� main ����� ������� ���������� �������� ������������
	 * ���������������� ������.
	 */
	public static void main(String[] args) {
		Task t = new Task();
		System.out.println(t.div(3, 2));
	}

}