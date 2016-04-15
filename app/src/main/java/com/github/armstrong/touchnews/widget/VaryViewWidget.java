package com.github.armstrong.touchnews.widget;

import android.view.View;
import android.view.ViewGroup;

import com.github.armstrong.touchnews.util.Constant;

public class VaryViewWidget {

        /**
         * 原来的View
         */
        public View mOriginView;
        /**
         * 提示View
         */
        public View mAlertView;
        /**
         * 没网View
         */
        public View mNoNetView;

        /**
         * 正在加载View
         */

        public View mLoadingView;
        /**
         * 数据为空的View
         */
        public View mEmptyView;

        /**
         * 正在加载View
         */
//        public ProgressWheel mLoadingProgress;
        ViewGroup mParentViewGroup;
        int mOriginViewIndex;
        ViewGroup.LayoutParams layoutParams;

        public VaryViewWidget ( View originView ) {
                setOriginView ( originView );

                layoutParams = mOriginView.getLayoutParams ( );
                mParentViewGroup = ( ViewGroup ) mOriginView.getParent ( );
                if ( mParentViewGroup == null ) {
                        throw new IllegalArgumentException (originView.getClass ().getSimpleName ()+"目标View 需要有ViewGroup父布局");
                }
                if ( mOriginViewIndex == - 1 ) {
                        for ( int index = 0 ; index < mParentViewGroup.getChildCount ( ) ; index++ ) {
                                if ( mOriginView == mParentViewGroup.getChildAt ( index ) ) {
                                        mOriginViewIndex = index;
                                        break;
                                }
                        }
                }
        }

        public View getAlertView ( ) {
                return mAlertView;
        }

        public void setAlertView ( View alertView, View.OnClickListener listener ) {
                mAlertView = alertView;
                if ( listener != null ) {
                        mAlertView.setOnClickListener ( listener );
                }
        }

        public View getOriginView ( ) {
                return mOriginView;
        }

        public void setOriginView ( View originView ) {
                mOriginView = originView;
        }

        public View getLoadingView ( ) {
                return mLoadingView;
        }

        public void setLoadingView ( View loadingView ) {
                mLoadingView = loadingView;
        }

        public View getNoNetView ( ) {
                return mNoNetView;
        }

        public void setNoNetView ( View noNetView ) {
                mNoNetView = noNetView;
        }

        public View getEmptyView ( ) {
                return mEmptyView;
        }

        public void setEmptyView ( View emptyView ) {
                mEmptyView = emptyView;
        }

        public void showView ( Constant.TYPE type ) {
                View infoView = null;
                switch ( type ) {
                        case LOADING:
                                infoView = mLoadingView;
                                break;
                        case NO_NET:
                                infoView = mAlertView;
                                break;
                        case ALERT:
                                infoView = mAlertView;
                                break;
                        case EMPTY:
                                infoView = mEmptyView;
                                break;
                        case ORIGIN:
                                infoView = mOriginView;
                                break;
                }
             if( mParentViewGroup.getChildAt ( mOriginViewIndex ) != infoView ) {
                if ( infoView != null ) {
                        ViewGroup parent = ( ViewGroup ) infoView.getParent ( );
                        if ( parent != null ) {
                                parent.removeView ( infoView );
                        }
                        mParentViewGroup.removeViewAt ( mOriginViewIndex );
                        mParentViewGroup.addView ( infoView, mOriginViewIndex, layoutParams );
                }
                }
        }
        public void hideInfo ( ) {
                showView ( Constant.TYPE.ORIGIN );
        }

        public void reinitializeVaryView ( ) {
                mAlertView = null;
                mLoadingView = null;
                mEmptyView = null;
        }

        public static class Builder {
                private View mAlertView;
                public View mNoNetView;
                private View mLoadingView;
                private View mEmptyView;
                private View mOriginView;
                private View.OnClickListener mListener;

                public Builder ( View originView ) {
                        mOriginView = originView;
                }

                public Builder setAlertView ( View alertView, View.OnClickListener listener ) {
                        mAlertView = alertView;
                        mAlertView.setOnClickListener ( listener );
                        return this;
                }

                public Builder setLoadingView ( View loadingView ) {
                        mLoadingView = loadingView;
                        return this;
                }

                public View getNoNetView ( ) {
                        return mNoNetView;
                }

                public void setNoNetView ( View noNetView ) {
                        mNoNetView = noNetView;
                }

                public Builder setEmptyView ( View emptyView ) {
                        mEmptyView = emptyView;
                        return this;
                }

                public Builder setOriginView ( View originView ) {
                        mOriginView = originView;
                        return this;
                }

                public VaryViewWidget build ( ) {
                        VaryViewWidget widget = new VaryViewWidget ( mOriginView );
                        if ( mOriginView != null ) {
                                widget.setEmptyView ( mEmptyView );
                        }
                        if ( mNoNetView != null ) {
                                widget.setEmptyView ( mEmptyView );
                        }
                        if ( mEmptyView != null ) {
                                widget.setEmptyView ( mEmptyView );
                        }
                        if ( mAlertView != null ) {
                                widget.setAlertView ( mAlertView, mListener );
                        }
                        if ( mLoadingView != null ) {
                                widget.setLoadingView ( mLoadingView );
                        }
                        return widget;
                }


        }

}