package ua.kharkov.knure.kolesnikov.db.entity;

/**
 * ќбъекты этого класса представл€ют собой строки из таблицы users.<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class User {
	private long id;
	private String login;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
