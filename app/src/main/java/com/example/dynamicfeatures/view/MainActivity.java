package com.example.dynamicfeatures.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dynamicfeatures.R;
import com.example.dynamicfeatures.adapter.ShibeAdapter;
import com.example.dynamicfeatures.databinding.ActivityMainBinding;
import com.example.dynamicfeatures.viewmodel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupViews();
        initObservers();
    }

    private void initObservers() {
        viewModel.getUrls().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                ShibeAdapter shibeAdapter = new ShibeAdapter(urls);
                binding.rvImageList.setAdapter(shibeAdapter);
            }
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {

        });
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvImageList.setLayoutManager(linearLayoutManager);
    }
}

/**
 * http://shibe.online/api/shibes?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]
 * <p>
 * BASE: http://shibe.online
 * PATH: /api/shibes
 * QUERY: ?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]
 **/