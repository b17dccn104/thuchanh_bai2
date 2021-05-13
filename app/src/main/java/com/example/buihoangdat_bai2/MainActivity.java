package com.example.buihoangdat_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button btAdd;
    private EditText stName, stDate, stPrice, stAmount;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteOrderOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new SQLiteOrderOpenHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = stName.getText().toString();
                String date = stDate.getText().toString();
                int amount = Integer.parseInt(stAmount.getText().toString());
                double price = Double.parseDouble(stPrice.getText().toString());
                Order order = new Order(name, date, amount, price);
                db.addOrder(order);
                List<Order> orders = db.getAll();
                adapter.setOrder(orders);
                recyclerView.setAdapter(adapter);

            }
        });





    }


    @Override
    protected void onRestart() {
        List<Order> orders = db.getAll();
        adapter.setOrder(orders);
        recyclerView.setAdapter(adapter);
        super.onRestart();
    }


    private void initView() {
    btAdd = findViewById(R.id.btAdd);
    recyclerView = findViewById(R.id.recyclerView);
    stName = findViewById(R.id.name);
    stAmount = findViewById(R.id.amount);
    stDate = findViewById(R.id.date);
    stPrice = findViewById(R.id.price);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.mSearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Order> list=db.getByName(newText);
                adapter.setOrder(list);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}