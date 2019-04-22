package com.github.cchao.touchnews.javaBean.news;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class Contentlist implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private String channelId;

    private String channelName;

    private String desc;

    private List<Imageurls> imageurls;

    private String link;

    private String nid;

    private String pubDate;

    private String source;

    private String title;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setImageurls(List<Imageurls> imageurls) {
        this.imageurls = imageurls;
    }

    public List<Imageurls> getImageurls() {
        return this.imageurls;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNid() {
        return this.nid;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

}
