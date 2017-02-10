package bees.elite.ir.offnet.config;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yazdandoost on 9/21/2016.
 */
public interface SerajAPI {
    //@GET("/erp/jersey/mobileRestFace/authenticate")

    @GET("/api/authenticate")
    Call<String> authenticate(@Query("username") String username, @Query("password") String password, @Query("deviceId") String deviceId);

    @GET("/api/pay")
    Call<String> pay(@Query("username") String username, @Query("couponId") String couponId, @Query("deviceId") String deviceId);

    @GET("/api/reject")
    Call<String> reject(@Query("username") String username, @Query("couponId") String couponId, @Query("deviceId") String deviceId);

    @GET("/api/location")
    Call<String> sendLocation(@Query("username") String userName , @Query("deviceId") String deviceId ,@Query("latitude") String latitude , @Query("longitude")  String longitude );


/*
    @GET("/jersey/mobileRestFace/getNewUserNotesByToken")
    Call<UserNoteModel> getNewUserNotesByToken(@Query("token") String token);

    @GET("/jersey/mobileRestFace/getNewUserNotesByToken")
    Call<UserNoteModel> getNewUserNotesByToken(@Query("token") String token, @Query("filter") String filter);

    @GET("/jersey/mobileRestFace/makeUserNoteVisitedMobile")
    Call<String> makeUserNoteVisitedMobile(@Query("userNoteId") String userNoteId, @Query("deviceId") String deviceId);

    @GET("/jersey/mobileRestFace/makeUserNoteArchivedMobile")
    Call<String> makeUserNoteArchivedMobile(@Query("userNoteId") String userNoteId, @Query("deviceId") String deviceId);*/
}