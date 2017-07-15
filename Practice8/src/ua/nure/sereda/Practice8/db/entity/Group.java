package ua.nure.sereda.Practice8.db.entity;

/**
 * Created by sered on 28.04.2017.
 */
public class Group {
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
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Group createGroup(String name) {
        Group group = new Group();
        group.setId(0);
        group.setName(name);
        return group;
    }
}
