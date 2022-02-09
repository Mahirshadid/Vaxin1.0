package com.mahirshadid.vax.reg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.Dataofdatesearch2;
import com.mahirshadid.vax.R;

import java.util.List;

public class adapterDATESEARCH2 extends RecyclerView.Adapter<adapterDATESEARCH2.viewadapter> {

    private List<Dataofdatesearch2> list;
    private Context context;

    public adapterDATESEARCH2(List<Dataofdatesearch2> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_items_datesearch,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        Dataofdatesearch2 item=list.get(position);

        holder.datenid.setText(item.getNid());
        holder.dateloc.setText(item.getLocation());
        holder.datedose1.setText(item.getDate1());
        holder.datevac.setText(item.getVac());
        holder.datedose2.setText(item.getDate2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView datenid,dateloc,datevac,datedose1,datedose2;

        public viewadapter(@NonNull View itemView) {
            super(itemView);

            datenid=itemView.findViewById(R.id.datenid);
            dateloc=itemView.findViewById(R.id.dateloc);
            datevac=itemView.findViewById(R.id.datevac);
            datedose1=itemView.findViewById(R.id.dose1);
            datedose2=itemView.findViewById(R.id.dose2);

        }
    }
}
