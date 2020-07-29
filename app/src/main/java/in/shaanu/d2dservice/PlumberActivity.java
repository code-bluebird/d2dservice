package in.shaanu.d2dservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

public class PlumberActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://servicesd2d.000webhostapp.com/shopper/public/index.php/api/fetch_plumber";
    String url = "https://servicesd2d.000webhostapp.com/";
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DevelopersList> developersLists;

    String total = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        developersLists = new ArrayList<>();
        sharedPreferences = getSharedPreferences(
                "spuserfile", 0);
        String area_id = sharedPreferences.getString("area_id", null);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //loadUrlData();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        FetchApi fetchApi = restAdapter.create(FetchApi.class);
        fetchApi.fetch(area_id,
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
                                    Toast.makeText(PlumberActivity.this, "No Service Available in this area.", Toast.LENGTH_SHORT).show();
                                    total = "";
                                } else if (sperror.equalsIgnoreCase("success")) {
                                    //Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                    JSONArray array = jsonObject.getJSONArray("data");
                                    for (int i = 0; i < array.length(); i++){

                                        JSONObject jo = array.getJSONObject(i);

                                        DevelopersList developers = new DevelopersList(jo.getString("p_name"), jo.getString("p_number"),
                                                jo.getString("p_fee"));
                                        developersLists.add(developers);

                                    }
                                    adapter = new DevelopersAdapter(developersLists, getApplicationContext());
                                    recyclerView.setAdapter(adapter);

                                }
                                else {
                                    Toast.makeText(PlumberActivity.this, sperror, Toast.LENGTH_SHORT).show();
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
