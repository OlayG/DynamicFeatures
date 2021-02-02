package com.example.dynamicfeatures.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Create Singleton Retrofit Instance and instance of our service
 */
public class RetrofitInstance {
    private static final String BASE_URL = "http://shibe.online";

    // Step 1: Declare instance initialized as null
    private static ShibeService INSTANCE = null;

    // Step 2: Make the constructor private
    private RetrofitInstance() {

    }

    // Step 3: Public method to access the new instance
    public static ShibeService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient())
                    .build()
                    .create(ShibeService.class);

        return INSTANCE;
    }


    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}
