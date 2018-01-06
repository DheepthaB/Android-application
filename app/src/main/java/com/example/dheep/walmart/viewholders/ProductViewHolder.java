package com.example.dheep.walmart.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dheep.walmart.R;
import com.example.dheep.walmart.models.Product;
import com.squareup.picasso.Picasso;

/**
 * Created by dheep on 12/21/2017.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ImageView prodImg;
    TextView prodPrice,prodName,prodDesc;
    public ProductViewHolder(View itemView) {
        super(itemView);
        prodImg = (ImageView) itemView.findViewById(R.id.prod_img);
        prodPrice = (TextView) itemView.findViewById(R.id.prod_price);
        prodName = (TextView) itemView.findViewById(R.id.prod_name);
        prodDesc = (TextView) itemView.findViewById(R.id.prod_desc);
    }

    public void bindViews(Context c, Product p){
        prodName.setText(p.getName());
        prodPrice.setText("$"+p.getPrice());
        prodDesc.setText(p.getDescription());
        Picasso.with(c).load(p.getImageURL()).into(prodImg);
    }
}
