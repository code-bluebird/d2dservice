package in.shaanu.d2dservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

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

public class RegisterActivity extends AppCompatActivity {
    EditText u_name,u_mobile,u_email,u_password,c_password;
    Button btn_register;
    String text,notice;
    AwesomeValidation awesomeValidation;
    String token = "uniquetoken";
    String url = "https://servicesd2d.000webhostapp.com/";
    String total = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        updateUI();
    }
        private void updateUI() {
            u_email = (EditText) findViewById(R.id.u_email);
            u_password = (EditText) findViewById(R.id.u_password);
            c_password = (EditText) findViewById(R.id.c_password);
            u_name = (EditText) findViewById(R.id.u_name);
            u_mobile = (EditText) findViewById(R.id.u_mobile);

            btn_register = (Button) findViewById(R.id.btn_register);

            String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{6,}";
            String regexPhone = "(0/91)?[6-9][0-9]{8,9}";
            awesomeValidation.addValidation(RegisterActivity.this,R.id.u_name,"[a-zA-Z\\s]+",R.string.name);
            awesomeValidation.addValidation(RegisterActivity.this,R.id.u_email, android.util.Patterns.EMAIL_ADDRESS, R.string.email);
            awesomeValidation.addValidation(RegisterActivity.this,R.id.u_mobile, regexPhone, R.string.mobile);
            awesomeValidation.addValidation(RegisterActivity.this,R.id.u_password,regexPassword,R.string.password);
            awesomeValidation.addValidation(RegisterActivity.this,R.id.c_password,R.id.u_password,R.string.cpassword);

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (awesomeValidation.validate())
                    {
                        //Toast.makeText(RegisterActivity.this, "Data recieved", Toast.LENGTH_SHORT).show();


                            RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(url).build();
                            final registerApi registerApi=restAdapter.create(registerApi.class);
                            registerApi.register(token,
                                    u_name.getText().toString().trim(),
                                    u_mobile.getText().toString().trim(),
                                    u_email.getText().toString().trim(),
                                    u_password.getText().toString().trim(),
                                    new Callback<Response>() {
                                        @Override
                                        public void success(Response response, Response response2) {
                                            String output="";
                                            BufferedReader br=null;
                                            try {
                                                br = new BufferedReader(new InputStreamReader(response.getBody().in()));

                                                while (output != null)
                                                {
                                                    output = br.readLine();
                                                    total += output;

                                                }
                                                try
                                                {
                                                    JSONObject jsonObject = new JSONObject(total);

                                                    text = (String) jsonObject.get("error");
                                                    Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_SHORT).show();
                                                    if (text.equalsIgnoreCase("User Added"))
                                                    {
                                                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                                        startActivity(intent);
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(RegisterActivity.this, "Not registered! try again", Toast.LENGTH_SHORT).show();
                                                        total="";
                                                    }


                                                } catch (JSONException e)
                                                {
                                                    e.printStackTrace();
                                                }


                                        /*if (output.equalsIgnoreCase("success")){
                                            Intent intent=new Intent(AdminLoginActivity.this,AdminHomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                            editor.putString("mobile",adminmobile.getText().toString());
                                            editor.commit();
                                        }*/
                                            } catch (IOException e)
                                            {
                                                e.printStackTrace();
                                            }
                                            //Toast.makeText(AdminLoginActivity.this, total, Toast.LENGTH_SHORT).show();


                                        }

                                        @Override
                                        public void failure(RetrofitError error) {

                                        }
                                    });

                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
