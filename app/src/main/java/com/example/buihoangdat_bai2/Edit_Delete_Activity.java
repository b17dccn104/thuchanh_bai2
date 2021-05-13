package com.example.buihoangdat_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Edit_Delete_Activity extends AppCompatActivity {
    private Button btEdit, btDelete, btBack;
    private EditText txtId, txtName, txtDate ,txtAmount, txtPrice;
    private SQLiteOrderOpenHelper db;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__delete_);
        initView();

        db = new SQLiteOrderOpenHelper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Order order = db.getById(id);
        txtId.setText(String.valueOf(id));
        txtName.setText(order.getName());
        txtDate.setText(order.getDate());
        txtAmount.setText(String.valueOf(order.getAmount()));
        txtPrice.setText(String.valueOf(order.getPrice()));



        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = txtName.getText().toString();
                    String date = txtDate.getText().toString();

                    int amount = Integer.parseInt(txtAmount.getText().toString());
                    double price = Double.parseDouble(txtPrice.getText().toString());
                    Order order = new Order(id, name,date, amount, price);
                    db.updateOrder(order);

                    finish();
                } catch (Exception e) {

                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteOrder(id);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        btEdit=findViewById(R.id.btnUp);
        btDelete=findViewById(R.id.btDelete);
        btBack=findViewById(R.id.upbtBack);
        txtId=findViewById(R.id.upID);
        txtName=findViewById(R.id.upName);
        txtDate = findViewById(R.id.upDate);
        txtAmount=findViewById(R.id.upAmount);
        txtPrice=findViewById(R.id.upPrice);
    }
}