package bees.elite.ir.offnet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;



import bees.elite.ir.offnet.R;
import bees.elite.ir.offnet.config.PrefManager;
import bees.elite.ir.offnet.config.RestClient;

public class UserSwitchStateActivity extends AppCompatActivity {

    SwitchCompat sb;
    private PrefManager pref;
    final RestClient rc = RestClient.getInstance();
    ImageButton ib_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_switch_state);
        pref = new PrefManager(this);
        sb = (SwitchCompat) findViewById(R.id.btn_switch_state);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(pref.isNotificationActive()) {
            sb.setChecked(true);
            sb.setShowText(true);
        }
        else{
            sb.setChecked(false);
            sb.setShowText(false);
        }
        /*ib_home=(ImageButton) findViewById(R.id.ib_home) ;
        ib_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/
       // sb.setChecked(pref.isNotificationActive());//ui dorost kar nemikone
        sb.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton s, boolean isChecked) {
                if (s.isChecked()) {
                    Toast.makeText(UserSwitchStateActivity.this, " فعال سازی", Toast.LENGTH_SHORT).show();
                    pref.setIsNotificationActive(true);
                    //active


                } else {
                    Toast.makeText(UserSwitchStateActivity.this, "غیر فعال سازی", Toast.LENGTH_SHORT).show();
                    pref.setIsNotificationActive(false);
                    //deactive

                }
            }
        });

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("تنظیمات");
       PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("بستن برنامه");
        //PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("جانمایی");
       // PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("سیستم تعمیرکار");
       // PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("ورود یوزر");
       // PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("تغییر وضعیت تعمیرکار");
//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem()
                       // new DividerDrawerItem(),
                        /*item4,
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem(),*/
                        //item6

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 1) {

                        } else if (((PrimaryDrawerItem) drawerItem).getIdentifier() == 2) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .withHeader(R.layout.drawer_header)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  EventBus.getDefault().register(UserSwitchStateActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
       // EventBus.getDefault().unregister(UserSwitchStateActivity.this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repairman_switch_state, menu);
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
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


}
