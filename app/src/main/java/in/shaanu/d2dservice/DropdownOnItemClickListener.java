package in.shaanu.d2dservice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class DropdownOnItemClickListener implements AdapterView.OnItemClickListener {

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // get the context and main activity to access variables
        Context mContext = v.getContext();
        PinActivity pinActivity = ((PinActivity) mContext);

        // add some animation when a list item was clicked
        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(10);
        v.startAnimation(fadeInAnimation);

        // dismiss the pop up
        pinActivity.popupWindowArea.dismiss();

        // get the text and set it as the button text
        String selectedItemText = ((TextView) v).getText().toString();
        pinActivity.buttonShowDropDown.setText(selectedItemText);

        // get the id
        String selectedItemTag = ((TextView) v).getTag().toString();
        Toast.makeText(mContext, "Area ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(mContext, DashboardActivity.class);
        intent.putExtra("area_id", selectedItemTag);
        pinActivity.startActivity(intent);
        pinActivity.finish();

    }
}
