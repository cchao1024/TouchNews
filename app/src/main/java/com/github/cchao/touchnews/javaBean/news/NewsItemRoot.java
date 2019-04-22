package com.github.cchao.touchnews.javaBean.news;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description: 新闻Item实体
 */

public class NewsItemRoot {
    private int showapi_res_code;

    private String showapi_res_error;

    private Showapi_res_body showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public int getShowapi_res_code() {
        return this.showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_error() {
        return this.showapi_res_error;
    }

    public void setShowapi_res_body(Showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public Showapi_res_body getShowapi_res_body() {
        return this.showapi_res_body;
    }

}

/**
 * 样例
 */
/*
{
	"showapi_res_code": 0,
	"showapi_res_error": "",
	"showapi_res_body": {
		"pagebean": {
			"allNum": 7995,
			"allPages": 400,
			"contentlist": [
				{
					"channelId": "5572a109b3cdc86cf39001db",
					"channelName": "国内最新",
					"desc": "引起社会的广泛关注和热烈讨论。",
					"imageurls": [],
					"link": "http://news.xinhuanet.com/politics/2015-07/05/c_1115820327.htm",
					"nid": "2678177830618688823",
					"pubDate": "2015-07-06 16:30:00",
					"source": "新华网",
					"title": "新华视点:深改小组四份文件聚焦生态建设透露哪些新信号? "
				},
				{
					"channelId": "5572a109b3cdc86cf39001db",
					"channelName": "国内最新",
					"desc": "在众多已上市或者准备上市的团队中，马云做为幕后推手，低调的控制着这些团队的小半个命脉。",
					"imageurls": [
						{
							"height": 119,
							"url": "http://news.huaxi100.com/uploadfile/2015/0706/20150706041013849.png",
							"width": 169
						},
						{
							"height": 119,
							"url": "http://news.huaxi100.com/uploadfile/2015/0706/20150706041024411.png",
							"width": 169
						},
						{
							"height": 119,
							"url": "http://news.huaxi100.com/uploadfile/2015/0706/20150706041023428.png",
							"width": 169
						}
					],
					"link": "http://news.huaxi100.com/show-228-650467-1.html&mobile=yes",
					"nid": "16717980784340357593",
					"pubDate": "2015-07-06 16:27:30",
					"source": "华西都市报",
					"title": "蚂蚁金服、恒大淘宝足球等集资上市 马云做幕后推手"
				},
				{
					"channelId": "5572a109b3cdc86cf39001db",
					"channelName": "国内最新",
					"desc": "即使以上四个假设都实现，在这家媒体看来，C罗获得今年金球奖荣誉的可能性也只是0%。",
					"imageurls": [
						{
							"height": 119,
							"url": "http://img2.cache.netease.com/sports/2015/7/6/20150706111324ca9ec_550.jpg",
							"width": 169
						}
					],
					"link": "http://sports.163.com/15/0706/11/ATRA6QN500051C8V.html",
					"nid": "9207527965600985083",
					"pubDate": "2015-07-06 16:25:00",
					"source": "网易体育",
					"title": "C罗逆袭金球?西媒调侃:机会0%"
				}
			],
			"currentPage": 1,
			"maxResult": 20
		},
		"ret_code": 0
	}
}
 */