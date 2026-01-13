package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WhetherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.PlaceHolders;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class WhetherService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AppCache appCache;
    @Value("${weather.api.key}")
    private  String apiKey ;
//    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;

    public WhetherResponse getWeather(String city) {
        WhetherResponse weatherResponse = redisService.get("Weather_Of_" + city, WhetherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY_KEY, city).replace(PlaceHolders.API_KEY, apiKey);
            ResponseEntity<WhetherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WhetherResponse.class);
            WhetherResponse body = response.getBody();
            if(body!=null){
                redisService.set("Weather_Of_" + city,body,300l);
            }
            return body;
        }
//        String requestBody="{\n" +
//                "    \"userName\":\"shailesh\",\n" +
//                "    \"password\":\"shailesh\"\n" +
//                "}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("key","value");
//        User user = User.builder().userName("shailesh").password("shailesh").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user,headers);
    }
}
