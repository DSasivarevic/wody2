package controllers;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import models.WOD;

/**
 * Created by dasas on 07/10/15.
 */
public class Server {

    public void saveWOD(WOD wod){
        new WODSave(wod).execute();
    }


    private class WODSave extends AsyncTask<Void, Void, Void> {

        private WOD w;
        public WODSave(WOD w){
            this.w = w;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Gson gson = new Gson();
                //String s = gson.toJson(w);
                String s = "";
                HttpURLConnection con = startServer("wod_save.php");
                con.getOutputStream().write(("wod="+s).getBytes());

                Scanner scanner = new Scanner(con.getInputStream());
                String s1 = "";
                while (scanner.hasNext()) {
                    s1 += scanner.nextLine();
                }
                System.out.println(s1);
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


