package com.example.lukey.booklibrary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lukey.booklibrary.Common.Common;
import com.example.lukey.booklibrary.Database.Database;
import com.example.lukey.booklibrary.Model.Order;
import com.example.lukey.booklibrary.Model.Product;
import com.example.lukey.booklibrary.Model.Request;
import com.example.lukey.booklibrary.ViewHolder.OrderDetailAdapter;
import com.example.lukey.booklibrary.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;


import static android.R.attr.order;

public class OrderDetail extends AppCompatActivity {

    TextView order_id,order_userName,order_total,order_expectedDelivery,order_comment;
    Button update;
    String order_id_value="";
    RecyclerView lstProducts;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);


        order_id = (TextView) findViewById(R.id.order_id);
        order_userName = (TextView) findViewById(R.id.order_userName);
        order_total = (TextView) findViewById(R.id.order_total);
        order_expectedDelivery = (TextView) findViewById(R.id.order_expectedDelivery);
        order_comment = (TextView) findViewById(R.id.order_comment);

        lstProducts = (RecyclerView) findViewById(R.id.lstProducts);
        lstProducts.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstProducts.setLayoutManager(layoutManager);

        if (getIntent() != null)
            order_id_value = getIntent().getStringExtra("OrderId");

        order_id.setText(order_id_value);
        order_userName.setText(Common.currentUser.getUserName());
        order_total.setText(Common.currentRequest.getTotal());
        order_expectedDelivery.setText(Common.currentRequest.getRequestedDeliveryDate());
        order_comment.setText(Common.currentRequest.getComment());


        final OrderDetailAdapter adapter = new OrderDetailAdapter(Common.currentRequest.getProducts());
        adapter.notifyDataSetChanged();
        lstProducts.setAdapter(adapter);

    }

}
