package com.chaozhis;

/**
 * 程序级常量
 *
 * @author fangying | 2014-07-01
 */
public class AppConstants {
    public static final int ONE_DAY = 24 * 60 * 60;
    public static final int FIVE_MIN = 5 * 60;
    public static final int HALF_DAY = 43200;
    public static final int ALMOST_TWO_HOUR = 2 * 60 * 60 - 10;
    public static final int ALMOST_TWO_MINUTES = 2 * 60 - 3;

    public static final String WX_REDIS_AUTOREPLY_PEFIX = "wx_autorepay_";
    public static final String DESkey = "czkey";

    public static final String USER_MOBILE_VALICODE = "phone_valicode_";
    public static final String USER_MOBILE_CAN_SEND_SMS = "phone_can_sms_";
    public static final String USER_IP_CAN_SEND_SMS = "ip_can_sms_";
    public static final String USER_LOGIN_SHOW_VALICODE = "login_show_valicode_";
    public static final String USER_IS_VALID_STATUS = "userstatus:user_valid_";
    public static final String DATA_SETTINGS = "data_settings";

    public static final Double FIRST_MONEY_PERCENT = 0.001; // 直接提成百分比
    public static final Double SECOND_MONEY_PERCENT = 0.001; // 间接提成百分比

}
