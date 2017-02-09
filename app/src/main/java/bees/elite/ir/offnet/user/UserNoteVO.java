package bees.elite.ir.offnet.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by yazdandoost on 9/4/2016.
 */
public class UserNoteVO {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("noteType")
    @Expose
    private String noteType;

    @SerializedName("gotoAddress")
    @Expose
    private String gotoAddress;

    @SerializedName("creationDate")
    @Expose
    private String creationDate;

    @SerializedName("creationDateFA")
    @Expose
    private String creationDateFA;

    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;

    @SerializedName("expirationDateFA")
    @Expose
    private String expirationDateFA;

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getExpirationDateFA() {
        return expirationDateFA;
    }

    public void setExpirationDateFA(String expirationDateFA) {
        this.expirationDateFA = expirationDateFA;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreationDateFA() {
        return creationDateFA;
    }

    public void setCreationDateFA(String creationDateFA) {
        this.creationDateFA = creationDateFA;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getGotoAddress() {
        return gotoAddress;
    }

    public void setGotoAddress(String gotoAddress) {
        this.gotoAddress = gotoAddress;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("scopeType")
    @Expose
    private String scopeType;

    @SerializedName("importance")
    @Expose
    private String importance;

    public UserNoteVO() {

    }

    public UserNoteVO(String id, String message, String creationDateFA, String expirationDateFA) {
        this.id = id;
        this.message = message;
        this.creationDateFA = creationDateFA;
        this.expirationDateFA = expirationDateFA;
    }

}
