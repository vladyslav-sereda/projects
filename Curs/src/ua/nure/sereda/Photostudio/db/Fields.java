package ua.nure.sereda.Photostudio.db;

/**
 * Created by sered on 11.05.2017.
 */
public class Fields {
    public static final class Users {
        public static final String ID = "id_user";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String PHONE = "phone_number";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
    }

    public static final class Reservations {
        public static final String ID = "id_reservation";
        public static final String DAY_ID = "id_day";
        public static final String USER_ID = "id_user";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String PRICE = "price";
        public static final String STATUS = "status";
    }
    public static final class WorkDays{
        public static final String ID = "id_day";
        public static final String DATE = "date";
        public static final String START_DAY = "start_day";
        public static final String END_DAY = "end_day";
    }
}
