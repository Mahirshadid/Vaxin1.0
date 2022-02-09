package com.mahirshadid.vax.reg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.R;
import com.mahirshadid.vax.referencedivision;

import java.util.List;

public class adapterCTG extends RecyclerView.Adapter<adapterCTG.viewadapter> {

    private List<referencedivision> list;
    private Context context;
    private String locationn;

    public adapterCTG(List<referencedivision> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_itemsctg1,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        referencedivision item=list.get(position);

        holder.ctgname.setText(item.getRefname());
        holder.ctgbirth.setText(item.getRefbirth());
        holder.ctgmob.setText(item.getRefmob());
        holder.ctgnid.setText(item.getRefnid());
        holder.ctgloc.setText(item.getRefloc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView ctgname,ctgbirth,ctgmob,ctgloc,ctgnid;

        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            ctgname=itemView.findViewById(R.id.ctgname);
            ctgbirth=itemView.findViewById(R.id.ctgbirth);
            ctgloc=itemView.findViewById(R.id.ctgloc);
            ctgmob=itemView.findViewById(R.id.ctgmob);
            ctgnid=itemView.findViewById(R.id.ctgnid);
        }
    }
}
