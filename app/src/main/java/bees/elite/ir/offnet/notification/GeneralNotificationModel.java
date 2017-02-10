package bees.elite.ir.offnet.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bees.elite.ir.offnet.user.UserNoteVO;



/**
 * Created by yazdandoost on 9/4/2016.
 */
public class GeneralNotificationModel {
    @SerializedName("noteType")
    @Expose
    private String noteType;

    public UserNoteVO getUserNoteVO() {
        return userNoteVO;
    }

    public void setUserNoteVO(UserNoteVO userNoteVO) {
        this.userNoteVO = userNoteVO;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    @SerializedName("userNoteVO")
    @Expose
    private UserNoteVO userNoteVO;


   /* @SerializedName("repairmanVO")
    @Expose
    private RepairManModel repairmanVO;

    public RepairManModel getRepairmanVO() {
        return repairmanVO;
    }

    public void setRepairmanVO(RepairManModel repairmanVO) {
        this.repairmanVO = repairmanVO;
    }*/

    public GeneralNotificationModel(String noteType,UserNoteVO userNoteVO) {
        this.noteType = noteType;
        this.userNoteVO=userNoteVO;
        //this.repairmanVO = repairmanVO;
    }



}
