package com.github.armstrong.touchnews.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.MusicEntity;
import com.github.armstrong.touchnews.presenter.MusicPresenter;
import com.github.armstrong.touchnews.presenter.i.IMusicPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseFragment;
import com.github.armstrong.touchnews.util.ImageUtil;
import com.github.armstrong.touchnews.util.ToastUtil;
import com.github.armstrong.touchnews.view.IMusicView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description: 音乐播放界面
 */

public class MusicFragment extends BaseFragment implements IMusicView {
        @Bind ( R.id.root_music_fragment )
        RelativeLayout mRootLayout;
        @Bind ( R.id.btn_music_play )
        ImageButton mPlayBtn;
        @Bind ( R.id.list_view_music_search )
        ListView mListView;
        @Bind ( R.id.btn_music_next )
        ImageButton mNextBtn;
        @Bind ( R.id.tv_music__name )
        TextView mMusicName;
        @Bind ( R.id.tv_music_singer )
        TextView mMusicSinger;
        @Bind ( R.id.iv_music_album )
        ImageView mAlbum;
        View mViewRoot;
        SearchView mMenuItemSearch;
        private IMusicPresenter mMusicsPresenter = null;
        MusicEntity mCurMusic;
        ArrayAdapter< String > mStringArrayAdapter;

        @Override
        public void onCreate ( Bundle savedInstanceState ) {
                super.onCreate ( savedInstanceState );
                setHasOptionsMenu ( true );
        }

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                mViewRoot = inflater.inflate ( R.layout.fragment_music, null );
                return mViewRoot;
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
                mToolbar.setTitle ( R.string.music );
        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mMusicsPresenter = new MusicPresenter ( mContext, this );
                mStringArrayAdapter = new ArrayAdapter< String > ( mContext, android.R.layout.simple_expandable_list_item_1, getResources ( )
                        .getStringArray ( R.array.news_titles ) );

                mListView.setAdapter ( mStringArrayAdapter );
                mListView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
                        @Override
                        public void onItemClick ( AdapterView< ? > parent, View view, int position, long id ) {
                                ToastUtil.showShortToast ( mContext, position + "" );
                                mMusicsPresenter.getMusic ( ( ( TextView ) view ).getText ( ).toString ( ) );
                        }
                } );
        }

        @OnClick ( { R.id.btn_music_next , R.id.btn_music_play } )
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
                        case R.id.btn_music_play:
                                mMusicsPresenter.onPlay ( );
                                break;
                        case R.id.btn_music_next:
                                mMusicsPresenter.onNext ( );
                                break;
                }
        }

        @Override
        public void onCreateOptionsMenu ( Menu menu, MenuInflater inflater ) {
                inflater.inflate ( R.menu.menu_music, menu );
                super.onCreateOptionsMenu ( menu, inflater );
                mMenuItemSearch = ( SearchView ) MenuItemCompat.getActionView ( menu.findItem ( R.id.action_search ) );
                mMenuItemSearch.setOnCloseListener ( new SearchView.OnCloseListener ( ) {
                        @Override
                        public boolean onClose ( ) {
                                mListView.setVisibility ( View.GONE );
                                return false;
                        }
                } );
                //文本输入框可见、显示ListView
                mMenuItemSearch.setOnQueryTextFocusChangeListener ( new View.OnFocusChangeListener ( ) {
                        @Override
                        public void onFocusChange ( View v, boolean hasFocus ) {
                                if ( hasFocus ) {
                                        mListView.setVisibility ( View.VISIBLE );
                                }
                        }
                } );
                mMenuItemSearch.setOnQueryTextListener ( new SearchView.OnQueryTextListener ( ) {
                        @Override
                        public boolean onQueryTextSubmit ( String query ) {
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange ( String newText ) {
                                ToastUtil.showShortToast ( mContext, newText );
                                return true;
                        }
                } );

        }

        @Override
        public boolean onOptionsItemSelected ( MenuItem item ) {
                switch ( item.getItemId ( ) ) {
                        case R.id.action_search:
                                Snackbar.make ( mViewRoot, "action_search", Snackbar.LENGTH_SHORT ).show ( );
                                return true;
                }
                return false;
        }

        @Override
        public void onDestroy ( ) {
                super.onDestroy ( );
        }

        @Override
        public void onMusicPlay ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_pause );
                Snackbar.make ( mViewRoot, "onMusicPlay", Snackbar.LENGTH_SHORT ).show ( );
        }

        @Override
        public void onMusicPause ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_play );
                Snackbar.make ( mViewRoot, "onMusicPause", Snackbar.LENGTH_SHORT ).show ( );
        }

        @Override
        public void onResumePlay ( ) {
                mPlayBtn.setImageResource ( R.drawable.btn_music_pause );
        }

        @Override
        public void onMusicPrepare ( MusicEntity curMusic ) {
                if ( curMusic != null ) {
                        mCurMusic = curMusic;
                        mMusicName.setText ( mCurMusic.getMusicInfo ( ).getSongname ( ) );
                        mMusicSinger.setText ( mCurMusic.getMusicInfo ( ).getSingername ( ) );
                        mPlayBtn.setImageResource ( R.drawable.btn_music_play );
                        Snackbar.make ( mViewRoot, "PREPARE", Snackbar.LENGTH_SHORT ).show ( );
                }
        }

        @Override
        public void setAlbum ( ) {
                if ( mCurMusic != null && mCurMusic.getMusicSinger ( ) != null ) {
                        String url = mCurMusic.getMusicSinger ( ).getImage ( );
                        ImageUtil.displayCircularImage ( mContext, url, mAlbum );
//                        ImageUtil.displayBlurImage ( mContext,url,mRootLayout );
                }
        }

}

