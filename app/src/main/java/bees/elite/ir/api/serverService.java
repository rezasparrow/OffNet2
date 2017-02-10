package bees.elite.ir.api;

import android.content.Context;
import android.location.Location;

import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.config.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Reza on 2/10/2017.
 */

public class ServerService {
    final RestClient restClient;
    Context context;

    public ServerService(Context context) {
        restClient = RestClient.getInstance();
        this.context = context;
    }

    public void sendLocation(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        PrefManager manager = new PrefManager(context);
        Call<String> stringCall = restClient.getApi().sendLocation(manager.getUserName(), manager.getAppRegId(), latitude.toString(), longitude.toString());

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
