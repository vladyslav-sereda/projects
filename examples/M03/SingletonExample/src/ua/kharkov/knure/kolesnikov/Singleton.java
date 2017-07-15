package ua.kharkov.knure.kolesnikov;

/**
 * Singleton. ����� ������� �� ����� ������ ������� ������� ������.<br/>
 * ������������ �����.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Singleton {

	/**
	 * ������, ������� ����� ���������� ����� {@link #getInstance()}
	 */
	private static Singleton instance;

	/**
	 * ���������� ������ Singleton, ������� ����� ������ ��� ������ ������
	 * ������.<br/>
	 * ����� ����� �������� ���������� �������������� (lazy initialization).<br/>
	 * ������������ synchronized ������ ��� ����������� ������������� ������� �
	 * ������ �� ������ �������.
	 * 
	 * @return ������ Singleton.
	 */
	public static synchronized final Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * �������� ����������� ������ ����������� �������� � ��� ������� ������� ��
	 * ��������� ������� ������.
	 */
	private Singleton() {
		// ...
	}

}
