package bees.elite.ir.offnet.config;

public class Config {
    // server URL configuration

   public static final String URL_SERAJ_API_BASE =   "http://192.168.42.253:8081/";
    public static final String SMS_ORIGIN = "ANHIVE";

    public static final String OTP_DELIMITER = ":";
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String NOTIFY_REPAIRMAN_PUSH_NOTIFICATION = "notifyRepairman";
    public static final String REPAIRMAN_INFO_PUSH_NOTIFICATION = "repairmanInfo";
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

}