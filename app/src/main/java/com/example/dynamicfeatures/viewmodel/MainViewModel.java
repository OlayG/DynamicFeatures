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

    private final MutableLiveData<List<String>> _urls = new MutableLiveData<>();
    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    private final ShibeRepository repo = ShibeRepository.getInstance();

    public LiveData<List<String>> getUrls() {
        return _urls;
    }

    public LiveData<String> getErrorMessage() {
        return _errorMessage;
    }

    public void fetchShibes(int count) {
        repo.getShibes(count).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NotNull Call<List<String>> call, @NotNull Response<List<String>> response) {
                List<String> urls = response.body();
                _urls.setValue(urls);
            }

            @Override
            public void onFailure(@NotNull Call<List<String>> call, @NotNull Throwable t) {
                _errorMessage.setValue(t.getMessage());
            }
        });
    }
}
