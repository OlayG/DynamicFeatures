package com.example.dynamicfeatures.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dynamicfeatures.R;
import com.example.dynamicfeatures.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.isSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                Toast.makeText(MainActivity.this, "Call: " + isSuccessful, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(((AppCompatEditText) findViewById(R.id.et_count)).getText().toString());
                viewModel.fetchShibes(count);
            }
        });
    }
}

/**
 http://shibe.online/api/shibes?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]

 BASE: http://shibe.online
 PATH: /api/shibes
 QUERY: ?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]


 **/