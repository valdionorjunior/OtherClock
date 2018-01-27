package br.com.rodrigues.junior.otherclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by junior on 24/01/18.
 */

public class TimeZoneAdapter extends RecyclerView.Adapter<TimeZoneAdapter.TimeZoneAdapterViewHolder>{


    private String[] mTimeZones;

    public TimeZoneAdapter(String [] Timezone){
        setTimeZones(Timezone);
    }


    public class TimeZoneAdapterViewHolder extends RecyclerView.ViewHolder{
        private final TextView myListItemTimeZone;


        public TimeZoneAdapterViewHolder(View view) {
            super(view);
            myListItemTimeZone = (TextView) view.findViewById(R.id.tv_time_zone_item);
        }

    }

    public void setTimeZones(String[] mTimeZones) {
        this.mTimeZones = mTimeZones;
        notifyDataSetChanged();
    }

    @Override
    public TimeZoneAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.time_zone_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new TimeZoneAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TimeZoneAdapterViewHolder timeZoneAdapterViewHolder, int position) {
        timeZoneAdapterViewHolder.myListItemTimeZone.setText(mTimeZones[position]);

    }

    @Override
    public int getItemCount() {
        if(mTimeZones==null){
            return 0;
        }
        return mTimeZones.length;
    }

}
