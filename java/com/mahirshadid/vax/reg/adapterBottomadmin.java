package com.mahirshadid.vax.reg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.Dataadmin;
import com.mahirshadid.vax.R;

import java.util.List;

public class adapterBottomadmin extends RecyclerView.Adapter<adapterBottomadmin.viewadapter> {

    private List<Dataadmin> list;
    private Context context;
    private String locationn;

    public adapterBottomadmin(List<Dataadmin> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_itemsforbottomshowvacdate,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        Dataadmin item=list.get(position);

        holder.ovdate.setText(item.getDate());
        holder.ovmob.setText(item.getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView ovmob,ovdate;

        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            ovdate=itemView.findViewById(R.id.ovdate);
            ovmob=itemView.findViewById(R.id.ovmob);

        }
    }
}
