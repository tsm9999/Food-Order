package com.example.bottombar_navigation_with_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSnapshot extends AppCompatActivity {
    TextView tv,amount,cost;
    ImageView add,remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_snapshot);
        tv=findViewById(R.id.ordername);
        cost=findViewById(R.id.cost);
        amount=findViewById(R.id.amount);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);

        Intent intent=getIntent();
       String s= intent.getStringExtra("name");

        tv.setText(s);
    }

    public void removeitem(View view)
    {
        int count= Integer.parseInt(amount.getText().toString());
        if(count>1)
        {
            count--;
            amount.setText(String.valueOf(count));
            cost.setText("Cost: Rs. "+String.valueOf(count*100));
        }
    }
    public void additem(View view)
    {
        int count= Integer.parseInt(amount.getText().toString());
        count++;
        amount.setText(String.valueOf(count));
        cost.setText("Cost: Rs. "+String.valueOf(count*100));


    }
}
