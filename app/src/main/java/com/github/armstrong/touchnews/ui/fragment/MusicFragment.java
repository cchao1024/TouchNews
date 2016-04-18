package com.github.armstrong.touchnews.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.presenter.MusicPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */

public class MusicFragment extends BaseLazyFragment {
        View mView;
        private MusicPresenter mMusicsPresenter = null;
        public void onFirstUserVisible ( ) {

        }

        @Nullable
        @Override
        public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
                mView=inflater.inflate ( R.layout.fragment_music,container );
                return mView;
        }
        @Override
        public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );

        }

/*@InjectView(R.id.musics_player_background)
        ImageView mBackgroundImage;

        @InjectView(R.id.musics_player_disc_view)
        PlayerDiscView mPlayerDiscView;

        @InjectView(R.id.musics_player_play_ctrl_btn)
        ImageButton mPlayerCtrlBtn;

        @InjectView(R.id.musics_player_play_next_btn)
        ImageButton mPlayerNextBtn;

        @InjectView(R.id.musics_player_play_prev_btn)
        ImageButton mPlayerPrevBtn;

        @InjectView(R.id.musics_player_seekbar)
        SeekBar mPlayerSeekBar;

        @InjectView(R.id.musics_player_name)
        TextView mTitle;

        @InjectView(R.id.musics_player_songer_name)
        TextView mSonger;

        @InjectView(R.id.musics_player_current_time)
        TextView mCurrentTime;

        @InjectView(R.id.musics_player_total_time)
        TextView mTotalTime;

        @InjectView(R.id.musics_player_loading_view)
        View mLoadingTargetView;


        private String mMusicsCollectId = UriHelper.URL_MUSICS_LIST_CHANNEL_ID;

        private List<MusicsListEntity> mPlayListData;

        private boolean isPlaying = true;
        private static final int BLUR_RADIUS = 100;

        private PlayBundleBroadCast mBundleBroadCast;
        private PlayPositionBroadCast mPositionBroadCast;
        private PlaySecondProgressBroadCast mSecondProgressBroadCast;

        @Override
        protected void onFirstUserVisible() {
                toggleShowLoading(true, null);
                mMusicsPresenter = new MusicsPresenterImpl(mContext, this);
                if (NetUtils.isNetworkConnected(mContext)) {
                        mMusicsPresenter.loadListData(TAG_LOG, mMusicsCollectId, Constants.EVENT_REFRESH_DATA);
                } else {
                        toggleNetworkError(true, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        toggleShowLoading(true, null);
                                        mMusicsPresenter.loadListData(TAG_LOG, mMusicsCollectId, Constants.EVENT_REFRESH_DATA);
                                }
                        });
                }
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                mBundleBroadCast = new PlayBundleBroadCast();
                IntentFilter bundleFilter = new IntentFilter();
                bundleFilter.addAction(Constants.ACTION_MUSIC_BUNDLE_BROADCAST);

                mContext.registerReceiver(mBundleBroadCast, bundleFilter);

                mPositionBroadCast = new PlayPositionBroadCast();
                IntentFilter posFilter = new IntentFilter();
                posFilter.addAction(Constants.ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST);

                mContext.registerReceiver(mPositionBroadCast, posFilter);

                mSecondProgressBroadCast = new PlaySecondProgressBroadCast();
                IntentFilter secondProgressFilter = new IntentFilter();
                secondProgressFilter.addAction(Constants.ACTION_MUSIC_SECOND_PROGRESS_BROADCAST);

                mContext.registerReceiver(mSecondProgressBroadCast, secondProgressFilter);

                mContext.startService(new Intent (mContext, MusicPlayService.class));
        }

        @Override
        public void onDetach() {
                super.onDetach();
                if(mMusicsPresenter != null) {
                        mMusicsPresenter.onStopPlay();
                }
                mContext.unregisterReceiver(mBundleBroadCast);
                mContext.unregisterReceiver(mPositionBroadCast);
                mContext.unregisterReceiver(mSecondProgressBroadCast);
        }

        @Override
        protected void onUserVisible() {
        }

        @Override
        protected void onUserInvisible() {
        }

        @Override
        protected View getLoadingTargetView() {
                return mLoadingTargetView;
        }

        @Override
        protected void initViewsAndEvents() {
                Bitmap bitmap = ImageBlurManager.doBlurJniArray( BitmapFactory.decodeResource(getResources(),
                        R.drawable.player_bg),
                        BLUR_RADIUS,
                        false);
                mBackgroundImage.setImageBitmap(bitmap);

                mPlayerCtrlBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if (isPlaying) {
                                        mMusicsPresenter.onPausePlay();
                                } else {
                                        mMusicsPresenter.onRePlay();
                                }
                        }
                });

                mPlayerNextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mMusicsPresenter.onNextClick();
                        }
                });

                mPlayerPrevBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mMusicsPresenter.onPrevClick();
                        }
                });
        }

        @Override
        protected int getContentViewLayoutID() {
                return R.layout.fragment_musics;
        }

        @Override
        protected void onEventComming(EventCenter eventCenter) {
                if (null == mMusicsPresenter) {
                        return;
                }
                int eventCode = eventCenter.getEventCode();
                switch (eventCode) {
                        case Constants.EVENT_START_PLAY_MUSIC:
                                mMusicsPresenter.onRePlay();
                                break;
                        case Constants.EVENT_STOP_PLAY_MUSIC:
                                mMusicsPresenter.onPausePlay();
                                break;
                }
        }

        @Override
        protected boolean isBindEventBusHere() {
                return true;
        }

        @Override
        public void refreshMusicsList(ResponseMusicsListentity data) {
                if (null != data) {
                        mPlayListData = data.getSong();
                        if (null != mPlayListData && !mPlayListData.isEmpty()) {
                                MusicPlayService.refreshMusicList(mPlayListData);
                                mMusicsPresenter.onStartPlay();
                        }
                }
        }

        @Override
        public void addMoreMusicsList(ResponseMusicsListentity data) {
                if (null != data) {
                        mPlayListData = data.getSong();
                        if (null != mPlayListData && !mPlayListData.isEmpty()) {
                                MusicPlayService.refreshMusicList(mPlayListData);
                                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_NEXT));
                        }
                }
        }

        @Override
        public void rePlayMusic() {
                isPlaying = true;
                mPlayerDiscView.rePlay();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_pause_selector);
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_REPLAY));
        }

        @Override
        public void startPlayMusic() {
                isPlaying = true;
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_pause_selector);
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PLAY));
        }

        @Override
        public void stopPlayMusic() {
                isPlaying = false;
                mPlayerDiscView.pause();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_play_selector);
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_STOP));
        }

        @Override
        public void pausePlayMusic() {
                isPlaying = false;
                mPlayerDiscView.pause();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_play_selector);
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PAUSE));
        }

        @Override
        public void playNextMusic() {
                isPlaying = true;
                mPlayerDiscView.next();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_play_selector);
                mMusicsPresenter.loadListData(TAG_LOG, mMusicsCollectId, Constants.EVENT_LOAD_MORE_DATA);
        }

        @Override
        public void playPrevMusic() {
                isPlaying = true;
                mPlayerDiscView.next();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_play_selector);
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_MUSIC_PREV));
        }

        @Override
        public void seekToPosition(int position) {
                mContext.sendBroadcast(new Intent(MusicPlayState.ACTION_SEEK_TO).putExtra(Constants.KEY_PLAYER_SEEK_TO_PROGRESS, position));
        }

        @Override
        public void refreshPageInfo(MusicsListEntity entity, int totalDuration) {
                toggleShowLoading(false, null);
                mPlayerDiscView.startPlay();
                mPlayerCtrlBtn.setImageResource(R.drawable.btn_pause_selector);

                if (null != entity) {
                        mTitle.setText(entity.getTitle());
                        StringBuilder sb = new StringBuilder();
                        sb.append("--\t");
                        sb.append(entity.getArtist());
                        sb.append("\t--");
                        mSonger.setText(sb.toString().trim());
                }

                if (totalDuration > 0) {
                        mPlayerSeekBar.setMax(totalDuration);
                }

                String imageUrl = entity.getPicture();
                if (!CommonUtils.isEmpty(imageUrl)) {
                        mPlayerDiscView.loadAlbumCover(imageUrl);
                        ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener() {
                                @Override
                                public void onLoadingStarted(String imageUri, View view) {

                                }

                                @Override
                                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                }

                                @Override
                                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                        Bitmap bitmap = ImageBlurManager.doBlurJniArray(loadedImage, BLUR_RADIUS, false);
                                        mBackgroundImage.setImageBitmap(bitmap);
                                }

                                @Override
                                public void onLoadingCancelled(String imageUri, View view) {

                                }
                        });
                } else {
                        Bitmap bitmap = ImageBlurManager.doBlurJniArray(BitmapFactory.decodeResource(getResources(),
                                R.drawable.player_bg),
                                BLUR_RADIUS,
                                false);
                        mBackgroundImage.setImageBitmap(bitmap);
                }

                String totalTime = CommonUtils.convertTime(totalDuration);
                if (null != totalTime && ! TextUtils.isEmpty(totalTime)) {
                        mTotalTime.setText(totalTime);
                }
        }

        @Override
        public void refreshPlayProgress(int progress) {
                mPlayerSeekBar.setProgress(progress);
                String currentTime = CommonUtils.convertTime(progress);
                if (null != currentTime && !TextUtils.isEmpty(currentTime)) {
                        mCurrentTime.setText(currentTime);
                }
        }

        @Override
        public void refreshPlaySecondProgress(int progress) {
                mPlayerSeekBar.setSecondaryProgress(progress);
        }

        private class PlayBundleBroadCast extends BroadcastReceiver {

                @Override
                public void onReceive( Context context, Intent intent) {
                        String action = intent.getAction();
                        if (null != action && !TextUtils.isEmpty(action)) {
                                if (action.equals(Constants.ACTION_MUSIC_BUNDLE_BROADCAST)) {
                                        Bundle extras = intent.getExtras();
                                        if (null != extras) {
                                                MusicsListEntity entity = extras.getParcelable(Constants.KEY_MUSIC_PARCELABLE_DATA);
                                                int totalDuration = extras.getInt(Constants.KEY_MUSIC_TOTAL_DURATION);

                                                mMusicsPresenter.refreshPageInfo(entity, totalDuration);
                                        }
                                }
                        }
                }

        }

        private class PlayPositionBroadCast extends BroadcastReceiver {

                @Override
                public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (null != action && !TextUtils.isEmpty(action)) {
                                if (action.equals(Constants.ACTION_MUSIC_CURRENT_PROGRESS_BROADCAST)) {
                                        Bundle extras = intent.getExtras();
                                        if (null != extras) {
                                                int progress = extras.getInt(Constants.KEY_MUSIC_CURRENT_DUTATION);

                                                mMusicsPresenter.refreshProgress(progress);
                                        }
                                }
                        }

                }
        }

        private class PlaySecondProgressBroadCast extends BroadcastReceiver {

                @Override
                public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (null != action && !TextUtils.isEmpty(action)) {
                                if (action.equals(Constants.ACTION_MUSIC_SECOND_PROGRESS_BROADCAST)) {
                                        Bundle extras = intent.getExtras();
                                        if (null != extras) {
                                                int progress = extras.getInt(Constants.KEY_MUSIC_SECOND_PROGRESS);

                                                mMusicsPresenter.refreshSecondProgress(progress);
                                        }
                                }
                        }

                }
        }

        @Override
        public void showError(String msg) {
                toggleShowError(true, msg, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mMusicsPresenter.loadListData(TAG_LOG, mMusicsCollectId, Constants.EVENT_REFRESH_DATA);
                        }
                });*/

}

