package com.example.dynamicfeatures.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dynamicfeatures.R;
import com.example.dynamicfeatures.adapter.ShibeAdapter;
import com.example.dynamicfeatures.databinding.ActivityShibeBinding;
import com.example.dynamicfeatures.utils.Constants;
import com.example.dynamicfeatures.viewmodel.MainViewModel;

import java.util.List;

public class ShibeActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityShibeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShibeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        // Get data from intent
        String stringData = getIntent().getStringExtra(Constants.SHIBE_ACTIVITY_PARAM_STRING);
        int intData = getIntent().getIntExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, 0);

        setupViews();
        initObservers();

        Toast.makeText(this, stringData, Toast.LENGTH_SHORT).show();
        viewModel.fetchShibes(intData);
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