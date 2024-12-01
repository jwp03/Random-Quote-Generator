package com.example.quotes.data.apiservice;

import com.example.quotes.data.RandomQuotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("quotes/random")
    Call<RandomQuotes> getRandomQuotes();
}
