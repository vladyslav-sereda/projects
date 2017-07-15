package ua.nure.sereda.Photostudio.exception;

/**
 * Created by sered on 11.05.2017.
 */
public class ErrorMessages {

    //user dao
    public static final String CANT_CREATE_USER = "Error occurred during creating user";
    public static final String CANT_UPDATE_USER = "Error occurred during updating user";
    public static final String CANT_DELETE_USER = "Error occurred during deleting user";
    public static final String CANT_GET_ALL_USERS = "Error occurred during getting users list";
    public static final String CANT_GET_USER_BY_ID = "Error occurred during getting user by id : ";
    public static final String CANT_GET_USER_BY_EMAIL = "Error occurred during getting user by e-mail : ";

    //workday dao
    public static final String CANT_CREATE_WORKDAY = "Error occurred during creating workday";
    public static final String CANT_UPDATE_WORKDAY = "Error occurred during updating workday";
    public static final String CANT_DELETE_WORKDAY = "Error occurred during deleting workday";
    public static final String CANT_GET_ALL_WORKDAYS = "Error occurred during getting workdays list";
    public static final String CANT_GET_WORKDAY_BY_ID = "Error occurred during getting workday by id : ";
    public static final String CANT_GET_WORKDAYS_FROM_TODAY = "Error occurred during getting workdays list from today";
    public static final String CANT_GET_WORKDAY_BY_DATE = "Error occurred during getting workday by date : ";;

    //reservation dao
    public static final String CANT_CREATE_RESERVATION = "Error occurred during creating reservation";
    public static final String CANT_UPDATE_RESERVATION = "Error occurred during updating reservation";
    public static final String CANT_DELETE_RESERVATION = "Error occurred during deleting reservation";
    public static final String CANT_GET_ALL_RESERVATIONS = "Error occurred during getting reservations list";
    public static final String CANT_GET_RESERVATION_BY_ID = "Error occurred during getting reservation by id : ";
    public static final String CANT_GET_RESERVATION_BY_STATUS = "Error occurred during getting reservation by status : ";
    public static final String CANT_GET_RESERVATION_BY_WORKDAY = "Error occurred during getting reservation by workday : ";
    public static final String CANT_GET_RESERVATION_BY_USER = "Error occurred during getting reservation by user : ";
}
