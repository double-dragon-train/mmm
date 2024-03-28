package com.spring.mmm.domain.weathers.service;

import com.spring.mmm.common.exception.InternalServerCaughtException;
import com.spring.mmm.domain.recommends.controller.response.FoodInformation;
import com.spring.mmm.domain.recommends.controller.response.WeatherDTO;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherService {

    @Value("${api.service-key}")
    private String serviceKey;
    private final FoodRepository foodRepository;

    public WeatherDTO getWeather(double latitude, double longitude)  {
        try {
            getGRID tmp = convertGRID_GPS(latitude, longitude);
            LocalDate today = LocalDate.now();
            String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(formattedDate, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0900", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(String.valueOf((int) tmp.x), "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(String.valueOf((int) tmp.y), "UTF-8")); /*예보지점의 Y 좌표값*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            String jsonString = sb.toString();
            JSONObject objData = new JSONObject(jsonString);
            JSONObject objResponse = objData.getJSONObject("response");
            JSONObject objBody = objResponse.getJSONObject("body");
            JSONObject objItems = objBody.getJSONObject("items");
            JSONArray itemArray = objItems.getJSONArray("item");

            float PTY = 0, RN1 = 0, REH=0, T1H=0, UUU=0, VVV=0, VEC=0, WSD=0;

            for (int i = 0; i < itemArray.length(); i++) {
                JSONObject item = itemArray.getJSONObject(i);
                String category = item.getString("category");
                String obsrValue = item.getString("obsrValue");
                switch (category) {
                    case "PTY" -> PTY = Float.valueOf(obsrValue);
                    case "RN1" -> RN1 = Float.valueOf(obsrValue);
                    case "REH" -> REH = Float.valueOf(obsrValue);
                    case "T1H" -> T1H = Float.valueOf(obsrValue);
                    case "UUU" -> UUU = Float.valueOf(obsrValue);
                    case "VVV" -> VVV = Float.valueOf(obsrValue);
                    case "VEC" -> VEC = Float.valueOf(obsrValue);
                    case "WSD" -> WSD = Float.valueOf(obsrValue);
                }
            }

            return WeatherDTO.create(PTY, RN1, REH, T1H, UUU, VVV, VEC, WSD);
        }
        catch (IOException e) {
            throw new InternalServerCaughtException(e, this);
        }
    }

    public static class Grid{
        public double x;
        public double y;
    }

    public Grid convertGPStoGPS(double latitude, double longitude) {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 210 / GRID; // 기준점 X좌표(GRID)
        double YO = 675 / GRID; // 기준점 Y좌표(GRID)

        double DEGRAD = Math.PI / 180.0;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        getGRID xy = new getGRID();

        double ra = Math.tan(Math.PI * 0.25 + (latitude) * DEGRAD * 0.5);
        if (ra<0) {
            ra = re * sf / (-1 * Math.pow(-ra, sn));
        } else {
            ra = re * sf / Math.pow(ra, sn);
        }
        double theta = longitude * DEGRAD - olon;
        if (theta > Math.PI) {theta -= 2.0 * Math.PI;}
        if (theta < -Math.PI) {theta += 2.0 * Math.PI;}
        theta *= sn;
        xy.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
        xy.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

        return xy;
    }

    public FoodInformation getWeatherFood(WeatherDTO weatherDTO) {
        int weatherId = 0;

        if (weatherDTO.getT1H() > 30) {
            weatherId = 2;
        }
        if (weatherDTO.getRN1() >= 0.5) {
            weatherId = 1;
        }
        log.debug("weatherDTO.getT1H() : {}",weatherDTO.getT1H());
        log.debug("weatherDTO.getRN1() : {}",weatherDTO.getRN1());
        if (weatherId != 0) {
            log.debug("weatherId : {}", weatherId);
            log.debug("수제비나와라 : {}", foodRepository.findByName("수제비"));
            FoodEntity soojebi = foodRepository.findByName("수제비");
            log.debug("수제비의 id : {}", soojebi.getFoodId());
            log.debug("수제비의 foodWeatherEntity : {}",soojebi.getFoodWeatherEntity());
            log.debug("수제비의 weatherId : {}",soojebi.getFoodWeatherEntity().getWeatherEntity());
            List<FoodEntity> foodEntities = foodRepository.findByWeatherId(weatherId);
            log.debug("foodEntities : {}", foodEntities);
            Random random = new Random();
            int randomIndex = random.nextInt(foodEntities.size());
            FoodEntity randomFoodEntity = foodEntities.get(randomIndex);

            return FoodInformation.builder()
                    .foodId(randomFoodEntity.getFoodId())
                    .name(randomFoodEntity.getName())
                    .imageSrc(randomFoodEntity.getImage())
                    .build();
        } else {
            throw new RecommendException(RecommendErrorCode.FOOD_RECOMMEND_NOT_FOUND);
        }

    }

}