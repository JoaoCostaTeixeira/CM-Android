package com.example.cmhw2;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Second2 extends Fragment {
    String message="Aveiro";
    TextView text;
    TextView temperature;
    TextView descript;
    TextView max;
    TextView min;
    ImageView image;

    ImageView pressureimg;
    TextView pressure;

    ImageView humidityimg;
    TextView humidity;

    ImageView visibilityimage;
    TextView visibility;

    ImageView windimage;
    TextView wind;

    public Second2() {
    }

    public Second2(String message) {
        // Required empty public constructor
        this.message = message;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_secondfragment, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        text = v.findViewById(R.id.textView);

        text.setText(message);
        image = v.findViewById(R.id.imageView);
        temperature = v.findViewById(R.id.textView3);
        descript = v.findViewById(R.id.textView2);

        max = v.findViewById(R.id.textView5);
        min = v.findViewById(R.id.textView6);

        pressure = v.findViewById(R.id.textView8);
        pressureimg = v.findViewById(R.id.imageView4);

        humidity = v.findViewById(R.id.textView7);
        humidityimg = v.findViewById(R.id.imageView5);

        visibility = v.findViewById(R.id.textView9);
        visibilityimage = v.findViewById(R.id.imageView6);

        wind = v.findViewById(R.id.textView4);
        windimage = v.findViewById(R.id.imageView7);

        getMeto();
        return v;
    }




    private void getMeto() {
        String charset = "UTF-8";
        Resources res = getResources();
        String apikey = res.getString(R.string.APIKEU);
        final String API = "&appid=" + apikey;
        final String chosecity = "q=" + message + "&units=metric";
        final String URL_PREFIX = "http://api.openweathermap.org/data/2.5/weather?";
        HttpURLConnection connection = null;
        try {
            String url2 = URL_PREFIX + chosecity + API;
            URL url = new URL(url2);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }


            in.close();
            JSONObject parser = new JSONObject(response.toString());

            JSONObject weather = parser.getJSONArray("weather").getJSONObject(0);
            JSONObject mainw = parser.getJSONObject("main");
            String icon = weather.getString("icon");

            Picasso.with(getContext())
                    .load("http://openweathermap.org/img/wn/" + icon + "@2x.png")
                    .into(image);
            Picasso.with(getContext())
                    .load("https://cdn2.iconfinder.com/data/icons/fillix-weather-and-climate/128/weather_climate_meteorology-05-512.png")
                    .into(pressureimg);
            Picasso.with(getContext())
                    .load("https://cdn1.iconfinder.com/data/icons/weather-470/128/HUMIDITY-512.png")
                    .into(humidityimg);
            Picasso.with(getContext())
                    .load("https://cdn4.iconfinder.com/data/icons/weather-forecast-26/96/reduced_visibility_foggy_condition_road_weather_low-512.png")
                    .into(visibilityimage);
            Picasso.with(getContext())
                    .load("https://cdn4.iconfinder.com/data/icons/nature-2-10/32/143-512.png")
                    .into(windimage);


            temperature.setText("" + (int) mainw.getDouble("temp") + "ºC");
            descript.setText(weather.getString("description"));
            min.setText("" + (int) mainw.getDouble("temp_min") + "ºC");
            max.setText("" + (int) mainw.getDouble("temp_max") + "ºC");

            if(mainw.optInt("pressure")!= 0){
                pressure.setText("" + (int) mainw.getInt("pressure") + " hPa");
            }else{
                pressure.setText("Without information" );
            }

            if(mainw.optInt("humidity") != 0){
                humidity.setText("" + (int) mainw.getInt("humidity") + "%");
            }else{
                humidity.setText("Without information" );
            }

            if(parser.optInt("visibility") != 0){
                System.out.println("\n\n\n" + parser.optInt("visibility"));
                visibility.setText("" + parser.getInt("visibility") + " m");
            }else{
                visibility.setText("Without information" );
            }

            if( parser.getJSONObject("wind").optDouble("speed") != 0){
                wind.setText("" + parser.getJSONObject("wind").getDouble("speed") + " m/s");
            }else{
                wind.setText("Without information" );
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
