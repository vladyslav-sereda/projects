package ua.kharkov.knure.kolesnikov;

/**
 * Singleton без отложенной инициализации.<br/>
 * Сущствуют и другие варианты структуры Singleton.
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
