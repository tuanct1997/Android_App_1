/*
package com.example.user.finalprojectapp1.Setting;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.user.finalprojectapp1.Beacon.BeaconAdapter;
import com.example.user.finalprojectapp1.Model.HomeModel;
import com.example.user.finalprojectapp1.R;

import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private BeaconAdapter.onItemClickListener onItemClickListener;
    private List<HomeModel> homeModels;




    public HomeAdapter(List<HomeModel> homeModels) {
        this.homeModels = homeModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beacon, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((HomeAdapter.ViewHolder) holder).bind(position);
    }



    @Override
    public int getItemCount() {
        return homeModels.size();
    }

    public void update(List<HomeModel> homeModels){
        this.homeModels = homeModels;
        this.notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onClick(int position);
    }
    public void setOnItemClickListener(BeaconAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView home_name;
        private LinearLayout layout;

        private ViewHolder(View view) {
            super(view);
            layout = (LinearLayout) view.findViewById(R.id.item_beacon_layout);
            home_name = (TextView) view.findViewById(R.id.item_beacon_major_and_minor);
        }

        public void bind(final int position){
            home_name.setText("");

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) onItemClickListener.onClick(position);
                }
            });
        }
    }
}
*/
