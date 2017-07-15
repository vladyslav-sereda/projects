package ua.kharkov.knure.kolesnikov.db.dao.mysql;

import ua.kharkov.knure.kolesnikov.db.dao.DAOFactory;
import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;

/**
 * ���������� DAOFactory ��� DBMS MySQL.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new MysqlUserDAO();
	}

	// ������ ��������� DAO ��� ������ ���������
	// ...

}
