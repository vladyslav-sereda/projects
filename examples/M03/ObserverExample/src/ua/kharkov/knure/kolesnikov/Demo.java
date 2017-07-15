package ua.kharkov.knure.kolesnikov;

/**
 * ����� ������������ ������ � �������� Observer.<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {
	public static void main(String[] args) {
		// �������� �������
		EventSource source = new EventSource();

		// ����������� ���� ���������� �������
		source.add(new EventListenerImplA());
		source.add(new EventListenerImplB());

		// ������������� ������������� ������� �1
		source.fireEvent1();
		System.out.println("~~~~~~~~");

		// ������������� ������������� ������� �2
		source.fireEvent2();
	}

}
