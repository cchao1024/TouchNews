package com.github.armstrong.touchnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.joke.JokeTextRoot;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeTextListRecyclerAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder > {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;
        public Context mContext;
        public List< JokeTextRoot.Contentlist > mData;
        public LayoutInflater mLayoutInflater;

//        private View.OnClickListener mOnClickListener;

        public JokeTextListRecyclerAdapter ( Context context, List< JokeTextRoot.Contentlist > data ) {
                mContext = context;
                mData = data;
                mLayoutInflater = LayoutInflater.from ( context );
        }

        @Override
        public int getItemViewType ( int position ) {
                if ( position + 1 == getItemCount ( ) ) {
                        return TYPE_FOOTER;
                } else {
                        return TYPE_ITEM;
                }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
                if ( viewType == TYPE_ITEM ) {
                        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.item_joke_text, parent, false );
                        return new MViewHolder ( view );
                } else {
                        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.list_view_footer, parent, false );
                        return new RecyclerView.ViewHolder ( view){
                                @Override
                                public String toString ( ) {
                                        return super.toString ( );
                                }
                        };
                }
        }

        @Override
        public void onBindViewHolder ( RecyclerView.ViewHolder holder, int position ) {
                if(holder instanceof MViewHolder) {
                        JokeTextRoot.Contentlist contentEntity = mData.get ( position );
                        ((MViewHolder)holder).mTitle.setText ( contentEntity.getTitle ( ) );
                        ((MViewHolder)holder).mContent.setText ( contentEntity.getText ( ) );
                }
        }

        @Override
        public int getItemCount ( ) {
                return mData.size ( )+1;
        }

        public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                public TextView mTitle;
                public TextView mContent;

                public MViewHolder ( View view ) {
                        super ( view );
                        mTitle = ( TextView ) view.findViewById ( R.id.tv_title );
                        mContent = ( TextView ) view.findViewById ( R.id.tv_content );
                        view.setOnClickListener ( this );
                }

                @Override
                public void onClick ( View v ) {
                       /* Intent intent = new Intent ( mContext, NewsDetailActivity.class );
                        intent.putExtra ( "contentList", mData.get ( this.getLayoutPosition ( ) ) );
//                     View transitionView = view.findViewById(R.id.ivNews);
                        ActivityOptionsCompat options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation ( ( HomeActivity ) mContext,
                                        mImage, mContext.getString ( R.string.transition__img ) );

                        ActivityCompat.startActivity ( ( HomeActivity ) mContext, intent, options.toBundle ( ) );*/

                }
        }
}
