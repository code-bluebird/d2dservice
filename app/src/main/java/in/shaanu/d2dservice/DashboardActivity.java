package in.shaanu.d2dservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    TextView profile,textprofile,textplumber,textcarpenter,textlaundry;
    ImageView imageprofilemain,imageprofile,imageplumber,imagecarpenter,imagelaundry;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //data came from pin activity
        Intent intent = getIntent();
        String str = intent.getStringExtra("area_id");


        sharedPreferences = this.getSharedPreferences(
                "spuserfile", 0);
        final SharedPreferences.Editor ed;
        ed = sharedPreferences.edit();
        ed.putString("area_id",str);
        ed.commit();
        //final String game = sharedPreferences.getString("area_id", null);
        //Toast.makeText(this,game , Toast.LENGTH_SHORT).show();
        //getActivity().setTitle("Household Services");
        profile = (TextView) findViewById(R.id.profile);
        textprofile = (TextView) findViewById(R.id.textprofile);
        textplumber = (TextView) findViewById(R.id.textplumber);
        textcarpenter = (TextView) findViewById(R.id.textcarpenter);
        textlaundry = (TextView) findViewById(R.id.textlaundry);

        imageprofilemain = (ImageView) findViewById(R.id.imageprofilemain);
        imageprofile = (ImageView) findViewById(R.id.imageprofile);
        imageplumber = (ImageView) findViewById(R.id.imageplumber);
        imagecarpenter = (ImageView) findViewById(R.id.imagecarpenter);
        imagelaundry = (ImageView) findViewById(R.id.imagelaundry);
        sharedPreferences=this.getSharedPreferences("spuserfile",0);
        final String name=sharedPreferences.getString("u_name",null);
        profile.append(name);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        textprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        textplumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,PlumberActivity.class);
                startActivity(intent);
            }
        });
        textcarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,CarpenterActivity.class);
                startActivity(intent);
            }
        });
        textlaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,LaundryActivity.class);
                startActivity(intent);
            }
        });


        imageprofilemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        imageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        imageplumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,PlumberActivity.class);
                startActivity(intent);
            }
        });
        imagecarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,CarpenterActivity.class);
                startActivity(intent);
            }
        });
        imagelaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,LaundryActivity.class);
                startActivity(intent);
            }
        });


    }

    /*****Alert Dailog box******/

    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        builder.setMessage("Are you sure want to EXIT ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
