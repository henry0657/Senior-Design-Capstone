package htoups2.prototype;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

/**
 * Created by Henry on 9/26/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String>{
    String JSON_STRING;
    Context context;
    AlertDialog alertDialog;


    public BackgroundWorker (Context ctx) {
        context = ctx;

    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected String doInBackground(String... params){
        String type = params[0];
        String post_url = "http://roadsideflooddetector.co.nf/post_in_db.php";
        String get_url = "http://roadsideflooddetector.co.nf/get_from_db.php";
        if(type.equals("post")){
            try {
                String street = params[1];
                String latitude = params[2];
                String longitude = params[3];
                String height = params[4];
                URL url = new URL(post_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("street", "UTF-8")+"="+URLEncoder.encode(street, "UTF-8")+"&"+
                        URLEncoder.encode("latitude", "UTF-8")+"="+URLEncoder.encode(latitude, "UTF-8")+"&"+
                        URLEncoder.encode("longitude", "UTF-8")+"="+URLEncoder.encode(longitude, "UTF-8")+"&"+
                        URLEncoder.encode("height", "UTF-8")+"="+URLEncoder.encode(height, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result ="";
                String line;
                while((line = bufferedReader.readLine()) != null) {
                        result +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
             catch (MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }


        }
       else if(type.equals("get")){

            try {
                URL url = new URL(get_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while((JSON_STRING = bufferedReader.readLine())  != null)
                {

                stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        if(result.endsWith("!")) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }


}
