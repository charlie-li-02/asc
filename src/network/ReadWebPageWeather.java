package network;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class ReadWebPageWeather {

    private String apiKey = "82f0c0ff2fa179f9ebb40b1020bf55e7";
    private String vancouver = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
    private String weatherString;

    private String weather;
    private int temp;
    private int max;
    private int min;
    private BufferedImage icon;

    public ReadWebPageWeather() {}

    //REQUIRES: vancouver, apiKey not null
    //MODIFIES: this
    //EFFECTS: accesses the weather api and stores the JSON file into weatherString
    public void readWeather() throws IOException {
        BufferedReader br = null;

        try {
            String urlString = vancouver + apiKey;
            URL url = new URL(urlString);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                weatherString = line;
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the description of the weather
    public void parseWeather() {
        JSONObject obj = new JSONObject(weatherString);

        JSONArray weatherArray = obj.getJSONArray("weather");
        for (int i = 0; i < weatherArray.length(); i++) {
            String description = weatherArray.getJSONObject(i).getString("main");
            weather = description;
        }

    }

    //REQUIRES: weatherString not null, readWeather called before
    //MODIFIES: nothing
    //EFFECTS: parses the JSON file and returns the temperature in celsius, originally kelvins in JSON
    public void parseTemperature() {
        JSONObject obj = new JSONObject(weatherString);

        double temperature = obj.getJSONObject("main").getDouble("temp");
        temp = (int) (temperature - 273.15);

//        double maxTemp = obj.getJSONObject("main").getDouble("temp_max");
//        max = (int) (maxTemp - 273.15);
//
//        double minTemp = obj.getJSONObject("main").getDouble("temp_min");
//        min = (int) (minTemp - 273.15);

    }

    public URL parseIcon() throws MalformedURLException {
        JSONObject obj = new JSONObject(weatherString);

        JSONArray weatherArray = obj.getJSONArray("weather");
        for (int i = 0; i < weatherArray.length(); i++) {
            String icon = weatherArray.getJSONObject(i).getString("icon");
            String iconURL = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
            URL url = new URL(iconURL);
            return url;
        }

        return null;
    }

    public void weatherIcon() throws IOException {
        BufferedImage iconImage = ImageIO.read(parseIcon());
        icon = iconImage;
    }

    public String getWeather() {
        return weather;
    }

    public int getTemp() {
        return temp;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public BufferedImage getIcon() {
        return icon;
    }
}
