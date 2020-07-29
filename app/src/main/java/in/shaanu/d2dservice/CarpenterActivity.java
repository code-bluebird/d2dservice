package in.shaanu.d2dservice;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CarpenterActivity extends AppCompatActivity {

    String url = "https://servicesd2d.000webhostapp.com/";
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DevelopersList2> developersLists2;
    String total = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpenter);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        developersLists2 = new ArrayList<>();
        sharedPreferences = getSharedPreferences(
                "spuserfile", 0);
        String area_id = sharedPreferences.getString("area_id", null);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //loadUrlData();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        FetchApi2 fetchApi2 = restAdapter.create(FetchApi2.class);
        fetchApi2.fetch(area_id,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        progressDialog.dismiss();
                        String output = "";
                        BufferedReader br;

                        try {
                            br = new BufferedReader(new InputStreamReader(response.getBody().in()));

                            while (output != null) {
                                output = br.readLine();
                                total += output;

                            }
                            //Toast.makeText(PlumberActivity.this, total, Toast.LENGTH_SHORT).show();
                            String sperror;
                            try {

                                JSONObject jsonObject = new JSONObject(total);
                                sperror = jsonObject.getString("error");


                                if (sperror.equalsIgnoreCase("invalid_email_password")) {
                                    Toast.makeText(CarpenterActivity.this, "No Service Available in this area.", Toast.LENGTH_SHORT).show();
                                    total = "";
                                } else if (sperror.equalsIgnoreCase("success")) {
                                    //Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                    JSONArray array = jsonObject.getJSONArray("data");
                                    for (int i = 0; i < array.length(); i++){

                                        JSONObject jo = array.getJSONObject(i);

                                        DevelopersList2 developers2 = new DevelopersList2(jo.getString("c_name"), jo.getString("c_number"),
                                                jo.getString("c_fee"));
                                        developersLists2.add(developers2);

                                    }
                                    adapter = new DevelopersAdapter2(developersLists2, getApplicationContext());
                                    recyclerView.setAdapter(adapter);

                                }
                                else {
                                    Toast.makeText(CarpenterActivity.this, sperror, Toast.LENGTH_SHORT).show();
                                }

                                //JSONArray array = jsonObject.getJSONArray("items");




                            } catch (JSONException e) {

                                e.printStackTrace();
                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });


    }


}
