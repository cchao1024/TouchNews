package com.github.cchao.touchnews.presenter;

import com.github.cchao.touchnews.BaseApplication;
import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contract.FragmentContainerContract;
import com.github.cchao.touchnews.ui.fragment.JokeImageListFragments;
import com.github.cchao.touchnews.ui.fragment.JokeTextListFragments;
import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class JokeFragmentsContainerPresenter implements FragmentContainerContract.Presenter {
    FragmentContainerContract.View mView;
    List<BaseFragment> mFragments;
    String[] mTitles;

    public JokeFragmentsContainerPresenter(FragmentContainerContract.View view) {
        mView = view;
        mView.setFragment(getFragments(), getTitles());
    }

    @Override
    public List<BaseFragment> getFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new JokeTextListFragments());
        mFragments.add(new JokeImageListFragments());
        return mFragments;
    }

    @Override
    public String[] getTitles() {
        mTitles = BaseApplication.getContext().getResources().getStringArray(R.array.joke_titles);
        return mTitles;
    }
}
