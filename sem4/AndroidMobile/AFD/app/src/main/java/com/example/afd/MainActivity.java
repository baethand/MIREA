package com.example.afd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afd.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SuppliesAdapter adapter;
    private List<Supplies> suppliesList;

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView = binding.listView;
        suppliesList = new ArrayList<>();

        initArtSuppliesList();

        adapter = new SuppliesAdapter(this, suppliesList);
        listView.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater();
        View headerView = inflater.inflate(R.layout.header, listView, false);
        listView.addHeaderView(headerView);



        Button buttonShowCheckedItems = findViewById(R.id.buttonShowCheckedItems);
        buttonShowCheckedItems.setOnClickListener(v -> {
            List<Supplies> checkedSupplies = adapter.getCheckedSupplies();
            if (checkedSupplies.isEmpty()) {
                Toast.makeText(MainActivity.this, "No items selected", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, SuppliesCartActivity.class);
                intent.putExtra("checkedSupplies", (Serializable) checkedSupplies);
                startActivity(intent);
            }
        });



    }

    private void initArtSuppliesList() {
        suppliesList.add(new Supplies(1, "Кисточки", 10.0, R.drawable.brushes));
        suppliesList.add(new Supplies(2, "Расчёска", 20.0, R.drawable.hairbrush));
        suppliesList.add(new Supplies(3, "Пудра", 30.0, R.drawable.pudra));
        suppliesList.add(new Supplies(4, "Зубная щётка", 15.0, R.drawable.toothbrush));
        suppliesList.add(new Supplies(5, "Зеркальце", 8.0, R.drawable.mirror));
        suppliesList.add(new Supplies(6, "Фен", 12.0, R.drawable.dryer));
        suppliesList.add(new Supplies(7, "Туш", 6.0, R.drawable.tush));
        suppliesList.add(new Supplies(8, "Щипчики", 2.0, R.drawable.shipc));
        suppliesList.add(new Supplies(9, "Крем для лица", 5.0, R.drawable.cream));
        suppliesList.add(new Supplies(10, "Краски для макияжа", 7.0, R.drawable.colors));
    }



}