package cn.wang;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;

import org.json.simple.JSONObject;




/**
 * Created by wang on 22/04/16.
 */

public class Main {
	
   
	
   private static DatabaseDAO buildDAO(){
		
		return new DerbyDatabaseDAO();
	}
	

	private static  Library buildModel(){
		return new Library(buildDAO());
	}

	private static Controller buildController(){
		
		return new Controller(buildModel());
	}
	
    public static void main(String args[]) throws UnsupportedEncodingException {
    	 
    	String url = "https://api.helldiversgame.com/0.3/";
         //URL parameters
        String urlParameters = "action=" + URLEncoder.encode("get_campaign_status", "UTF-8");
       
        System.setProperty("javax.net.ssl.trustStore", "/Users/wang/Desktop/arrowheadkeystore");

        ExecutePost executePost = new ExecutePost(url, urlParameters);
        
        JSONObject jsonArray = executePost.fetchJson();
       
		JsonHandler handler = new JsonHandler(jsonArray);
		Controller controller = buildController();
		//controller.updateDatabase(handler);
		Timer timer = new Timer(); ;
		timer.schedule(new java.util.TimerTask() { public void run() {controller.updateDatabase(handler);} }, 0, 1000); 
			
		
    }
		
   


}
