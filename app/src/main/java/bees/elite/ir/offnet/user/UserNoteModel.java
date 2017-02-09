package bees.elite.ir.offnet.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by yazdandoost on 9/24/2016.
 */
public class UserNoteModel {
    public List<UserNoteVO> getUserNoteVO() {
        return userNoteVO;
    }

    public void setUserNoteVO(List<UserNoteVO> userNoteVO) {
        this.userNoteVO = userNoteVO;
    }

    @SerializedName("userNoteVO")
    @Expose
    private List<UserNoteVO> userNoteVO = new ArrayList<UserNoteVO>();


}
