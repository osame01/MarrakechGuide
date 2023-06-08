package com.lst.marrakechassistance.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lst.marrakechassistance.Activity.HotelsActivity;
import com.lst.marrakechassistance.Activity.MustVisitActivity;
import com.lst.marrakechassistance.Activity.RestaurantsActivity;
import com.lst.marrakechassistance.Activity.SearchActivity;
import com.lst.marrakechassistance.Adapter.HotelMainAdapter;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.Utils.HotelUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<Hotel> hotels;
    private RecyclerView nearRecyclerView, popularRecyclerView;
    private HotelMainAdapter adapter;

    // Make a GridLayout item to reference the View GridLayout
    private GridLayout gridLayout;
    private EditText searchBar;

    // Store the Location of the user
    Location userLocation;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        // Initialise The gridLayout item

        gridLayout = rootView.findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);
            childView.setOnClickListener((view)->{
                int viewId = view.getId();
                if (viewId == R.id.FragHotel){
                    Intent intent = new Intent(getContext(), HotelsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragRest){
                    Intent intent = new Intent(getContext(), RestaurantsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragTransport) {
                    Intent intent = new Intent(getContext(), RestaurantsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragMV) {
                    Intent intent = new Intent(getContext(), MustVisitActivity.class);
                    startActivity(intent);
                }
            });
        }

        // define The search Bar
        // We're not doing the search here so for that We will Make A new SearchActivity
        searchBar = rootView.findViewById(R.id.recherche);
        searchBar.setOnClickListener((view)->{
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
        }
        );

        // Load the hotels From the local Data
        HotelUtil hotelUtil = new HotelUtil(getActivity());
        hotels = (ArrayList<Hotel>) hotelUtil.loadHotelsFromCSV();

        nearRecyclerView = rootView.findViewById(R.id.recyclerViewNear);
        nearRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new HotelMainAdapter(hotels);
        nearRecyclerView.setAdapter(adapter);

        popularRecyclerView = rootView.findViewById(R.id.recyclerViewPopular);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularRecyclerView.setAdapter(adapter);

        return  rootView;
    }

    private List<Hotel> calculateDistancesToHotels(List<Hotel> hotelsList) {
        if (userLocation != null && hotelsList != null) {
            for (Hotel hotel : hotelsList) {
                Location hotelLocation = new Location("");
                hotelLocation.setLatitude(hotel.getLatitude());
                hotelLocation.setLongitude(hotel.getLongitude());
                float distance = userLocation.distanceTo(hotelLocation);
                hotel.setDistance(distance);
            }

            // Sort hotels by distance
            hotelsList.sort((hotel1, hotel2) -> Float.compare(hotel1.getDistance(), hotel2.getDistance()));

        }
        return hotelsList;
    }

}