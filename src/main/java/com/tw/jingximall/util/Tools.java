package com.tw.jingximall.util;

import com.tw.jingximall.entity.OrderInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.sql.Date;

/**
 * Created by wangjie on 2018/5/16.
 */
public class Tools {
    public static HttpHeaders getHttpHeader(HttpServletRequest request,int id){
        URI url = URI.create(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getRequestURI() + "/" + id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(url);
        return httpHeaders;
    }
    public static String getDateString() {
        return new Date(System.currentTimeMillis()).toString();
    }
}
