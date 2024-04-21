package com.example.laboratorio3;




import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IComentario {

    @GET("?apikey=bf81d461&i=tt3896198")
    Call<ArrayList<Persona>> getComentarios();

}