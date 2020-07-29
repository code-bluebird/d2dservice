package in.shaanu.d2dservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
TextView register_l;
    String token = "uniquetoken";
    String url = "https://servicesd2d.000webhostapp.com/";

    Button btn_login;
    EditText u_mobile, u_password;
    SharedPreferences sharedPreferences;
    String total = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        u_mobile=findViewById(R.id.l_mobile);
        u_password=findViewById(R.id.l_password);
        btn_login=findViewById(R.id.btn_login);
        sharedPreferences = getSharedPreferences("spuserfile", 0);
        final SharedPreferences.Editor ed;
        ed = sharedPreferences.edit();
        register_l=findViewById(R.id.register_l);
        register_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (u_mobile.getText().toString().equals("") ||
                        u_password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
                } else {
                    RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
                    loginApi Login = restAdapter.create(loginApi.class);
                    Login.login(
                            token,
                            u_mobile.getText().toString().trim(),
                            u_password.getText().toString().trim(),
                            new Callback<Response>() {
                                @Override
                                public void success(Response response, Response response2) {
                                    String output = "";
                                    BufferedReader br;
                                    try {
                                        br = new BufferedReader(new InputStreamReader(response.getBody().in()));

                                        while (output != null) {
                                            output = br.readLine();
                                            total += output;

                                        }


                                        String sperror, sp_u_mobile, sp_u_password, sp_u_name, sp_u_email;
                                        try {
                                            JSONObject jsonObject = new JSONObject(total);
                                            sperror = jsonObject.getString("error");


                                            if (sperror.equalsIgnoreCase("invalid_email_password")) {
                                                Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                                total = "";
                                            } else if (sperror.equalsIgnoreCase("success")) {
                                                //Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                JSONObject jo = jsonArray.getJSONObject(0);
                                                sp_u_name = jo.getString("u_name");
                                                sp_u_mobile = jo.getString("u_mobile");
                                                sp_u_email = jo.getString("u_email");

                                                sp_u_password = jo.getString("u_password");
                                                ed.putString("u_name", sp_u_name);
                                                ed.putString("u_mobile", sp_u_mobile);
                                                ed.putString("u_email",sp_u_email);

                                                ed.putString("u_password", sp_u_password);
                                                ed.commit();
                                                Intent intent = new Intent(LoginActivity.this, PinActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                //Toast.makeText(LoginActivity.this, sperror, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //Toast.makeText(AdminLoginActivity.this, total, Toast.LENGTH_SHORT).show();


                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
                }
            }
        });
    }

    /*****Alert Dailog box******/

    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Are you sure want to EXIT ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Login First", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
