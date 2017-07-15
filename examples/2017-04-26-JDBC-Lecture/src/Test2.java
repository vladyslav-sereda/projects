import com.my.db.DBException;
import com.my.db.entity.User;
import com.my.db.logic.UserManager;


public class Test2 {
	
	public static void main(String[] args) throws DBException {
		UserManager userManager = UserManager.getInstance();
		User user = userManager.findUser("client");
		System.out.println(user);
	}

}
