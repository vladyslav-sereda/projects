package ua.kharkov.knure.kolesnikov;

/**
 * Singleton. Можно создать не более одного объекта данного класса.<br/>
 * Классическая схема.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Singleton {

	/**
	 * Объект, который будет возвращать метод {@link #getInstance()}
	 */
	private static Singleton instance;

	/**
	 * Возвращает объект Singleton, который будет создан при первом вызове
	 * метода.<br/>
	 * Такую схему называют отложенной инициализацией (lazy initialization).<br/>
	 * Спецификатор synchronized ставят для обеспечения синхронизации доступа к
	 * методу из разных потоков.
	 * 
	 * @return объект Singleton.
	 */
	public static synchronized final Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * Закрытый конструктор делает невозможным создание с его помощью объекта за
	 * пределами данного класса.
	 */
	private Singleton() {
		// ...
	}

}
