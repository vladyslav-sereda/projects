package ua.kharkov.knure.kolesnikov.db.dao.mysql;

import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;
import ua.kharkov.knure.kolesnikov.db.entity.User;

public class MysqlUserDAO implements UserDAO {

	/**
	 * Метод содержит функциональность добавления пользователя в базу данных
	 * MySQL с использованием функциональных элементов, которые предоставляет
	 * эта СУБД.
	 */
	@Override
	public void create(User user) {
		// просто проверка того, что все работает
		System.out.println("MysqlUserDAO#create");
	}

	// заглушки

	@Override
	public User read(long id) {
		return null;
	}

	@Override
	public boolean update(User user) {
		return false;
	}

	@Override
	public boolean delete(User user) {
		return false;
	}

}
