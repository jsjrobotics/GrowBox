package com.jsjrobotics.growbox.display;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jsjrobotics.growbox.display.detail.DetailController;
import com.jsjrobotics.growbox.display.detail.DetailDisplay;
import com.jsjrobotics.growbox.display.graph.GraphController;
import com.jsjrobotics.growbox.display.graph.GraphDisplay;

import java.util.Arrays;
import java.util.List;

public class GrowboxFragmentAdapter extends BaseAdapter {
    private final List<AndroidThingsDisplay> mData;
    private final Activity mActivity;

    public GrowboxFragmentAdapter(Activity activity){
        mActivity = activity;
        mData = buildDisplay();
    }

    private List<AndroidThingsDisplay> buildDisplay() {
        GraphDisplay graphDisplay = new GraphDisplay();
        GraphController graphController = new GraphController(graphDisplay);
        DetailDisplay detailDisplay = new DetailDisplay();
        DetailController detailController = new DetailController(detailDisplay);
        return Arrays.asList(
                graphDisplay,
                graphController,
                detailDisplay,
                detailController
        );
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mData.get(position).getView(mActivity, convertView, parent);
    }
}
