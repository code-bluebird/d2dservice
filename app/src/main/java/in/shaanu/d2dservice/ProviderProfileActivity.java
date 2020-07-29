package in.shaanu.d2dservice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProviderProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        TextView userNameTextView = (TextView) findViewById(R.id.usernameTextView);


        Intent intent = getIntent();
        final String userName = intent.getStringExtra(DevelopersAdapter.KEY_NAME);
        final String userName2 = intent.getStringExtra(DevelopersAdapter2.KEY_NAME);
        final String userName3 = intent.getStringExtra(DevelopersAdapter3.KEY_NAME);


        userNameTextView.setText(userName);
        userNameTextView.setText(userName2);
        userNameTextView.setText(userName3);


    }
}
