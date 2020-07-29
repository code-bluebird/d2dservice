package in.shaanu.d2dservice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by EKENE on 7/23/2017.
 */

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {


    public static final String KEY_NAME = "p_name";
    public static final String KEY_NUMBER = "p_number";
    public static final String KEY_FEE = "p_fee";

    // we define a list from the DevelopersList java class

    private List<DevelopersList> developersLists;
    private Context context;

    public DevelopersAdapter(List<DevelopersList> developersLists, Context context) {

        // generate constructors to initialise the List and Context objects

        this.developersLists = developersLists;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developers_list,  null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final DevelopersList developersList = developersLists.get(position);
        holder.p_name.setText(developersList.getP_name());
        holder.p_number.setText(developersList.getP_number());
        holder.p_fee.setText(developersList.getP_fee());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevelopersList developersList1 = developersLists.get(position);
                //Intent skipIntent = new Intent(v.getContext(), ProviderProfileActivity.class);
               /* skipIntent.putExtra(KEY_NAME, developersList1.getP_name());
                skipIntent.putExtra(KEY_NUMBER, developersList1.getP_number());
                skipIntent.putExtra(KEY_FEE, developersList1.getP_fee());
                v.getContext().startActivity(skipIntent);*/
            }
        });

    }

    @Override

    //return the size of the listItems (developersList)

    public int getItemCount() {
        return developersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        public TextView p_name;
        public TextView p_number;
        public TextView p_fee;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects

            p_name = itemView.findViewById(R.id.p_name);
            p_number = itemView.findViewById(R.id.p_number);
            p_fee =  itemView.findViewById(R.id.p_fee);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

    }
}
