package in.shaanu.d2dservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class PinActivity extends AppCompatActivity  {

    String TAG = "MainActivity.java";

    String popUpContents[];
    PopupWindow popupWindowArea;
    Button buttonShowDropDown;
    Button nxtbtn;
    SharedPreferences sharedPreferences;
    String s1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

       // nxtbtn = findViewById(R.id.nxtbtn);

        List<String> dogsList = new ArrayList<String>();
        dogsList.add("Shakti Khand 2::1");
        dogsList.add("Gyan Khand 1::2");
        dogsList.add("Nyay Khand 2::3");

        // convert to simple array
        popUpContents = new String[dogsList.size()];
        dogsList.toArray(popUpContents);


        // initialize pop up window
        popupWindowArea = popupWindowArea();


        // button on click listener

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.buttonShowDropDown:
                        // show the list view as dropdown
                        popupWindowArea.showAsDropDown(v, -5, 0);
                        break;
                }
            }
        };

        // our button
        buttonShowDropDown = (Button) findViewById(R.id.buttonShowDropDown);
        buttonShowDropDown.setOnClickListener(handler);
    }

    public PopupWindow popupWindowArea() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewDogs = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewDogs.setAdapter(dogsAdapter(popUpContents));

        // set the item click listener
        listViewDogs.setOnItemClickListener(new DropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(500);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);

        return popupWindow;
    }

    /*
     * adapter where the list values will be set
     */
    private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dogsArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                // visual settings for the list item
                TextView listItem = new TextView(PinActivity.this);

                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(20);
                listItem.setPadding(5, 10, 5, 10);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

        return adapter;
    }


}