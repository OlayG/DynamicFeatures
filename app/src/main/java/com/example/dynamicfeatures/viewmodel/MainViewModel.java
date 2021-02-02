package com.example.dynamicfeatures.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dynamicfeatures.repo.ShibeRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> _isSuccessful = new MutableLiveData<>();

    public LiveData<Boolean> isSuccessful() {
        return _isSuccessful;
    }

    public void fetchShibes(int count) {
        ShibeRepository.getInstance().getShibes(count).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(
                    @NotNull Call<List<String>> call,
                    @NotNull Response<List<String>> response
            ) {
                _isSuccessful.setValue(response.body().size() == count);
            }

            @Override
            public void onFailure(@NotNull Call<List<String>> call, @NotNull Throwable t) {
                _isSuccessful.setValue(false);
            }
        });
    }
}
