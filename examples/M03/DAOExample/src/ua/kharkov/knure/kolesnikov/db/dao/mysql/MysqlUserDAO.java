package ua.kharkov.knure.kolesnikov.db.dao.mysql;

import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;
import ua.kharkov.knure.kolesnikov.db.entity.User;

public class MysqlUserDAO implements UserDAO {

	/**
	 * ����� �������� ���������������� ���������� ������������ � ���� ������
	 * MySQL � �������������� �������������� ���������, ������� �������������
	 * ��� ����.
	 */
	@Override
	public void create(User user) {
		// ������ �������� ����, ��� ��� ��������
		System.out.println("MysqlUserDAO#create");
	}

	// ��������

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
