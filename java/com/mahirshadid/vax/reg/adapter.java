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
import com.mahirshadid.vax.adminaction1;
import com.mahirshadid.vax.admindate;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewadapter> {

    private List<Data> list;
    private Context context;
    private String category;

    public adapter(List<Data> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public viewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reg_items,parent, false);
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

        holder.rbutton.setOnClickListener(v -> {
            Intent intent=new Intent(context, adminaction1.class);
            intent.putExtra("nam",item.getNam());
            intent.putExtra("pass",item.getPass());
            intent.putExtra("mob",item.getMob());
            intent.putExtra("nid",item.getNid());
            intent.putExtra("loc",item.getLoc());
            intent.putExtra("birth",item.getBirth());
            intent.putExtra("key",item.getKey());
            intent.putExtra("category",category);

            context.startActivity(intent);

        });

        holder.givetoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, admindate.class);
                intent.putExtra("mob",item.getMob());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewadapter extends RecyclerView.ViewHolder {

        private TextView rcat,rname,rbirth,rpass,rmob,rloc,rnid;
        private Button rbutton,givetoken;

        public viewadapter(@NonNull View itemView) {
            super(itemView);
            
            rname=itemView.findViewById(R.id.rname);
            rbirth=itemView.findViewById(R.id.rbirth);
            rpass=itemView.findViewById(R.id.rpass);
            rmob=itemView.findViewById(R.id.rmob);
            rloc=itemView.findViewById(R.id.rloc);
            rnid=itemView.findViewById(R.id.rnid);

            rbutton=itemView.findViewById(R.id.rbutton);
            givetoken=itemView.findViewById(R.id.givetoken);



        }
    }
}
