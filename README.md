#指尖资讯

> 基于MVP架构、遵循Material Design的Android应用。包括新闻，图片，笑话，天气，聊天机器人等等。欢迎吐槽，欢迎点赞. 

#这是效果图


![Splash](http://img.blog.csdn.net/20160517153223672) ![navigateView](http://img.blog.csdn.net/20160517152740944) ![newsFragments](http://img.blog.csdn.net/20160517154123285) ![ImageShowAty](http://img.blog.csdn.net/20160517153649284) ![musicFragment](http://img.blog.csdn.net/20160517153327998) ![musicSearch](http://img.blog.csdn.net/20160517153345657) ![chatFragment](http://img.blog.csdn.net/20160517153359452) ![项目结构](http://img.blog.csdn.net/20160517120012181)

##Gif

#说明

> **项目基于MVP架构、通篇使用免费Api > [百度Api](http://apistore.baidu.com/)。以下是各个模块介绍：**

###新闻资讯

- 数据源来至[易源新闻api](http://apistore.baidu.com/apiworks/servicedetail/688.html)。通过设置不同的频道号获取新闻，新闻均来之网络，已在新闻详情页标题说明出处。

###开心一下

- 只有文本和图片两类，图片双击放大及移动使用开源框架PhotoView

###音乐鉴赏

- 使用MediaPlay播放音乐，通过Presenter控制音乐的业务，Fragment负责各个按钮，album的更新，model去获取播放地址、album地址、播放列表。支持搜索框实时搜索歌曲。

###聊天机器人

- 使用[图灵机器人api](http://www.tuling123.com)、原api提供许多接口，鄙人只取一瓢文本对话。注释里把对话过程写成请求与响应。

###天气页

- 天气页从[和风天气](http://www.heweather.com/)获取的到json数据，有两个data 键，用了个包才解析成功，图标默认蓝色背景，也就是navigation图片上不协调的，p图什么的最烦了。

#开源框架引用


- **ButterKnife**
Link: https://github.com/JakeWharton/butterknife

- **glide**
Link: https://github.com/bumptech/glide

- **PhotoView**
Link: https://github.com/chrisbanes/PhotoView

- **EventBus**
Link: https://github.com/greenrobot/EventBus

- **Gson**
Link: https://github.com/google/gson

- **logutils**
Link: https://github.com/pengwei1024/LogUtils

- **Volley**
Link: https://android.googlesource.com/platform/frameworks/volley

- **Umeng**
Link: http://www.umeng.com/

- **waitingdots**
Link: https://github.com/tajchert/WaitingDots

#Download

- **GitHub** :  https://github.com/cchao1024/TouchNews
- **Git@ OSC** :  https://git.oschina.net/cchao1024_120/TouchNews

- **[app下载链接、点我下载](https://www.pgyer.com/touchnews)**

- **二维码扫一扫**：![二维码](http://img.blog.csdn.net/20160517165411722)

#About Me

> - E-mail: **cchao1024@163.com**
> - QQ : **1037322351**
> - 打赏我：
![这里写图片描述](http://img.blog.csdn.net/20160517173055763)


#License

Copyright 2016 cchao1024@163.com

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.



