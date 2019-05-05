package com.example.restservice_test2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {


    CardView small, medium, large;
    TextView pizzaname, pizzaprice, pizzadescription, smallpizza, largepizza, mediumpizza, allprice1, itemcount, minplus;
    ImageView pizzaimage;
    Button plus, minus, addtoCart;
    CheckBox addcheese;

    //check additional cheese
    Boolean is_checkbox_Checked_or_not = false;

    //Default is set to small size
    int selected_pizza_type = 1;

    //Default quantity size set to one
    int qty = 1;


     String Pizzaname1;
     String PizzaDescription1;
     String Imageurl;
     Double Allprice;

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
        allprice1 = (TextView) findViewById(R.id.allprice);
        itemcount = (TextView) findViewById(R.id.qty);
        minplus = (TextView) findViewById(R.id.minusplus);

        pizzaimage = (ImageView) findViewById(R.id.image);

        small = (CardView) findViewById(R.id.small);
        medium = (CardView) findViewById(R.id.medium);
        large = (CardView) findViewById(R.id.large);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        addtoCart = (Button) findViewById(R.id.addToCart);


        addcheese = (CheckBox) findViewById(R.id.addcheese);

        //Default selection to small pizza
        small.setCardBackgroundColor(Color.GRAY);

        Intent intent = getIntent();
        final String imgurl = intent.getStringExtra("IMG");
        final Double pizzaprice1 = intent.getDoubleExtra("PRICE",0.00);
        final Double pizzaprice3 = intent.getDoubleExtra("PIZZA_LARGE",0.00);
        final Double pizzaprice2 = intent.getDoubleExtra("PIZZA_MEDIUM",0.00);
        final String pizzaname1 = intent.getStringExtra("NAME");
        final String pizzadescription1 = intent.getStringExtra("DETAILS");

        final Double pizzawithCheese1 = pizzaprice1+160;
        final Double pizzawithCheese2 = pizzaprice2+160;
        final Double pizzawithCheese3 = pizzaprice3+160;

        Imageurl = imgurl;
        PizzaDescription1 = pizzadescription1;
        Pizzaname1 = pizzaname1;


        Glide.with(DetailActivity.this).load(imgurl).into(pizzaimage);
        pizzaname.setText(pizzaname1);
        pizzadescription.setText(pizzadescription1);
        pizzaprice.setText("Rs." + pizzaprice1);

        allprice1.setText("Rs. " + pizzaprice1);


        // disable when starting add additional cheese to pizza
        if(addcheese.isChecked() == true){
            addcheese.setChecked(false);
        }

        // adding or not additional cheese to pizza
        addcheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding cheese
                if(is_checkbox_Checked_or_not == false) {
                    //is pizza type small
                    if (selected_pizza_type == 1) {
                        Double pizzawithCheese11 = pizzawithCheese1 * qty;
                        allprice1.setText("Rs." + pizzawithCheese11);
                        pizzaprice.setText("Rs." + pizzawithCheese11);
                        Allprice = pizzawithCheese11;
                        is_checkbox_Checked_or_not = true;

                    }else if(selected_pizza_type == 2){
                        Double pizzawithCheese22 = pizzawithCheese2 * qty;
                        allprice1.setText("Rs." + pizzawithCheese22);
                        pizzaprice.setText("Rs." + pizzawithCheese22);
                        Allprice = pizzawithCheese22;
                        is_checkbox_Checked_or_not = true;
                    }else if(selected_pizza_type == 3){
                        Double pizzawithCheese33 = pizzawithCheese3 * qty;
                        allprice1.setText("Rs." + pizzawithCheese33);
                        pizzaprice.setText("Rs." + pizzawithCheese33);
                        Allprice = pizzawithCheese33;
                        is_checkbox_Checked_or_not = true;
                    }
                }else{
                    if(selected_pizza_type == 1) {
                        Double pizzaprice11 = pizzaprice1 * qty;
                        allprice1.setText("Rs." + pizzaprice11);
                        pizzaprice.setText("Rs." + pizzaprice11);
                        Allprice = pizzaprice11;
                        is_checkbox_Checked_or_not = false;

                    }else if(selected_pizza_type == 2){
                        Double pizzaprice22 = pizzaprice2 * qty;
                        allprice1.setText("Rs." + pizzaprice22);
                        pizzaprice.setText("Rs." + pizzaprice22);
                        Allprice = pizzaprice22;
                        is_checkbox_Checked_or_not = false;

                    }else if(selected_pizza_type == 3){
                        Double pizzaprice33 = pizzaprice3 * qty;
                        allprice1.setText("Rs." + pizzaprice33);
                        pizzaprice.setText("Rs." + pizzaprice33);
                        Allprice = pizzaprice33;
                        is_checkbox_Checked_or_not = false;
                    }
                }
            }
        });

        //select pizza type
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color
                small.setCardBackgroundColor(Color.GRAY);
                medium.setCardBackgroundColor(Color.green(1));
                large.setCardBackgroundColor(Color.green(1));

                //set_pizza_type_to_small
                selected_pizza_type = 1;

                //change price according to selection
                allprice1.setText("Rs." + pizzaprice1);
                pizzaprice.setText("Rs." + pizzaprice1);

                //Reset number of items
                qty = 1;
                itemcount.setText(1 + " Items");
                minplus.setText(1 + "");

                // disable when starting add additional cheese to pizza
                if(addcheese.isChecked() == true){
                    addcheese.setChecked(false);
                }
                is_checkbox_Checked_or_not = false;
            }
        });

        //select pizza type
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color
                medium.setCardBackgroundColor(Color.GRAY);
                small.setCardBackgroundColor(Color.green(1));
                large.setCardBackgroundColor(Color.green(1));

                //set_pizza_type_to_medium
                selected_pizza_type = 2;

                //change price according to selection
                allprice1.setText("Rs." + pizzaprice2);
                pizzaprice.setText("Rs." + pizzaprice2);

                //Reset number of items
                qty = 1;
                itemcount.setText(1 + " Items");
                minplus.setText(1 + "");

                // disable when starting add additional cheese to pizza
                if(addcheese.isChecked() == true){
                    addcheese.setChecked(false);
                }
                is_checkbox_Checked_or_not = false;
            }
        });

        //select pizza type
        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //color
                medium.setCardBackgroundColor(Color.green(1));
                small.setCardBackgroundColor(Color.green(1));
                large.setCardBackgroundColor(Color.GRAY);

                //set_pizza_type_to_large
                selected_pizza_type = 3;

                //change price according to selection
                allprice1.setText("Rs." + pizzaprice3);
                pizzaprice.setText("Rs." + pizzaprice3);

                //Reset number of items
                qty = 1;
                itemcount.setText(1 + " Items");
                minplus.setText(1 + "");

                // disable when starting add additional cheese to pizza
                if(addcheese.isChecked() == true){
                    addcheese.setChecked(false);
                }
                is_checkbox_Checked_or_not = false;
            }
        });

        //number of pizza need +
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Maximum five pizza can order
                if(qty < 5) {
                    qty++;
                }else{
                    Toast.makeText(DetailActivity.this, "FIVE Pizzas can be ordered maximally!", Toast.LENGTH_SHORT).show();
                }

                //calculate small pizza price
                if(selected_pizza_type == 1){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese11 = pizzawithCheese1 * qty;
                        allprice1.setText("Rs." + pizzawithCheese11);
                        pizzaprice.setText("Rs." + pizzawithCheese11);
                        Allprice = pizzawithCheese11;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice11 = pizzaprice1 * qty;
                        allprice1.setText("Rs." + pizzaprice11);
                        pizzaprice.setText("Rs." + pizzaprice11);
                        Allprice = pizzaprice11;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }else if(selected_pizza_type == 2){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese22 = pizzawithCheese2 * qty;
                        allprice1.setText("Rs." + pizzawithCheese22);
                        pizzaprice.setText("Rs." + pizzawithCheese22);
                        Allprice = pizzawithCheese22;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice22 = pizzaprice2 * qty;
                        allprice1.setText("Rs." + pizzaprice22);
                        pizzaprice.setText("Rs." + pizzaprice22);
                        Allprice = pizzaprice22;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }else if(selected_pizza_type == 3){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese33 = pizzawithCheese3 * qty;
                        allprice1.setText("Rs." + pizzawithCheese33);
                        pizzaprice.setText("Rs." + pizzawithCheese33);
                        Allprice = pizzawithCheese33;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice33 = pizzaprice3 * qty;
                        allprice1.setText("Rs." + pizzaprice33);
                        pizzaprice.setText("Rs." + pizzaprice33);
                        Allprice = pizzaprice33;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }

            }
        });

        // number of pizza need -
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //should select minimum one pizza
                if(qty > 1){
                    qty--;
                }

                //calculate small pizza price
                if(selected_pizza_type == 1){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese11 = pizzawithCheese1 * qty;
                        allprice1.setText("Rs." + pizzawithCheese11);
                        pizzaprice.setText("Rs." + pizzawithCheese11);
                        Allprice = pizzawithCheese11;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice11 = pizzaprice1 * qty;
                        allprice1.setText("Rs." + pizzaprice11);
                        pizzaprice.setText("Rs." + pizzaprice11);
                        Allprice = pizzaprice11;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }else if(selected_pizza_type == 2){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese22 = pizzawithCheese2 * qty;
                        allprice1.setText("Rs." + pizzawithCheese22);
                        pizzaprice.setText("Rs." + pizzawithCheese22);
                        Allprice = pizzawithCheese22;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice22 = pizzaprice2 * qty;
                        allprice1.setText("Rs." + pizzaprice22);
                        pizzaprice.setText("Rs." + pizzaprice22);
                        Allprice = pizzaprice22;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }else if(selected_pizza_type == 3){

                    //additional cheese check
                    if(is_checkbox_Checked_or_not == true){
                        Double pizzawithCheese33 = pizzawithCheese3 * qty;
                        allprice1.setText("Rs." + pizzawithCheese33);
                        pizzaprice.setText("Rs." + pizzawithCheese33);
                        Allprice = pizzawithCheese33;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }else{
                        Double pizzaprice33 = pizzaprice3 * qty;
                        allprice1.setText("Rs." + pizzaprice33);
                        pizzaprice.setText("Rs." + pizzaprice33);
                        Allprice = pizzaprice33;
                        itemcount.setText(qty + " Items");
                        minplus.setText(qty + "");
                    }
                }
            }
        });

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        addTocart();
            }
        });
    }

    public void addTocart(){

        Intent cartdetails = new Intent(this, SignUpActivity.class);
/*
        cartdetails.putExtra("NAME", Pizzaname1);
        cartdetails.putExtra("DETAILS", PizzaDescription1);
        cartdetails.putExtra("PRICE", Allprice);
        cartdetails.putExtra("IMG", Imageurl);
*/
        startActivity(cartdetails);
    }

}
