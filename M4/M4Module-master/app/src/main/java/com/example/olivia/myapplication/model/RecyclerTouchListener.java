package com.example.olivia.myapplication.model;

import android.content.Context;
import android.media.midi.MidiOutputPort;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Olivia on 4/24/2017.
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private GestureDetector mGestureDetector;

    public RecyclerTouchListener(Context context, OnItemClickListener listener) {
        clickListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View child = view.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && mGestureDetector != null) {
            clickListener.onItemClick(child, view.getChildAdapterPosition(child));
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
