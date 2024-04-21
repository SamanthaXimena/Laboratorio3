package com.example.laboratorio3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PrimeNumberApiOnrender {

    @GET("primeNumbers?len=999&order=1")
    Call<List<Posts>> getPosts();

}
