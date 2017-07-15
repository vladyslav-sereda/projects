import java.util.List;

import com.my.db.DBException;
import com.my.db.DBManager;
import com.my.db.entity.User;

public class Test {
	// jdbc:derby://localhost:1527/testdb?n=v&n2=v2&
	private static final String CONNECTION_URL = "jdbc:derby://localhost:1527/testdb;user=test;password=test";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	public static void main(String[] args) {
		DBManager dbManager = DBManager.getInstance();
		try {
			User user = new User(0, "asdf2", "name", 2);
			dbManager.createUser(user);

			List<User> users = dbManager.findAllUsers();
			for (User u : users) {
				System.out.println(u);
			}
			
			System.out.println("~~~");
			System.out.println(user);
		} catch (DBException ex) {
			ex.printStackTrace();
		}


	}
	public static void main2(String[] args) {
		DBManager dbManager = DBManager.getInstance();
		try {
			List<User> users = dbManager.findAllUsers();
			for (User user : users) {
				System.out.println(user);

			}
			System.out.println("~~~");
			System.out.println(dbManager.findUser("admin"));
		} catch (DBException ex) {
			System.out.println(ex.getMessage());
		}


	}

}
