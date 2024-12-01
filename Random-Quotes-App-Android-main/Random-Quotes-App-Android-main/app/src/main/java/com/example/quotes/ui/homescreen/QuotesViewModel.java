package com.example.quotes.ui.homescreen;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quotes.data.RandomQuotes;
import com.example.quotes.data.retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesViewModel extends ViewModel {
    private MutableLiveData<RandomQuotes> randomQuotes = new MutableLiveData<>();
    public LiveData<RandomQuotes> _randomQuotes = randomQuotes;

    public void set_randomQuotes(){
        RetrofitClient.getInstance().getRandomQuotes().enqueue(new Callback<RandomQuotes>() {
            @Override
            public void onResponse(Call<RandomQuotes> call, Response<RandomQuotes> response) {
                Log.d("response", response.body().getAuthor());
                if (response.isSuccessful() && response.body() != null) {
                    randomQuotes.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RandomQuotes> call, Throwable t) {

            }
        });
    }
}
