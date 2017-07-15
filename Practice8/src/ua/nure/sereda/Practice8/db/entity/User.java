package ua.nure.sereda.Practice8.db.entity;

/**
 * Created by sered on 28.04.2017.
 */
public class User {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser(String name) {
        User user = new User();
        user.setId(0);
        user.setName(name);
        return user;
    }
}
