package bees.elite.ir.offnet.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import at.markushi.ui.CircleButton;
import bees.elite.ir.offnet.R;
import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.config.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAcceptRejectPayRequestActivity extends AppCompatActivity {
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    TextView descOfCoupn;
    TextView dateOfCoupn;
    TextView amountOfCoupn;
    TextView area;
    TextView category;
    String coupnId;
String paymentReqId;
    private PrefManager pref;

    final RestClient rc = RestClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_accept_reject_request);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        pref = new PrefManager(this);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.repairman_ar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View v = findViewById(R.id.repairman_ar_content_repairman);
        descOfCoupn = (TextView) v.findViewById(R.id.user_ar_desc);
        dateOfCoupn = (TextView) v.findViewById(R.id.user_ar_date);
        amountOfCoupn = (TextView) v.findViewById(R.id.user_ar_amount);
        area = (TextView) v.findViewById(R.id.user_ar_area);
        category = (TextView) v.findViewById(R.id.user_ar_category);
        descOfCoupn.setText((String)getIntent().getSerializableExtra("descOfCoupn"));
        dateOfCoupn.setText((String)getIntent().getSerializableExtra("dateOfCoupn"));
        amountOfCoupn.setText((String) getIntent().getSerializableExtra("amountOfCoupn"));
        area.setText((String) getIntent().getSerializableExtra("area"));
        category.setText((String) getIntent().getSerializableExtra("category"));
        coupnId=(String) getIntent().getSerializableExtra("coupnId");
        paymentReqId=(String) getIntent().getSerializableExtra("paymentReqId");


        CircleButton acceptbtn = (CircleButton) v.findViewById(R.id.user_ar_accept_btn);
        CircleButton rejectbtn = (CircleButton) v.findViewById(R.id.user_ar_reject_btn);

        acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> call = rc.getApi().pay(pref.getUserName(), coupnId.toString().trim(), pref.getAppRegId());

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String res = response.body();
                            //check beshe ke user pass doroste
                            if (res!=null) {
                                if (res.equals("true")) {
                                    pref.setUserAuthenticateToken(res);
                                    Intent intent = new Intent(getApplicationContext(),UserSwitchStateActivity.class);
                                    pref.setUserVOList("");
                                    startActivity(intent);
                                    //Pbar.setVisibility(View.GONE);
                                    Toast.makeText(UserAcceptRejectPayRequestActivity.this, "  پرداخت با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(UserAcceptRejectPayRequestActivity.this, "خطا در   پرداخت", Toast.LENGTH_SHORT).show();
                                    // Pbar.setVisibility(View.GONE);
                                }
                            }
                            else {

                                Toast.makeText(UserAcceptRejectPayRequestActivity.this, "خطا در   پرداخت", Toast.LENGTH_SHORT).show();
                                // Pbar.setVisibility(View.GONE);
                            }

                        } else {

                            Toast.makeText(UserAcceptRejectPayRequestActivity.this, "not success", Toast.LENGTH_SHORT).show();
                            // Pbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UserAcceptRejectPayRequestActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> call = rc.getApi().reject(pref.getUserName(), coupnId.toString().trim(), pref.getAppRegId());

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String res = response.body();
                            //check beshe ke user pass doroste
                            if (res!=null) {
                                if (res.equals("true")) {
                                    pref.setUserAuthenticateToken(res);
                                    Intent intent = new Intent(getApplicationContext(),UserSwitchStateActivity.class);
                                    pref.setUserVOList("");
                                    startActivity(intent);
                                    //Pbar.setVisibility(View.GONE);
                                    Toast.makeText(UserAcceptRejectPayRequestActivity.this, " لغو پرداخت با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(UserAcceptRejectPayRequestActivity.this, "خطا در   لغو پرداخت", Toast.LENGTH_SHORT).show();
                                    // Pbar.setVisibility(View.GONE);
                                }
                            }
                            else {

                                Toast.makeText(UserAcceptRejectPayRequestActivity.this, "خطا در   لغو پرداخت", Toast.LENGTH_SHORT).show();
                                // Pbar.setVisibility(View.GONE);
                            }

                        } else {

                            Toast.makeText(UserAcceptRejectPayRequestActivity.this, "not success", Toast.LENGTH_SHORT).show();
                            // Pbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UserAcceptRejectPayRequestActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        /*PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("ریست لاگین");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("تعمیرکار");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("جانمایی");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("سیستم تعمیرکار");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("ورود یوزر");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("تغییر وضعیت تعمیرکار");
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("خروج");
//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        *//*item1,
                        new DividerDrawerItem(),*//*
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        *//*item4,
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem(),*//*
                        //item6
                        item7

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 1) {
                            pref.setIsRepairManActive(false);
                            pref.logOut();
                            Intent i = new Intent(RepairmanAcceptRejectRequestActivity.this, VerificationActivity.class);
                            startActivity(i);
                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 2) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 3) {
                            Intent intent = new Intent(getApplicationContext(), RastehActivity.class);
                            startActivity(intent);
                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 4) {
                            Intent intent = new Intent(getApplicationContext(), AuthenticateRepairmanActivity.class);
                            startActivity(intent);
                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 5) {
                            Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                            startActivity(intent);
                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 6) {
                            Intent intent = new Intent(getApplicationContext(), RepairmanSwitchStateActivity.class);
                            startActivity(intent);
                        }
                        else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 7) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .withHeader(R.layout.drawer_header)
                .build();*/
        /*mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.NOTIFY_REPAIRMAN_PUSH_NOTIFICATION)) {
                    // new notify repairman push notification is received
                    String message = intent.getStringExtra("message");
                    String requestId = intent.getStringExtra("requestId");
                    String serviceName = intent.getStringExtra("serviceName");
                    String serviceDescription = intent.getStringExtra("serviceDescription");
                    String address = intent.getStringExtra("address");
                    txtMessage.setText(message);
                    txtServiceName.setText(serviceName);
                    txtServiceDesc.setText(serviceDescription);
                    txtAddress.setText(address);
                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                }
            }
        };*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_repairman_accept_reject_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Intent i = getIntent();
        descOfCoupn.setText(i.getStringExtra("desc"));
        dateOfCoupn.setText(i.getStringExtra("date"));
        amountOfCoupn.setText(i.getStringExtra("amount"));
        area.setText(i.getStringExtra("area"));
        category.setText(i.getStringExtra("category"));
        coupnId = i.getStringExtra("id");

    }
}
