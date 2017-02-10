package bees.elite.ir.offnet.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by yazdandoost on 9/4/2016.
 */
public class Coupn {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("areaId")
    @Expose
    private String areaId;

    @SerializedName("disCountPCT")
    @Expose
    private String disCountPCT;

    @SerializedName("minScore")
    @Expose
    private String minScore;

    @SerializedName("maxScore")
    @Expose
    private String maxScore;


    @SerializedName("minAmount")
    @Expose
    private String minAmount;

    @SerializedName("caller")
    @Expose
    private String caller;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("toDate")
    @Expose
    private String toDate;

    @SerializedName("fromDate")
    @Expose
    private String fromDate;

    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    @SerializedName("areaName")
    @Expose
    private String areaName;

    @SerializedName("callerName")
    @Expose
    private String callerName;

    @SerializedName("indexOfState")
    @Expose
    private String indexOfState;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getDisCountPCT() {
        return disCountPCT;
    }

    public void setDisCountPCT(String disCountPCT) {
        this.disCountPCT = disCountPCT;
    }

    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getIndexOfState() {
        return indexOfState;
    }

    public void setIndexOfState(String indexOfState) {
        this.indexOfState = indexOfState;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    @SerializedName("noteType")
    @Expose
    private String noteType;



    public Coupn() {

    }

    public Coupn(String id, String indexOfState, String areaName, String desc, String amount) {
        this.id = id;
        this.indexOfState = indexOfState;
        this.areaName = areaName;
        this.desc = desc;
        this.amount = amount;
    }

}
