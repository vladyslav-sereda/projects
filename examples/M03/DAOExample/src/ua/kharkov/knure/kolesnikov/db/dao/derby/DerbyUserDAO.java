package ua.kharkov.knure.kolesnikov.db.dao.derby;

import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;
import ua.kharkov.knure.kolesnikov.db.entity.User;

public class DerbyUserDAO implements UserDAO {

	/**
	 * Метод содержит функциональность добавления пользователя в базу данных
	 * Apache Derby с использованием функциональных элементов, которые
	 * предоставляет эта СУБД.
	 */
	@Override
	public void create(User user) {
		// просто проверка того, что все работает
		System.out.println("DerbyUserDAO#create");
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
