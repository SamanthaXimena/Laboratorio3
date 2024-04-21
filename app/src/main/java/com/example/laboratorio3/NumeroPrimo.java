package com.example.laboratorio3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NumeroPrimo extends AppCompatActivity {

    private TextView mJsonTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numero_primo);

        mJsonTextView = findViewById(R.id.jasonText);
        getPosts();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /******************************* Internet *****************************/

        Toast.makeText(this, "Tiene internet: " + tengoInternet(), Toast.LENGTH_LONG).show();

}

//VISUALIZAR NUMEROS PRIMOS

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PrimeNumberApiOnrender primeNumberApiOnrender = retrofit.create(PrimeNumberApiOnrender.class);
        Call<List<Posts>> call = primeNumberApiOnrender.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){

                    mJsonTextView.setText("Codigo: " + response.code());
                    return;
                }
                List<Posts> postsList = response.body();

                for(Posts post: postsList){
                    String content = "";
                    content+= "Su primo es: " + post.getNumber() + "\n";

                    mJsonTextView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mJsonTextView.setText(t.getMessage());
            }
        });
    }

    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test-internet", "Internet: " + tieneInternet);

        return tieneInternet;
    }
}