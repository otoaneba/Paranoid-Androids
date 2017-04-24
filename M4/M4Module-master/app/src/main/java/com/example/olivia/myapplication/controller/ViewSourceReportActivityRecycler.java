package com.example.olivia.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olivia.myapplication.model.RecyclerTouchListener;
import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.SourceReportAdapter;
import com.example.olivia.myapplication.model.User;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static com.example.olivia.myapplication.model.RetrieveSourceReportData.reports;

/**
 * This is the page that shows a list of source reports. You
 * Have the option to either create source reports or show
 * details for individual source report.
 */
public class ViewSourceReportActivityRecycler extends AppCompatActivity {

    private User user;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<SourceReport> reportList;

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clickListener;
        private GestureDetector mGestureDetector;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, ClickListener listener) {
            clickListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View child = view.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && mGestureDetector != null) {
                clickListener.onClick(child, view.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_action:
                Intent intent = new Intent(ViewSourceReportActivityRecycler.this, ProfileActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                return true;
            case R.id.logout_action:
                Intent intent1 = new Intent(ViewSourceReportActivityRecycler.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_recycler_layout);
        //Initializes buttons on page

        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
//        Button cancelButton = (Button) findViewById(R.id.cancel_report_source);
//        Button viewMap = (Button) findViewById(R.id.view_source_report_map_button);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addReport);
        FloatingActionButton locationButton = (FloatingActionButton) findViewById(R.id.locationButton);
        //The source reports need to be added to an array to be shown

        //Sets up list of source reports
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reports);
//        final ListView reportList = (ListView) findViewById(R.id.report_list);
//        reportList.setAdapter(adapter);
//        reportList.setOnItemClickListener(
//                new AdapterView.OnItemClickListener(){
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mRecyclerView = (RecyclerView) findViewById(R.id.source_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SourceReportAdapter(this, reports);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
            @Override
            public void onLongClick(View view, int position) {
                SourceReport report = reports.get(position);
                Intent intent = new Intent(ViewSourceReportActivityRecycler.this, ShowSourceReportActivity.class);
                String time = report.getTime();
                int reportNum = report.getReportNumber();
                String creator = report.getCreator();
                String location = report.getLocation();
                String condition = report.getCondition();
                String type = report.getType();

                intent.putExtra("repNo", reportNum);
                intent.putExtra("time", time);
                intent.putExtra("WorkerName", creator);
                intent.putExtra("loc", location);
                intent.putExtra("condition", condition);
                intent.putExtra("type", type);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }));


//        locationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewSourceReportActivity.this, ViewSourceReportsLocationActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//            }
//        });
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewSourceReportActivity.this, CreateSourceReportActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//            }
//        });
//        //Cancel button returns to Main Screen Activity
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewSourceReportActivity.this, MainActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//        //ViewMap button change display to the map page that views all the pin pointed locations
    }

}

