package com.example.q_leito.xyzreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.q_leito.xyzreader.R;
import com.example.q_leito.xyzreader.data.ArticleLoader;
import com.example.q_leito.xyzreader.data.ItemsContract;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Q_Lei on 8/2/2016.
 */
public class ArticleAdapter
        extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{
    private Context mContext;
    private Cursor mCursor;

    public ArticleAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        return mCursor.getLong(ArticleLoader.Query._ID);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_article, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                        ItemsContract.Items.buildItemUri(
                                getItemId(viewHolder.getAdapterPosition()))));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        mCursor.moveToPosition(position);
        viewHolder.mTitleView.setText(mCursor.getString(ArticleLoader.Query.TITLE));
        viewHolder.mSubtitleView.setText(
                String.format("%s by %s", DateUtils.getRelativeTimeSpanString(
                        mCursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                        System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_ALL).toString(), mCursor.getString(ArticleLoader.Query.AUTHOR)));

        Picasso.with(mContext)
                .load(mCursor.getString(ArticleLoader.Query.THUMB_URL))
                .into(viewHolder.mThumbnailView);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        @Bind(R.id.thumbnail)
        ImageView mThumbnailView;

        @Bind(R.id.article_title)
        TextView mTitleView;

        @Bind(R.id.article_subtitle)
        TextView mSubtitleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
