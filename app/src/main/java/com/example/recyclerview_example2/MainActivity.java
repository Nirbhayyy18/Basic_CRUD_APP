package com.example.recyclerview_example2;

import android.app.Dialog;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<infomodel> data = new ArrayList<>();


    RecyclerView rc;
    FloatingActionButton f1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rc = findViewById(R.id.recyclerView);
        rc.setLayoutManager(new LinearLayoutManager(this));
        f1 = findViewById(R.id.floatingActionButton);

        data.add(new infomodel("Nirbhay", "9678483987"));
        data.add(new infomodel("Abhay", "8766886689"));


        personAdapter adapter = new personAdapter(data);
        rc.setAdapter(adapter);

        f1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextInputEditText t1,t2;
                Dialog d1 = new Dialog(MainActivity.this);
                d1.setContentView(R.layout.add_data);
                t1 = d1.findViewById(R.id.name_input);
                t2 = d1.findViewById(R.id.phone_input);
                Button save = d1.findViewById(R.id.save_btn);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = t1.getText().toString();
                        String phone = t2.getText().toString();
                        data.add(new infomodel(name, phone));
                        adapter.notifyItemInserted(data.size()-1);
                        rc.scrollToPosition(data.size() - 1);
                        d1.dismiss();
                    }
                });

                d1.getWindow().setLayout(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT  );

                d1.show();
            }

        });
    }
   }