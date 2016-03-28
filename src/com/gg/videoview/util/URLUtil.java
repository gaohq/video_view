package com.gg.videoview.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by gaohaoqing
 * Date : 16/3/2
 * Time : 11:58
 */
public class URLUtil {

    /**
     * 返回安全的URL
     *
     * @param downloadUrl 没转义之前的URL
     * @return
     */
    public static String returnSafeURL(String downloadUrl) {
        String hostURL = downloadUrl.substring(0, downloadUrl.lastIndexOf("/"));
        String url = downloadUrl.substring(downloadUrl.lastIndexOf("/"), downloadUrl.length());
        String safeURL = "";
        try {
            safeURL = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("video-view", "转义失败");
            return "";
        }
        return hostURL + safeURL;
    }

}
