package com.rest;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Teddy on 2016-04-29.
 */
@RestController
public class PostRequestSnapshotsController {
    @CrossOrigin
    @RequestMapping("/c=3")

    public @ResponseBody String getSnaps(@RequestParam(value="season", defaultValue="deafult") String season, @RequestParam(value="start", defaultValue="deafult")String start, @RequestParam(value="end", defaultValue="deafult")String end) throws UnsupportedEncodingException {
        String url = "https://api.helldiversgame.com/0.3/";
        //Url parameters
        PostRequestSnapShots urlParameters = new PostRequestSnapShots(season, start, end);
        //url could be final in class
        System.setProperty("javax.net.ssl.trustStore", "arrowheadkeystore");
        Post_Request executePostRequest = new Post_Request(url, urlParameters.getUrlParameters());
        String result = executePostRequest.fetchData();

        return result;
    }
}
