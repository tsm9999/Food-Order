package com.example.bottombar_navigation_with_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    CardView cvBurger,cvPizza,cvDessert;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        cvBurger=getActivity().findViewById(R.id.cvburger);
        cvPizza=getActivity().findViewById(R.id.cvpizza);
        cvDessert=getActivity().findViewById(R.id.cvdesserts);

        cvBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Burgers.class);
                startActivity(intent);
            }
        });
        cvPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Pizza.class);
                startActivity(intent);
            }
        });
        cvDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Desserts.class);
                startActivity(intent);
            }
        });


    }
}
