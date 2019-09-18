package com.example.aissms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CartFragment extends Fragment {
    TextView t1,t2;
    Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //just change the fragment_cart
        //with the fragment you want to inflate
        //like if the class is MenuFragment it should have R.layout.home_fragment
        //if it is CartFragment it should have R.layout.fragment_cart
        return inflater.inflate(R.layout.fragment_cart, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        t1=getActivity().findViewById(R.id.tv1);
        t2=getActivity().findViewById(R.id.tv2);
        button=getActivity().findViewById(R.id.pay);
        Intent intent=getActivity().getIntent();
        String s1=intent.getStringExtra("cartItem");
        int temp=intent.getIntExtra("cost",1);
        t1.setText(s1);
        t2.setText(String.valueOf(temp));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Paying", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
