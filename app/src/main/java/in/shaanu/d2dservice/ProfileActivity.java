package in.shaanu.d2dservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button logout;
    TextView username, email,mobile, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        mobile = (TextView) findViewById(R.id.mobile);
        pincode = (TextView) findViewById(R.id.pincode);
        logout=(Button) findViewById(R.id.logout);


        sharedPreferences = getSharedPreferences(
                "spuserfile", 0);
        String u_name = sharedPreferences.getString("u_name", null);
        username.append(u_name);
        String u_email = sharedPreferences.getString("u_email", null);
        email.append(u_email);
        String u_mobile = sharedPreferences.getString("u_mobile", null);
        mobile.append(u_mobile);
        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,PinActivity.class);
                startActivity(intent);
                finish();
            }
        });


        /*String pincode = sharedPreferences.getString("u_pincode", null);
        username.append(pincode);*/



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("spuserfile",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
