package com.github.cchao.touchnews.javaBean.news;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class Showapi_res_body {
    private Pagebean pagebean;

    private int ret_code;

    public void setPagebean(Pagebean pagebean) {
        this.pagebean = pagebean;
    }

    public Pagebean getPagebean() {
        return this.pagebean;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public int getRet_code() {
        return this.ret_code;
    }

}
