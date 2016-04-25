package com.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Teddy on 2016-04-22.
 */
@RestController
public class Post_RequestController {
    @RequestMapping("/post")
    public String post_request(@RequestParam(value="action", defaultValue="get_campaign") String name)
    {
        String url = "https://api.helldiversgame.com/0.3/";
        //Url parameters
        String urlParameters = null;
        try {
            urlParameters = "action=" + URLEncoder.encode("get_campaign_status", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("catch in post_requestController");
            e.printStackTrace();
        }

        System.setProperty("javax.net.ssl.trustStore", "C:/Users/Teddy/Documents/KTH/Termin_2/Project/Mellan_Server/arrowheadkeystore");
        Post_Request executePostRequest = new Post_Request(url, urlParameters);
        String result = executePostRequest.fetchData();

        return result;
    }

}
