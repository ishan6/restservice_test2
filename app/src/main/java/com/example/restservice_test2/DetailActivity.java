package com.example.restservice_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class DetailActivity extends AppCompatActivity {

    TextView pizzaname, pizzaprice, pizzadescription, smallpizza, largepizza, mediumpizza;
    ImageView pizzaimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        pizzaname = (TextView) findViewById(R.id.name);
        smallpizza = (TextView) findViewById(R.id.smallpizzza);
        mediumpizza = (TextView) findViewById(R.id.mediumpizzza);
        largepizza = (TextView) findViewById(R.id.largepizzza);
        pizzadescription = (TextView) findViewById(R.id.description);
        pizzaprice = (TextView) findViewById(R.id.price);
        pizzaimage = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();
        String imgurl = intent.getStringExtra("IMG");
        Double pizzaprice1 = intent.getDoubleExtra("PRICE",0.00);
        Double pizzaprice2 = intent.getDoubleExtra("PIZZA_LARGE",0.00);
        Double pizzaprice3 = intent.getDoubleExtra("PIZZA_MEDIUM",0.00);


        Glide.with(DetailActivity.this).load(imgurl).into(pizzaimage);
        pizzaname.setText(getIntent().getStringExtra("NAME"));
        pizzadescription.setText(getIntent().getStringExtra("DETAILS"));
        pizzaprice.setText("Rs." + pizzaprice1);

        smallpizza.setText("Rs. " + pizzaprice1);
        largepizza.setText("Rs. " + pizzaprice2);
        mediumpizza.setText("Rs. " + pizzaprice3);



    }
}
