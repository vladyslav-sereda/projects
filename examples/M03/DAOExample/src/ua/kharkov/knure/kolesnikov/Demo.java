package ua.kharkov.knure.kolesnikov;

import ua.kharkov.knure.kolesnikov.db.dao.DAOFactory;
import ua.kharkov.knure.kolesnikov.db.dao.UserDAO;

/**
 * Класс, который демонстрирует работу с DAOFactor.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {
	public static void main(String[] args) throws Exception {

		// Derby
		DAOFactory
				.setDaoFactoryFCN("ua.kharkov.knure.kolesnikov.db.dao.derby.DerbyDAOFactory");
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.create(null);

		// MySQL
		DAOFactory
				.setDaoFactoryFCN("ua.kharkov.knure.kolesnikov.db.dao.mysql.MysqlDAOFactory");
		daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDAO();
		userDAO.create(null);
	}

}
