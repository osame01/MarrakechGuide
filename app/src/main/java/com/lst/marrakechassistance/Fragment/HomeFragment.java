package com.lst.marrakechassistance.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lst.marrakechassistance.Adapter.HotelMainAdapter;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<Hotel> hotels;
    private RecyclerView nearRecyclerView, popularRecyclerView;
    private HotelMainAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        hotels = generateItems();

        nearRecyclerView = rootView.findViewById(R.id.recyclerViewNear);
        nearRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new HotelMainAdapter(hotels);
        nearRecyclerView.setAdapter(adapter);

        popularRecyclerView = rootView.findViewById(R.id.recyclerViewPopular);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularRecyclerView.setAdapter(adapter);

        return  rootView;
    }

    private ArrayList<Hotel> generateItems() {
        ArrayList<Hotel> ItemsArrayList = new ArrayList<>();

        ItemsArrayList.add(new Hotel("The heart wants what it wants","adresse","his is a modern fairy tale\n" +
                "No happy endings\n" +
                "No wind in our sails\n" +
                "But I can't imagine a life without\n" +
                "Breathless moments\n" +
                "Breaking me down, down, down, down",2,1,200,"hotel",true));

        ItemsArrayList.add(new Hotel("The heart wants what it wants","adresse","his is a modern fairy tale\n" +
                "No happy endings\n" +
                "No wind in our sails\n" +
                "But I can't imagine a life without\n" +
                "Breathless moments\n" +
                "Breaking me down, down, down, down",2,1,200,"activity",false));

        ItemsArrayList.add(new Hotel("The heart wants what it wants","hotel","his is a modern fairy tale\n" +
                "No happy endings\n" +
                "No wind in our sails\n" +
                "But I can't imagine a life without\n" +
                "Breathless moments\n" +
                "Breaking me down, down, down, down",2,1,200,"restaurant",true));
        return ItemsArrayList;
    }
}