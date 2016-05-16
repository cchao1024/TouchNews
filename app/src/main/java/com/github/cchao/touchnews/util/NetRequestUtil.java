package com.github.cchao.touchnews.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.cchao.touchnews.BaseApplication;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description: Volley 网络请求util
 */
public class NetRequestUtil {
        public final String TAG = this.getClass ( ).getSimpleName ( );
        Context mContext = BaseApplication.getContext ( );
        public static RequestQueue mRequestQueue = Volley.newRequestQueue ( BaseApplication.getContext ( ) );
        private static NetRequestUtil netRequestUtil;

        private NetRequestUtil ( ) {}

        public static NetRequestUtil getInstance ( ) {
                if ( netRequestUtil == null ) {
                        synchronized ( NetRequestUtil.class ) {
                                if ( netRequestUtil == null )
                                        netRequestUtil = new NetRequestUtil ( );
                        }
                }
                return netRequestUtil;
        }

        /**
         * get请求获取JsonObject
         *
         * @param url      url
         * @param param    param
         * @param listener callback
         */
        public void getJson ( String url, Map< String, String > param, final RequestListener listener ) {
                url += prepareParam ( param );
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest ( Request.Method.GET, url, new Response.Listener< JSONObject > ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                listener.onResponse ( response );
                                Log.i ( TAG, response.toString ( ) );
                        }
                }, new Response.ErrorListener ( ) {
                        @Override
                        public void onErrorResponse ( VolleyError error ) {
                                listener.onError ( error );
                                Log.i ( TAG, error.getMessage ( ), error );
                        }
                } );
                mRequestQueue.add ( jsonObjectRequest );
        }

        /**
         * get请求获取JsonObject 带Headers参数
         *
         * @param url      url
         * @param param    param
         * @param listener callback
         */
        public void getJsonWithHeaders ( String url, final Map< String, String > param, final Map< String, String > headers, final RequestListener listener ) {
                url += prepareParam ( param );
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest ( Request.Method.GET, url, new Response.Listener< JSONObject > ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                listener.onResponse ( response );
//                                Log.i ( TAG, response.toString ( ) );
                        }
                }, new Response.ErrorListener ( ) {
                        @Override
                        public void onErrorResponse ( VolleyError error ) {
                                listener.onError ( error );
//                                Log.i ( TAG, error.getMessage ( ), error );
                        }
                } ) {
                        @Override
                        public Map< String, String > getHeaders ( ) throws AuthFailureError {
                                if ( headers == null ) {
                                        return super.getHeaders ();
                                } else {
                                        return headers;
                                }

                        }

                };
                mRequestQueue.add ( jsonObjectRequest );

        }

        public void post ( String url, final Map< String, String > param, final NetRequestUtil.RequestListener listener ) {
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest ( Request.Method.POST, url, new Response.Listener< JSONObject > ( ) {
                        @Override
                        public void onResponse ( JSONObject response ) {
                                listener.onResponse ( response );
                        }
                }, new Response.ErrorListener ( ) {
                        @Override
                        public void onErrorResponse ( VolleyError error ) {
                                listener.onError ( error );
                        }
                } ){
                        @Override
                        protected Map< String, String > getParams ( ) throws AuthFailureError {
                                return param;
                        }
                };
                mRequestQueue.add ( jsonObjectRequest );
        }

        private String prepareParam ( Map< String, String > paramMap ) {

                if ( paramMap == null || paramMap.isEmpty ( ) ) {
                        return "";
                } else {
                        StringBuffer sb = new StringBuffer ( );
                        sb.append ( "?" );
                        for ( String key : paramMap.keySet ( ) ) {
                                String value = ( String ) paramMap.get ( key );
                                if ( sb.length ( ) < 1 ) {
                                        sb.append ( key ).append ( "=" ).append ( value );
                                } else {
                                        sb.append ( "&" ).append ( key ).append ( "=" ).append ( value );
                                }
                        }
                        return sb.toString ( );
                }
        }

        public void cancelAll ( Object tag ) {
                mRequestQueue.cancelAll ( tag );
        }

        public interface RequestListener {
                public void onResponse ( JSONObject response );

                public void onError ( VolleyError error );
        }
}
