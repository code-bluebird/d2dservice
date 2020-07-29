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

public class DevelopersAdapter2 extends RecyclerView.Adapter<DevelopersAdapter2.ViewHolder> {


    public static final String KEY_NAME = "c_name";
    public static final String KEY_NUMBER = "c_number";
    public static final String KEY_FEE = "c_fee";

    // we define a list from the DevelopersList java class

    private List<DevelopersList2> developersLists2;
    private Context context;

    public DevelopersAdapter2(List<DevelopersList2> developersLists2, Context context) {

        // generate constructors to initialise the List and Context objects

        this.developersLists2 = developersLists2;
        this.context = context;

    }

    @Override
    public DevelopersAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developers_list2,  null);
        return new DevelopersAdapter2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DevelopersAdapter2.ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final DevelopersList2 developersList2 = developersLists2.get(position);
        holder.c_name.setText(developersList2.getC_name());
        holder.c_number.setText(developersList2.getC_number());
        holder.c_fee.setText(developersList2.getC_fee());



        holder.linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevelopersList2 developersList1 = developersLists2.get(position);
                /*Intent skipIntent = new Intent(v.getContext(), ProviderProfileActivity.class);
                skipIntent.putExtra(KEY_NAME, developersList1.getC_name());
                skipIntent.putExtra(KEY_NUMBER, developersList1.getC_number());
                skipIntent.putExtra(KEY_FEE, developersList1.getC_fee());
                v.getContext().startActivity(skipIntent);*/
            }
        });

    }

    @Override

    //return the size of the listItems (developersList)

    public int getItemCount() {
        return developersLists2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        public TextView c_name;
        public TextView c_number;
        public TextView c_fee;
        public LinearLayout linearLayout2;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects

            c_name = itemView.findViewById(R.id.c_name);
            c_number = itemView.findViewById(R.id.c_number);
            c_fee =  itemView.findViewById(R.id.c_fee);
            linearLayout2 = itemView.findViewById(R.id.linearLayout2);
        }

    }
}
