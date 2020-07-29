package in.shaanu.d2dservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DevelopersAdapter3 extends RecyclerView.Adapter<DevelopersAdapter3.ViewHolder> {


    public static final String KEY_NAME = "l_name";
    public static final String KEY_NUMBER = "l_number";
    public static final String KEY_FEE = "l_fee";

    // we define a list from the DevelopersList java class

    private List<DevelopersList3> developersLists3;
    private Context context;

    public DevelopersAdapter3(List<DevelopersList3> developersLists3, Context context) {

        // generate constructors to initialise the List and Context objects

        this.developersLists3 = developersLists3;
        this.context = context;

    }

    @Override
    public DevelopersAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developers_list3,  null);
        return new DevelopersAdapter3.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DevelopersAdapter3.ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final DevelopersList3 developersList3 = developersLists3.get(position);
        holder.l_name.setText(developersList3.getL_name());
        holder.l_number.setText(developersList3.getL_number());
        holder.l_fee.setText(developersList3.getL_fee());



        holder.linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevelopersList3 developersList1 = developersLists3.get(position);
               /* Intent skipIntent = new Intent(v.getContext(), ProviderProfileActivity.class);
                skipIntent.putExtra(KEY_NAME, developersList1.getL_name());
                skipIntent.putExtra(KEY_NUMBER, developersList1.getL_number());
                skipIntent.putExtra(KEY_FEE, developersList1.getL_fee());
                v.getContext().startActivity(skipIntent);*/
            }
        });

    }

    @Override

    //return the size of the listItems (developersList)

    public int getItemCount() {
        return developersLists3.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        public TextView l_name;
        public TextView l_number;
        public TextView l_fee;
        public LinearLayout linearLayout3;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects

            l_name = itemView.findViewById(R.id.l_name);
            l_number = itemView.findViewById(R.id.l_number);
            l_fee =  itemView.findViewById(R.id.l_fee);
            linearLayout3 = itemView.findViewById(R.id.linearLayout3);
        }

    }
}
