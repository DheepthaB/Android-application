package com.example.dheep.walmart.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dheep.walmart.MainActivity;
import com.example.dheep.walmart.R;
import com.example.dheep.walmart.models.Product;
import com.example.dheep.walmart.viewholders.ProductViewHolder;

import java.util.List;

/**
 * Created by dheep on 12/21/2017.
 */

public class ProductsRecyclerAdapter extends RecyclerView.Adapter {

    Context mContext;
    MainActivity mainActivity;
    List<Product> productList;

    public ProductsRecyclerAdapter(Context c, MainActivity mainActivity, List<Product> productList) {
        mContext=c;
        this.mainActivity=mainActivity;
        this.productList=productList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowLayout= LayoutInflater.from(mContext).inflate(R.layout.rv_list_item_row,parent,false);
        ProductViewHolder myViewHolder=new ProductViewHolder(rowLayout);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder myViewHolder=(ProductViewHolder) holder;
        Product prod=productList.get(position);
        myViewHolder.bindViews(mContext,prod);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
