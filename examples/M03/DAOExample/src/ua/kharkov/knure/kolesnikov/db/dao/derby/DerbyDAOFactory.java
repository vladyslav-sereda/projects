package ua.kharkov.knure.kolesnikov.db.dao.derby;

import ua.kharkov.knure.kolesnikov.db.dao.DAOFactory;
import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;

/**
 * ���������� DAOFactory ��� DBMS Apache Derby.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class DerbyDAOFactory extends DAOFactory {

	/**
	 * ������������ ������ - ���������� ������ ��� ����� ������ EntityDAO
	 * (��������, � ����� ������, � �������������������). �� ����� ������� ���,
	 * ����� ����� ��������� ���� � ��� �� ������ (�.�. ����������� Singleton
	 * ��� ��������� EntityDAO).
	 */
	@Override
	public UserDAO getUserDAO() {
		return new DerbyUserDAO();
	}

	// ������ ��������� DAO ��� ������ ���������
	// ...

}
