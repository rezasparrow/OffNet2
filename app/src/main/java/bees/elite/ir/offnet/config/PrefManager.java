package bees.elite.ir.offnet.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by yazdandoost on 9/18/2016.
 */
public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SerajPref";

    // All Shared Preferences Keys
    private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_WIZARD_RESULT_TEMP = "jSON";
    private static final String KEY_USER_AUTHENTICATE_TOKEN = "rm_authenticate_token";
    private static final String KEY_REPAIRMAN_STATE = "rm_state";
    private static final String KEY_REPAIRMAN_TRADE_UNIT = "";
    private static final String KEY_last_serach = "last_search";
    private static final String KEY_userVOList = "userVOList";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isRepairManActive() {
        return pref.getBoolean(KEY_REPAIRMAN_STATE, false);
    }
    public void setIsRepairManActive(boolean isActive) {
        editor.putBoolean(KEY_REPAIRMAN_STATE, isActive);
        editor.commit();
    }
    public String getUserVOList(){
        return pref.getString(KEY_userVOList, null);
    }
    public void setUserVOList(String userVoJson){
        editor.putString(KEY_userVOList, userVoJson);
        editor.commit();
    }

    public void setUserAuthenticateToken(String token) {
        editor.putString(KEY_USER_AUTHENTICATE_TOKEN, token);
        editor.commit();
    }
    public String getUserAuthenticateToken() {
        return pref.getString(KEY_USER_AUTHENTICATE_TOKEN, null);
    }
    public void setAppRegId(String token) {
        editor.putString("regId", token);
        editor.commit();
    }
    public String getAppRegId() {
        return pref.getString("regId", null);
    }

    public void setRepairmanTradeUnit(String val) {
        editor.putString(KEY_REPAIRMAN_TRADE_UNIT, val);
        editor.commit();
    }
    public String getRepairmanTradeUnit() {
        return pref.getString(KEY_REPAIRMAN_TRADE_UNIT, null);
    }
    public void setWizardResultTemp(String wizardResult) {
        editor.putString(KEY_WIZARD_RESULT_TEMP, wizardResult);
        editor.commit();
    }
    public String getWizardResultTemp() {
        return pref.getString(KEY_WIZARD_RESULT_TEMP, null);
    }
    public boolean hasWizardResultTempValue() {
        return !pref.getString(KEY_WIZARD_RESULT_TEMP, null).equals("jSON");
    }


    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, null);
    }

    public void setLastSearch(String lastSearch) {
        editor.putString(KEY_last_serach, lastSearch);
        editor.commit();
    }

    public String getLastSearch() {
        return pref.getString(KEY_last_serach, null);

    }


    public void createLogin(String mobile) {
        //editor.putString(KEY_NAME, name);
        //editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE, mobile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public boolean isWaitingForSms() {
        return pref.getBoolean(KEY_IS_WAITING_FOR_SMS, false);
    }
    public void setIsWaitingForSms(boolean isWaiting) {
        editor.putBoolean(KEY_IS_WAITING_FOR_SMS, isWaiting);
        editor.commit();
    }
    public void clearSession() {
        editor.clear();
        editor.commit();
    }
    public void logOut() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<String, String>();
        profile.put("name", pref.getString(KEY_NAME, null));
        profile.put("email", pref.getString(KEY_EMAIL, null));
        profile.put("mobile", pref.getString(KEY_MOBILE, null));
        return profile;
        }
        }
