package bees.elite.ir.offnet.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import bees.elite.ir.offnet.R;
import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.config.RestClient;
import bees.elite.ir.offnet.service.LocationService;
import bees.elite.ir.offnet.user.Coupn;


public class StartAppActivity extends AppCompatActivity {
    public static final int uniqueId = 243214;
    ListView mainList;
    ProgressDialog pd;
    final RestClient restClient = RestClient.getInstance();
    EditText username;
    EditText password;
    Button login;
    private PrefManager pref;
    final RestClient rc = RestClient.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        // setContentView(R.layout.content_main);
        pref = new PrefManager(this);
        /*String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        pref.setAppRegId(android_id);*/

        if (pref.getUserAuthenticateToken() == null || pref.getUserAuthenticateToken().equals("")) {
            Intent i = new Intent(getApplicationContext(), AuthenticateUserActivity.class);
            startActivity(i);
        } else {

            startService(new Intent(getApplicationContext(), LocationService.class));
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        notificationReceived();
        displayFirebaseRegId();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void notificationReceived() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("noteType")) {
                Toast.makeText(getApplicationContext(), "دریافت نوتیفیکیشن", Toast.LENGTH_LONG).show();
               // if (intent.getStringExtra("noteType").equals("general")) {
                    Toast.makeText(getApplicationContext(), "Type:general", Toast.LENGTH_LONG).show();
                    String coupn = (String) intent.getSerializableExtra("coupn");
                    try {
                        Gson gson = new Gson();
                        String jsonOfUserVoList=pref.getUserVOList();
                        Type type = new TypeToken<List<Coupn>>(){}.getType();
                        //List<UserNoteVO> l=gson.fromJson(jsonOfUserVoList,type);
                        JSONObject jsonObj = new JSONObject(intent.getStringExtra("coupn"));
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(intent.getStringExtra("coupn"));

                        Coupn userNoteVO = gson.fromJson(mJson, Coupn.class);
                       // l.add(userNoteVO);
                       // Collections.reverse(l);
                        //String jsonOfuserVOList = gson.toJson(l);
                      //  pref.setUserVOList(jsonOfuserVOList);
                        Intent i = new Intent(getApplicationContext(), UserAcceptRejectRequestActivity.class);

                        i.putExtra("desc",jsonObj.getString("desc"));
                        i.putExtra("date", jsonObj.getString("date"));
                        i.putExtra("amount", jsonObj.getString("amount"));
                        i.putExtra("area", jsonObj.getString("area"));
                        i.putExtra("category", jsonObj.getString("category"));
                        i.putExtra("id", jsonObj.getString("id"));

                        startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   // UserNoteVO userNoteVO = (UserNoteVO)intent.getSerializableExtra("userNoteVO");

                   /* Intent i = new Intent(getApplicationContext(), ShowMessagesActivity.class);
                    startActivity(i);*/
               // }
            }
        }
    }

    private void displayFirebaseRegId() {

        String regId = pref.getAppRegId();

        Toast.makeText(getApplicationContext(), pref.getLatitude(), Toast.LENGTH_LONG).show();

        if (!TextUtils.isEmpty(regId))
            Toast.makeText(getApplicationContext(), regId, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Firebase Reg Id is not received yet!", Toast.LENGTH_LONG).show();
    }
}


