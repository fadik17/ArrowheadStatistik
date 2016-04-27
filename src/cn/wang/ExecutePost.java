package cn.wang;

import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 * Created by wang on 19/04/16.
 */

public class ExecutePost {


    private  String url;
    private String urlParameters;

    public ExecutePost(String url, String urlParameters) {
        this.url = url;
        this.urlParameters = urlParameters;
    }

    public  String fetchData()
    {

        HttpURLConnection connection = null;
        try {
            //Create connection
            URL urlArrowHeadMasterServer = new URL(url);
            connection = (HttpURLConnection)urlArrowHeadMasterServer.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            return response.toString();
            

        } catch (Exception e) {

            e.printStackTrace();
           
            return null;

        } finally {
           
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

   
	public  JSONObject fetchJson()
    {

        HttpURLConnection connection = null;
        try {
            //Create connection
            
            URL urlArrowHeadMasterServer = new URL(url);
            connection = (HttpURLConnection)urlArrowHeadMasterServer.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            /*String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }*/
            
            JSONParser parser = new JSONParser();
            Object object = parser.parse(rd);
			JSONObject jsonObject = (JSONObject) object;
            rd.close();
            
            return jsonObject;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {
            
            if(connection != null) {
                connection.disconnect();
            }
        }
    }


}
