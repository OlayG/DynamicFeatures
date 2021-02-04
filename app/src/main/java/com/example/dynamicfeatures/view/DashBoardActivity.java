package com.example.dynamicfeatures.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dynamicfeatures.databinding.ActivityDashBoardBinding;
import com.example.dynamicfeatures.utils.Constants;


public class DashBoardActivity extends AppCompatActivity {

    private ActivityDashBoardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShibeActivity();
            }
        });
    }

    private void goToShibeActivity() {
        String strData = null;
        int intData = 0;
        if (binding.etStringInput.getText() != null)
            strData = binding.etStringInput.getText().toString();
        if (binding.etIntInput.getText() != null) {
            String intDataString = binding.etIntInput.getText().toString();
            intData = Integer.parseInt(intDataString);
        }

        Intent intent = new Intent(DashBoardActivity.this, ShibeActivity.class);

        if (strData != null && !strData.isEmpty())
            intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_STRING, strData);

        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, intData);

        startActivity(intent);

    }
}
