package com.example.q_leito.xyzreader.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mItemOffset;

    public DividerItemDecoration(int itemOffSet) {
        mItemOffset = itemOffSet;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.set(0, mItemOffset, 0, mItemOffset);
    }
}

