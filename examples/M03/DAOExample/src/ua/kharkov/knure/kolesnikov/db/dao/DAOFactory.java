package ua.kharkov.knure.kolesnikov.db.dao;

/**
 * Абстрактная фабрика получения реализаций DAOFactory.<br/>
 * С помощью данного класса можно осуществить переключение с одной DB на другую.<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public abstract class DAOFactory {
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * Закрытое поле класса, которое хранит объект DAOFactory
	 */
	private static DAOFactory instance;

	/**
	 * Метод получения объекта DAOFactory. Какая именно реализация DAOFactory
	 * будет возвращена определяют настройки фабрики.
	 * 
	 * @return экземпляр потомка DAOFactory, имя которого содержится в
	 *         {@link #daoFactoryFCN}
	 */
	public static synchronized DAOFactory getInstance() throws Exception {
		if (instance == null) {
			Class<?> clazz = Class.forName(DAOFactory.daoFactoryFCN);
			instance = (DAOFactory) clazz.newInstance();
		}
		return instance;
	}

	/**
	 * Конструктор для возможности наследовать данный класс.<br/>
	 */
	protected DAOFactory() {
		// nothing to do
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Содержит полное квалифицированное имя класса, объект которого будет
	 * возвращать {@link #getInstance()}
	 */
	private static String daoFactoryFCN;

	public static void setDaoFactoryFCN(String daoFactoryFCN) {
		instance = null;
		DAOFactory.daoFactoryFCN = daoFactoryFCN;
	}

	/**
	 * Метод получения DAO для сущности User.
	 * 
	 * @return реализация UserDAO, которую определяют настройки фабрики
	 *         {@link DAOFactory}.
	 */
	public abstract UserDAO getUserDAO();

	// остальные методы получения DAO для всех остальных сущностей
	// ...
}
