package com.mahirshadid.vax.reg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.R;
import com.mahirshadid.vax.othervaccineData;

import java.util.List;

public class adapterOthervac extends RecyclerView.Adapter<adapterOthervac.viewadapter> {

    private List<othervaccineData> list;
    private Context context;
    private String categoryothervac;

    public adapterOthervac(List<othervaccineData> list, Context context, String categoryothervac) {
        this.list = list;
        this.context = context;
        this.categoryothervac = categoryothervac;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_items2,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        othervaccineData item=list.get(position);

        holder.oname.setText(item.getName());
        holder.obirth.setText(item.getBirthyear());
        holder.omob.setText(item.getMobile());
        holder.oloc.setText(item.getLocation());
        holder.otime.setText(item.getTime());
        holder.oeme.setText(item.getEmergency());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView oname,obirth,omob,oloc,otime,oeme;

        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            oname=itemView.findViewById(R.id.oname);
            obirth=itemView.findViewById(R.id.obirth);
            omob=itemView.findViewById(R.id.omob);
            oloc=itemView.findViewById(R.id.oloc);
            otime=itemView.findViewById(R.id.otime);
            oeme=itemView.findViewById(R.id.oeme);
        }
    }
}
