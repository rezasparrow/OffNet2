package bees.elite.ir.offnet.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import bees.elite.ir.offnet.user.Coupn;
import bees.elite.ir.offnet.user.UserNoteVO;



/**
 * Created by yazdandoost on 9/4/2016.
 */
public class GeneralNotificationModel {
    @SerializedName("noteType")
    @Expose
    private String noteType;


    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public Coupn getCoupn() {
        return coupn;
    }

    public void setCoupn(Coupn coupn) {
        this.coupn = coupn;
    }

    @SerializedName("coupn")
    @Expose
    private Coupn coupn;


   /* @SerializedName("repairmanVO")
    @Expose
    private RepairManModel repairmanVO;

    public RepairManModel getRepairmanVO() {
        return repairmanVO;
    }

    public void setRepairmanVO(RepairManModel repairmanVO) {
        this.repairmanVO = repairmanVO;
    }*/

    public GeneralNotificationModel(String noteType,Coupn coupn) {
        this.noteType = noteType;
        this.coupn=coupn;
        //this.repairmanVO = repairmanVO;
    }



}
