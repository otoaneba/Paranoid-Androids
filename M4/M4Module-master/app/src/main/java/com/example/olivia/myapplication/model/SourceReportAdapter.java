package com.example.olivia.myapplication.model;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.olivia.myapplication.model.SourceReport;

import com.example.olivia.myapplication.controller.R;

import java.util.ArrayList;
import static com.example.olivia.myapplication.model.RetrieveSourceReportData.reports;


/**
 * Created by Olivia on 4/21/2017.
 */

public class SourceReportAdapter extends RecyclerView.Adapter<SourceReportAdapter.ViewHolder> {

    private ArrayList<SourceReport> mReportList;
    private Context mContext;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView reportNum, creator, location, time;

        //public TextView date;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View v) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(v);
            v.setOnClickListener(this);
            reportNum =  (TextView) itemView.findViewById(R.id.reportNum_TextView);
            creator = (TextView) itemView.findViewById(R.id.creater_TextView);
            location = (TextView) itemView.findViewById(R.id.location_TextView);
            time = (TextView) itemView.findViewById(R.id.time_TextView);
            //date = (TextView) itemView.findViewById(R.id.date_TextView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public SourceReportAdapter(Context context, ArrayList<SourceReport> myReports) {
        mReportList = myReports;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public SourceReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View sourceReportView = inflater.inflate(R.layout.report_item_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(sourceReportView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SourceReportAdapter.ViewHolder viewHolder, int position) {
        SourceReport report = mReportList.get(position);

        TextView reportNumTV = viewHolder.reportNum;
        reportNumTV.setText("" + report.getReportNumber());
        TextView createrTV = viewHolder.creator;
        createrTV.setText(report.getCreator());
        TextView locationTV = viewHolder.location;
        locationTV.setText(report.getLocation());
        TextView timeTV =  viewHolder.time;
        timeTV.setText(report.getTime());
    }

    @Override
    public int getItemCount() {
        return mReportList.size();
    }
}