package com.example.aissms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Pizza extends AppCompatActivity {
    CardView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        p3=findViewById(R.id.p3);
        p4=findViewById(R.id.p4);
        p5=findViewById(R.id.p5);
        p6=findViewById(R.id.p6);
        p7=findViewById(R.id.p7);
        p8=findViewById(R.id.p8);
        p9=findViewById(R.id.p9);
        p10=findViewById(R.id.p10);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P1");
                startActivity(intent);
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P2");
                startActivity(intent);
            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P3");
                startActivity(intent);
            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P4");
                startActivity(intent);
            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P5");
                startActivity(intent);
            }
        });
        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P6");
                startActivity(intent);
            }
        });
        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P7");
                startActivity(intent);
            }
        });
        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P8");
                startActivity(intent);
            }
        });
        p9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P9");
                startActivity(intent);
            }
        });
        p10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), OrderSnapshot.class);
                intent.putExtra("name","P10");
                startActivity(intent);
            }
        });
    }
}
