package bees.elite.ir.offnet.config;

public class Config {
    // server URL configuration

 // public static final String URL_SERAJ_API_BASE =   "http://79.175.172.108/";
   public static final String URL_SERAJ_API_BASE =   "http://192.168.21.162:8080/";
//public static final String URL_SERAJ_API_BASE =   "http://serajcloud.ir";

   /* public static final String URL_SERAJ_API_BASE =   "http://192.168.1.36:8181/";*/

    // SMS provider identification
    // It should match with your SMS gateway origin
    // You can use  MSGIND, TESTER and ALERTS as sender ID
    // If you want custom sender Id, approve MSG91 to get one
    public static final String SMS_ORIGIN = "ANHIVE";

    // special character to prefix the otp. Make sure this character appears only once in the sms
    public static final String OTP_DELIMITER = ":";
    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String NOTIFY_REPAIRMAN_PUSH_NOTIFICATION = "notifyRepairman";
    public static final String REPAIRMAN_INFO_PUSH_NOTIFICATION = "repairmanInfo";
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

}