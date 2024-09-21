package ac.rs.singidunum.aplikacija;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Api {
    public static class Element{
        private String name, value;

        public Element(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public static void postDataJson(String url, final List<Element> data, final ReadDataHandler rdh){
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String odgovor = "";

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder();

                    for(Element element : data){
                        builder.appendQueryParameter(element.getName(), element.getValue());
                    }

                    String postData = builder.build().getEncodedQuery();

                    OutputStream os = con.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(postData);
                    bw.flush();
                    bw.close();
                    con.getOutputStream().close();
                    con.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String red;
                    while ((red = br.readLine()) != null){
                        odgovor += red + "\n";
                    }
                    br.close();
                    con.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return odgovor;
            }

            @Override
            protected void onPostExecute(String odgovor) {
                rdh.setJson(odgovor);
                rdh.sendEmptyMessage(0);
            }
        };
        task.execute(url);
    }
}