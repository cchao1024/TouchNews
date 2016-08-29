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

import com.github.cchao.touchnews.javaBean.news.Contentlist;
import com.github.cchao.touchnews.util.Constant;

import java.util.List;

/**
 * 绑定News List数据的 view、presenter
 */
public interface NewListDataContract {

        interface View {
                /**
                 * 获取数据
                 *
                 * @param newsList newsList
                 */
                void onRefreshData ( List< Contentlist > newsList );

                /**
                 * 添加数据
                 *
                 * @param newsList add newsList
                 */
                void onReceiveMoreListData ( List< Contentlist > newsList );

                /**
                 * 显示信息  e.g. 没有网络、正在加载、异常
                 *
                 * @param INFOType 枚举值
                 * @param infoText 提示的文本内容
                 * @see Constant.INFO_TYPE
                 */
                void showInfo ( Constant.INFO_TYPE INFOType, String infoText );

                void hideInfo ( );

        }

        interface Presenter {
                void getRefreshData ( );

                void getMoreData ( );
        }
}
