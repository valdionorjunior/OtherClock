package br.com.rodrigues.junior.otherclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by junior on 24/01/18.
 */

public class TimeZoneAdapter extends RecyclerView.Adapter<TimeZoneAdapter.NumberViewHolder>{

    private  int mNumerItems;

    public TimeZoneAdapter(int numberOfItems){
        mNumerItems = numberOfItems;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.time_zone_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mNumerItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder{
        private TextView myListItemTimeZone;


        public NumberViewHolder(View itemView) {
            super(itemView);
            myListItemTimeZone = (TextView) itemView.findViewById(R.id.tv_time_zone_item);
        }

        public void bind(int listIndex){
            myListItemTimeZone.setText(String.valueOf(listIndex));
        }
    }
}
