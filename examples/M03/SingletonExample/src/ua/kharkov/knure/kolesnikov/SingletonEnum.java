package ua.kharkov.knure.kolesnikov;

/**
 * Singleton в виде enum.<br/>
 * Отложенной инициализации нет.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public enum SingletonEnum {
	INSTANCE;

	SingletonEnum() {
		// ...
	}
}