package ua.kharkov.knure.kolesnikov;

/**
 * Singleton ��� ���������� �������������.<br/>
 * ��������� � ������ �������� ��������� Singleton.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class SingletonWOLI {
	public static final SingletonWOLI instance = new SingletonWOLI();

	
	public static SingletonWOLI getInstance() {
		return instance;
	}
	
	private SingletonWOLI() {
		// ...
	}
}
