package com.example.dheep.walmart;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dheep.walmart.adapters.ProductsRecyclerAdapter;
import com.example.dheep.walmart.models.Product;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<Product> productList;
    RecyclerView rvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind views
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        rvProducts = (RecyclerView) findViewById(R.id.rv_products);

        MyFragementPagerAdapter myFragementPagerAdapter = new MyFragementPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(myFragementPagerAdapter);

        //fetch data from server
        FetchProductList fetchProductList = new FetchProductList();
        fetchProductList.execute(Product.WALMART_PRODUCTS_URL);

    }

    class FetchProductList extends AsyncTask<String, String, List<Product>> {

        @Override
        protected List<Product> doInBackground(String... params) {
            productList = new ArrayList<>();
            String url = params[0];

            //Fetches data from server
            String serverResponse = getDataFromURL(url);
            //Log.d("ServerResponse", "doInBackground: "+serverResponse);
            //Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

            //no data from server
            if (serverResponse == null || serverResponse.length() == 0) {
                //Toast.makeText(getApplicationContext(),"No data found!",Toast.LENGTH_SHORT).show();
            } else {//data found
                //Toast.makeText(getApplicationContext(),serverResponse,Toast.LENGTH_LONG).show();
                productList = Product.getProductListFromJsonArray(serverResponse);
            }
            return productList;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            productList = products;
            rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            ProductsRecyclerAdapter productsRecyclerAdapter=new ProductsRecyclerAdapter(getApplicationContext(),MainActivity.this,productList);

            rvProducts.setAdapter(productsRecyclerAdapter);
        }
    }

    public static String getDataFromURL(String url) {

        String resString = "";

        try {

            HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client

            if (!url.startsWith("http")) {

                url = "http://" + url;

            }

            HttpGet httpget = new HttpGet(url); // Set the action you want to do

            HttpResponse response = null; // Executeit


            response = httpclient.execute(httpget);


            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent(); // Create an InputStream with the response

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);

            StringBuilder sb = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) // Read line by line

                sb.append(line + "\n");


            resString = sb.toString(); // Result is here


            is.close(); // Close the stream


        } catch (IOException e) {

            e.printStackTrace();

            return null;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        return resString;

    }
}
