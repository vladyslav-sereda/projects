package ua.kharkov.knure.kolesnikov.db.dao;

/**
 * ����������� ������� ��������� ���������� DAOFactory.<br/>
 * � ������� ������� ������ ����� ����������� ������������ � ����� DB �� ������.<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public abstract class DAOFactory {
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * �������� ���� ������, ������� ������ ������ DAOFactory
	 */
	private static DAOFactory instance;

	/**
	 * ����� ��������� ������� DAOFactory. ����� ������ ���������� DAOFactory
	 * ����� ���������� ���������� ��������� �������.
	 * 
	 * @return ��������� ������� DAOFactory, ��� �������� ���������� �
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
	 * ����������� ��� ����������� ����������� ������ �����.<br/>
	 */
	protected DAOFactory() {
		// nothing to do
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * �������� ������ ����������������� ��� ������, ������ �������� �����
	 * ���������� {@link #getInstance()}
	 */
	private static String daoFactoryFCN;

	public static void setDaoFactoryFCN(String daoFactoryFCN) {
		instance = null;
		DAOFactory.daoFactoryFCN = daoFactoryFCN;
	}

	/**
	 * ����� ��������� DAO ��� �������� User.
	 * 
	 * @return ���������� UserDAO, ������� ���������� ��������� �������
	 *         {@link DAOFactory}.
	 */
	public abstract UserDAO getUserDAO();

	// ��������� ������ ��������� DAO ��� ���� ��������� ���������
	// ...
}
