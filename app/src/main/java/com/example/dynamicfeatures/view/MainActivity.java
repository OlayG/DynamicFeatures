package com.example.dynamicfeatures.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dynamicfeatures.R;
import com.example.dynamicfeatures.databinding.ActivityMainBinding;
import com.example.dynamicfeatures.viewmodel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private MaterialTextView tvDisplay;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //initViews(); // using findViewById
        setupViews();
        initObservers();
    }

    private void initObservers() {
        viewModel.getUrls().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                //tvDisplay.setText(urls.toString()); // using findViewById
                binding.tvDisplay.setText(urls.toString());
            }
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            //tvDisplay.setText(errorMessage); // using findViewById
            binding.tvDisplay.setText(errorMessage);
        });
    }

    private void initViews() {
        findViewById(R.id.btn_fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countString = ((AppCompatEditText) findViewById(R.id.et_count)).getText().toString();
                if (!countString.isEmpty())
                    viewModel.fetchShibes(Integer.parseInt(countString));
            }
        });
        tvDisplay = findViewById(R.id.tv_display);
    }

    private void setupViews() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countString = ((AppCompatEditText) findViewById(R.id.et_count)).getText().toString();
                if (!countString.isEmpty())
                    viewModel.fetchShibes(Integer.parseInt(countString));
            }
        });
    }
}

/**
 * http://shibe.online/api/shibes?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]
 * <p>
 * BASE: http://shibe.online
 * PATH: /api/shibes
 * QUERY: ?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]
 **/