//package com.example.olivia.myapplication.model;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.olivia.myapplication.controller.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static android.content.Context.LAYOUT_INFLATER_SERVICE;
//import static com.example.olivia.myapplication.model.RetrievePurityReportData.reports;
//
//
///**
// * Created by Olivia on 4/21/2017.
// */
//
//public class SourceReportAdapter extends RecyclerView.Adapter<SourceReportAdapter.ViewHolder> {
//
//    private ArrayList<HashMap<String, String>> mReportList;
//    private Context mContext;
//
//    // Provide a direct reference to each of the views within a data item
//    // Used to cache the views within the item layout for fast access
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        public TextView mTextView;
//        public Button messageButton;
//
//        // We also create a constructor that accepts the entire item row
//        // and does the view lookups to find each subview
//        public ViewHolder(View itemView) {
//            // Stores the itemView in a public final member variable that can be used
//            // to access the context from any ViewHolder instance.
//            super(itemView);
//
//            mTextView = (TextView) itemView.findViewById(R.id.textView1);
//        }
//    }
//
//    public SourceReportAdapter(Context context, ArrayList<HashMap<String, String>> myReports) {
//        mReportList = myReports;
//    }
//
//    private Context getContext() {
//        return mContext;
//    }
//
//    @Override
//    public SourceReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.report_recycler_layout, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setText(mReportList);
//    }
//}