package com.example.restservice_test2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {



    private Context mtx;
    private List<Product> productList;

    public ProductAdapter(Context mtx, List<Product> productList) {
        this.mtx = mtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.list_item, null);
       // ProductViewHolder holder = new ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewname.setText(product.getName());
        holder.textViewdescription.setText(product.getDescription());
        holder.textViewprice.setText(String.valueOf(product.getPrice()));

        Glide.with(mtx).load(product.getImgurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewname,textViewdescription, textViewprice;
        public ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            itemView = itemView.findViewById(R.id.imageView);
            textViewname = itemView.findViewById(R.id.name);
            textViewdescription = itemView.findViewById(R.id.description);
            textViewprice = itemView.findViewById(R.id.price);
        }
    }

}
