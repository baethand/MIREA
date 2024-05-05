package com.example.afd;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SuppliesCartActivity extends AppCompatActivity {

    private ListView listView;
    private SuppliesAdapter adapter;
    private List<Supplies> checkedSupplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afdsupplies_cart);

        listView = findViewById(R.id.listView);

        checkedSupplies = (List<Supplies>) getIntent().getSerializableExtra("checkedSupplies");

        adapter = new SuppliesAdapter(this, checkedSupplies);

        listView.setAdapter(adapter);

        double totalPrice = adapter.calculateTotalPrice(checkedSupplies);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        textViewTotalPrice.setText("Total price: $" + totalPrice);
    }


}
