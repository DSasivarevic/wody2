package controllers;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by dasas on 07/10/15.
 */
public class Server {

    public void saveWOD(String wod){
        new WODSave(wod).execute();
    }



    private class WODSave extends AsyncTask<String, Void, Void> {

        private String w;
        public WODSave(String s){
            this.w = s;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                //String s = "";
                Log.d("ERROR", "HTTP CONNECT");

                HttpURLConnection con = startServer("wod_save.php");

                con.getOutputStream().write(("wod=" + w).getBytes());


                Scanner scanner = new Scanner(con.getInputStream());
                Log.d("ERROR", "HTTP CONNECT2");

                String s1 = "";
                while (scanner.hasNext()) {
                    s1 += scanner.nextLine();
                }
                Log.d("ERROR",s1);
                scanner.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        private HttpURLConnection startServer(String serverurl) throws Exception {
            URL url = new URL("http://72.9.151.78/~wody/"+serverurl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            return  con;
        }
    }
}


