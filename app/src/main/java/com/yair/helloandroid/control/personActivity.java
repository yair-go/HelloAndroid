package com.yair.helloandroid.control;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yair.helloandroid.R;
import com.yair.helloandroid.model.Const;
import com.yair.helloandroid.model.entities.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class personActivity extends AppCompatActivity {

    TextView responseHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Intent myIntent = getIntent();

        //  a -
//        Long personId = myIntent.getLongExtra("Id", -1);
//        String personName = myIntent.getStringExtra("Name");
//
//        Person person = new Person();
//        person.setId(personId);
//        person.setName(personName);

        // b- if we pass the Person to the function:
       // Person person = (Person) myIntent.getSerializableExtra("Person");
        Person person = (Person) myIntent.getSerializableExtra(Const.Person_KEY);
        TextView nameTV = (TextView) findViewById(R.id.nameTV);
        nameTV.setText(person.getName());
        responseHttp = (TextView) findViewById(R.id.responeHttp);
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                String response = null;
                try {
                    response = GET("http://ygoldsht.vlab.jct.ac.il/db.php");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
            @Override
            protected void onPostExecute(String response) {
                responseHttp.setText(response);
            }
        }.execute();
    }
   /* private static String GET(String url) throws Exception {
        Socket s = null;
       // s = new Socket(InetAddress.getByName("http://ygoldsht.vlab.jct.ac.il"), 80);
        s = new Socket("109.226.10.68",80);
        PrintWriter pw = null;
        pw = new PrintWriter(s.getOutputStream());

        pw.println("GET" + url + "  HTTP/1.1");
        pw.println("Host: ygoldsht.vlab.jct.ac.il");
        pw.println();
        pw.flush();
        BufferedReader br = null;
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

       String t;
       // while ((t = br.readLine()) != null) System.out.println(t);
        StringBuffer response = new StringBuffer();
        while ((t = br.readLine()) != null)
            response.append(t);
        br.close();
        return response.toString();
    }*/

    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {// success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //  print result
            return response.toString();
        } else {
            return "";
        }
    }
    private static String POST(String url, Map<String,Object> params) throws IOException {
        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else return "";
    }

}
