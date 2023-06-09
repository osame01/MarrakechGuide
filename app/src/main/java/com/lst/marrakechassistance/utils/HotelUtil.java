package com.lst.marrakechassistance.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.lst.marrakechassistance.Model.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HotelUtil {
    private final Context context;

    public HotelUtil(Context context) {
        this.context = context;
    }
    public List<Hotel> loadHotelsFromCSV(){
        List<Hotel> hotels = new ArrayList<>();
        AssetManager manager = context.getAssets();
        try {
            InputStream inputStream = manager.open("hotels.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Skip the first Line
            bufferedReader.readLine();

            String line ;
            while ( (line=bufferedReader.readLine()) != null){
                String[] columns = line.split(",");
                String name = columns[0];
                String address = columns[1];
                String price = columns[2];
                String lat = columns[3];
                String lon = columns[4];

                hotels.add(new Hotel(name, address, Integer.parseInt(price),"marrakech" ,Double.parseDouble(lat),Double.parseDouble(lon)));

            }

            // Close the file
            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return hotels;
    }
}
