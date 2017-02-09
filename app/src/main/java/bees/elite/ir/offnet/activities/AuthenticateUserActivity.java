package bees.elite.ir.offnet.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bees.elite.ir.offnet.R;
import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.config.RestClient;
import bees.elite.ir.offnet.user.UserAuthTokenModel;
import bees.elite.ir.offnet.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

;

public class AuthenticateUserActivity extends AppCompatActivity {
    private static final String TAG = AuthenticateUserActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private Activity activity;

    EditText username;
    EditText password;
    Button login;
    // ProgressBar Pbar;
    private PrefManager pref;
    final RestClient rc = RestClient.getInstance();
    private View layoutIntroScreen;
    private View layoutContent;

    private View layoutContentSelectShop;
    private boolean layoutCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG);
        activity = this;

        progressDialog = Utils.generateProgressDialog(this, false);
        init();


    }
    private void init(){
        //if (!GeneralNotificationApp.getInstance().isDataConnected()) {
        progressDialog.hide();
        Timber.d("No network connection.");

        initSplashLayout();

        // Skip intro screen.
        new CountDownTimer(3000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                layoutContent.setVisibility(View.VISIBLE);
                layoutIntroScreen.setVisibility(View.GONE);
            }
        }.start();


        // Show retry button.
        //layoutContentNoConnection.setVisibility(View.VISIBLE);
        layoutContentSelectShop.setVisibility(View.VISIBLE);
   /* } else {
        progressDialog.hide();


            }*/
    }

    private void initSplashLayout() {
        if (!layoutCreated) {
            setContentView(R.layout.activity_authenticate_user);

            layoutContent = findViewById(R.id.splash_content);
            layoutIntroScreen = findViewById(R.id.splash_intro_screen);
            // layoutContentNoConnection = findViewById(R.id.splash_content_no_connection);
            layoutContentSelectShop = findViewById(R.id.splash_content_select_shop);
            username = (EditText) findViewById(R.id.rm_username);
            password = (EditText) findViewById(R.id.rm_password);
            username.setText("09356577055");
            login = (Button) findViewById(R.id.rm_btn_login);
            // Pbar = (ProgressBar)findViewById(R.id.progressBar1);
            pref = new PrefManager(this);
            //  Pbar.setVisibility(View.GONE);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.show();
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            init();
                        }
                    }, 600);
                    //Pbar.setVisibility(View.VISIBLE);

                    if (pref.getAppRegId()!=null && !pref.getAppRegId().equals("")) {
                        Call<String> call = rc.getApi().authenticate(username.getText().toString().trim(), password.getText().toString().trim(), pref.getAppRegId());

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful()) {
                                    String res = response.body();
                                    //check beshe ke user pass doroste
                                    if (res!=null) {
                                        if (!res.equals("")) {
                                            pref.setUserAuthenticateToken(res);
                                            //Intent intent = new Intent(getApplicationContext(), ShowMessagesActivity.class);
                                            pref.setUserVOList("");
                                           // startActivity(intent);
                                            //Pbar.setVisibility(View.GONE);
                                            Toast.makeText(AuthenticateUserActivity.this, "شما با موفقیت وارد برنامه شدید", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(AuthenticateUserActivity.this, "یوزرنیم یا پسورد غلط است", Toast.LENGTH_SHORT).show();
                                        // Pbar.setVisibility(View.GONE);
                                    }
                                } else {

                                    Toast.makeText(AuthenticateUserActivity.this, "not success", Toast.LENGTH_SHORT).show();
                                    // Pbar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(AuthenticateUserActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // Pbar.setVisibility(View.GONE);
                        //device id is null
                    }
                }
            });
            layoutCreated = true;
             } else {
            Timber.d("%s screen is already created.", this.getClass().getSimpleName());
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_authenticate_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
