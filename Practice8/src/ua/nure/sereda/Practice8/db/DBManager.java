package ua.nure.sereda.Practice8.db;

import ua.nure.sereda.Practice8.db.entity.Group;
import ua.nure.sereda.Practice8.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sered on 28.04.2017.
 */
public class DBManager {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3333/Practice8?user=root&password=159357&useSSL=false";

    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT , ?)";

    private static final String SQL_FIND_USER = "SELECT * FROM users WHERE name= ? ";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

    private static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT , ?)";

    private static final String SQL_FIND_GROUP = "SELECT * FROM groups WHERE name= ?";

    private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";

    private static final String SQL_SET_GROUPS_FOR_USERS = "INSERT INTO users_groups VALUES (?,?)";

    private static final String SQL_GET_USER_GROUPS =
            "SELECT groups.id , groups.name " +
                    "FROM users, groups, users_groups " +
                    "WHERE groups.id=users_groups.group_id " +
                    "AND users.id=users_groups.user_id " +
                    "AND users.id=?";

    private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id=?";

    private static final String SQL_UPDATE_GROUP = "UPDATE groups SET name=? WHERE id=?";

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, user.getName());
            if (prepareStatement.executeUpdate() > 0) {
                ResultSet resultSet = prepareStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL_FIND_USER)) {
            user = new User();
            prepareStatement.setString(1, name);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void insertGroup(Group group) {
        try (Connection connection = getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, group.getName());
            if (prepareStatement.executeUpdate() > 0) {
                ResultSet resultSet = prepareStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    group.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group getGroup(String name) {
        Group group = null;
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL_FIND_GROUP)) {
            group = new Group();
            prepareStatement.setString(1, name);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public List<Group> findAllGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_GROUPS)) {
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    public void setGroupsForUser(User user, Group... groups) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            for (Group group : groups) {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_GROUPS_FOR_USERS);
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, group.getId());
                preparedStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Group> getUserGroups(User user) {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_GROUPS)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public void deleteGroup(Group group) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_GROUP)) {
            prepareStatement.setInt(1, group.getId());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_GROUP)) {
            prepareStatement.setString(1, group.getName());
            prepareStatement.setInt(2, group.getId());
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
