/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.cchao.touchnews.contract;

import com.github.cchao.touchnews.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 绑定fragment块容器 view、presenter
 */
public interface FragmentContainerContract {

    interface View {
        void setFragment(List<BaseFragment> fragments, String[] titles);
    }

    interface Presenter {
        List<BaseFragment> getFragments();

        String[] getTitles();
    }
}
