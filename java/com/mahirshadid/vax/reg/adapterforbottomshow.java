package com.mahirshadid.vax.reg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahirshadid.vax.Data;
import com.mahirshadid.vax.R;
import com.mahirshadid.vax.updatevaccinedatewhengiven;
import com.mahirshadid.vax.updatevaccinedateseconddose;

import java.util.List;

public class adapterforbottomshow extends RecyclerView.Adapter<adapterforbottomshow.viewadapter> {

    private List<Data> list;
    private Context context;
    private String category;

    public adapterforbottomshow(List<Data> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_itemsbottomshow,parent, false);
        return new viewadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewadapter holder, int position) {

        Data item=list.get(position);

        holder.rname.setText(item.getNam());
        holder.rbirth.setText(item.getBirth());
        holder.rpass.setText(item.getPass());
        holder.rmob.setText(item.getMob());
        holder.rloc.setText(item.getLoc());
        holder.rnid.setText(item.getNid());

        holder.gotobutton.setOnClickListener(v -> {
            Intent intent=new Intent(context, updatevaccinedatewhengiven.class);
            intent.putExtra("nid",item.getNid());
            intent.putExtra("loc",item.getLoc());

            context.startActivity(intent);

        });

        holder.gotobutton2.setOnClickListener(v -> {
            Intent intent=new Intent(context, updatevaccinedateseconddose.class);
            intent.putExtra("nid",item.getNid());
            intent.putExtra("loc",item.getLoc());

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView rcat,rname,rbirth,rpass,rmob,rloc,rnid;
        private Button gotobutton,gotobutton2;

        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            rname=itemView.findViewById(R.id.bname);
            rbirth=itemView.findViewById(R.id.bbirth);
            rpass=itemView.findViewById(R.id.bpass);
            rmob=itemView.findViewById(R.id.bmob);
            rloc=itemView.findViewById(R.id.bloc);
            rnid=itemView.findViewById(R.id.bnid);

            gotobutton=itemView.findViewById(R.id.bbutton);
            gotobutton2=itemView.findViewById(R.id.bbutton2);




        }
    }
}
