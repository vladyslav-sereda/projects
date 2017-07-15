package ua.nure.sereda.SummaryTask4.db;

/**
 * Created by Vladyslav.
 */
public class Fields {
    //Users
    public static final class Users {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role_id";
        public static final String BANNED = "banned";
    }

    //Role
    public static final class Roles {
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    //Books
    public class Books {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AUTHOR = "author";
        public static final String PUBLISHER = "publisher";
        public static final String PUBLIC_DATE = "publication_date";
        public static final String AVAILABLE = "available";
    }

    //Orders
    public class Orders {
        public static final String ID = "id";
        public static final String USER_ID = "user_id";
        public static final String BOOK_ID = "book_id";
        public static final String STATUS = "status";
        public static final String READING_ROOM = "reading_room";
        public static final String DEADLINE = "deadline";
    }
}
