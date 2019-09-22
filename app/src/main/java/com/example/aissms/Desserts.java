package com.example.aissms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Desserts extends AppCompatActivity {
    CardView d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);


        d1=findViewById(R.id.d1);
        d2=findViewById(R.id.d2);
        d3=findViewById(R.id.d3);
        d4=findViewById(R.id.d4);
        d5=findViewById(R.id.d5);
        d6=findViewById(R.id.d6);
        d7=findViewById(R.id.d7);
        d8=findViewById(R.id.d8);
        d9=findViewById(R.id.d9);
        d10=findViewById(R.id.d10);

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D1");
                startActivity(intent);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D2");
                startActivity(intent);
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D3");
                startActivity(intent);
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D4");
                startActivity(intent);
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D5");
                startActivity(intent);
            }
        });
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D6");
                startActivity(intent);
            }
        });
        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D7");
                startActivity(intent);
            }
        });
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D8");
                startActivity(intent);
            }
        });
        d9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D9");
                startActivity(intent);
            }
        });
        d10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","D10");
                startActivity(intent);
            }
        });
    }
}
