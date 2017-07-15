package ua.kharkov.knure.kolesnikov.db.dao.derby;

import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;
import ua.kharkov.knure.kolesnikov.db.entity.User;

public class DerbyUserDAO implements UserDAO {

	/**
	 * ����� �������� ���������������� ���������� ������������ � ���� ������
	 * Apache Derby � �������������� �������������� ���������, �������
	 * ������������� ��� ����.
	 */
	@Override
	public void create(User user) {
		// ������ �������� ����, ��� ��� ��������
		System.out.println("DerbyUserDAO#create");
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
