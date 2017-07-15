package ua.kharkov.knure.kolesnikov.db.dao.derby;

import ua.kharkov.knure.kolesnikov.db.dao.DAOFactory;
import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;

/**
 * Реализация DAOFactory для DBMS Apache Derby.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class DerbyDAOFactory extends DAOFactory {

	/**
	 * Классический подход - возвращать каждый раз новый объект EntityDAO
	 * (связанно, в общем случае, с потокобезопасностью). Но можно сделать так,
	 * чтобы метод возвращал один и тот же объект (т.е. реализовать Singleton
	 * для получения EntityDAO).
	 */
	@Override
	public UserDAO getUserDAO() {
		return new DerbyUserDAO();
	}

	// методы получения DAO для других сущностей
	// ...

}
