package com.example.impressiontutorialstepthrough;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by User on 1/18/16.
 */
public class ImpressionAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<String> mDataSet;
    private final VisibilityTracker mVisibilityTracker;
    private final WeakHashMap<View, Integer> mViewPositionMap = new WeakHashMap<>();

    public ImpressionAdapter(Activity activity, List<String> dataSet) {
        mDataSet = dataSet;
        mVisibilityTracker = new VisibilityTracker(activity);

        mVisibilityTracker.setVisibilityTrackerListener(new VisibilityTracker.VisibilityTrackerListener() {
            @Override
            public void onVisibilityChanged(List<View> visibleViews, List<View> invisibleViews) {
                handleVisibleViews(visibleViews);
            }
        });
    }

    private void handleVisibleViews(List<View> visibleViews) {
        Log.d(ImpressionAdapter.class.getSimpleName(), "Currently visible views \n");
        for (View v : visibleViews) {
            Integer viewPosition = mViewPositionMap.get(v);
            String viewTitle = mDataSet.get(viewPosition);
            Log.d(ImpressionAdapter.class.getSimpleName(), viewTitle);
        }

        Log.d(ImpressionAdapter.class.getSimpleName(), "------------------------------");

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_layout, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int position) {
        String title = mDataSet.get(position);

        //alternate view background color
        productViewHolder.itemView.setBackgroundResource(position % 2 == 0 ? android.R.color.white : android.R.color.darker_gray);
        productViewHolder.mTitleTextView.setText(title);

        mViewPositionMap.put(productViewHolder.itemView, position);
        mVisibilityTracker.addView(productViewHolder.itemView, 0);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}