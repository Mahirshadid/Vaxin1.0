package com.mahirshadid.vax.reg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.DataforAMBhos;
import com.mahirshadid.vax.R;

import java.util.List;

public class adapterHA extends RecyclerView.Adapter<adapterHA.viewadapter> {

    private List<DataforAMBhos> list;
    private Context context;
    private String category;

    public adapterHA(List<DataforAMBhos> list, Context context) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_items3,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        DataforAMBhos item = list.get(position);

        holder.haname.setText(item.getName());
        holder.halocation.setText(item.getLocation());
        holder.hatype.setText(item.getType());
        holder.hamobile.setText(item.getPhone());
        holder.haav.setText(item.getAvailable_hours());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView haname,halocation,hatype,hamobile,haav;


        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            haname=itemView.findViewById(R.id.haname);
            hamobile=itemView.findViewById(R.id.hamobile);
            halocation=itemView.findViewById(R.id.halocation);
            hatype=itemView.findViewById(R.id.hatype);
            haav=itemView.findViewById(R.id.haav);

        }
    }
}
