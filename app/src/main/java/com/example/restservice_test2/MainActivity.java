package com.example.restservice_test2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  final String URL_DATA = "http://192.168.42.133:8080/demo/all";
    RecyclerView recyclerView;
    ProductAdapter adapter;

    List<Product> productslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productslist = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        loadRecyclerviewData();

    }

    private void loadRecyclerviewData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data From Server..");
        progressDialog.dismiss();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray products  = new JSONArray(response);
                    for (int i =0; i<products.length(); i++){
                        JSONObject productobject  = products.getJSONObject(i);

                        int id = productobject.getInt("id");
                        String name = productobject.getString("name");
                        String description = productobject.getString("description");
                        Double price = productobject.getDouble("price");
                        String imageurl = productobject.getString("imageurl");

                        Product product = new Product(id, name, description, price, imageurl);
                        productslist.add(product);
                    }

                    adapter = new ProductAdapter(MainActivity.this, productslist);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
