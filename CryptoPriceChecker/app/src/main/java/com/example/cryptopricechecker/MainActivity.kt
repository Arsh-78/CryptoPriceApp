package com.example.cryptopricechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cryptopricechecker.RestClient.client
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRetrofitTemporarily()
    }

    private fun setupRetrofitTemporarily() {

        val builder = OkHttpClient.Builder()
        // We add the interceptor to OkHttpClient.
        // It will add authentication headers to every call we make.
        builder.interceptors().add(AuthenticationInterceptorInterceptor())
        val client = builder.build()


        /*val api = Retrofit.Builder() // Create retrofit builder
            .baseUrl("https://pro-api.coinmarketcap.com/") // Base url for the api has to end with a slash.
            .addConverterFactory(GsonConverterFactory.create()) // Use GSON converter for JSON to POJO object mapping.
            .client(client) // Here we set the custom OkHttp client we just created.
            .build().create(ApiService::class.java)*/
        var mApiService: ApiService = RestClient.client.create(ApiService::class.java);


        val currentFiatCurrencyCode = "INR"
        val call = mApiService.getAllCryptocurrencies("INR")
        val result = call.enqueue(object : Callback<CryptocurrenciesLatest> {
            override fun onResponse(
                call: Call<CryptocurrenciesLatest>,
                response: Response<CryptocurrenciesLatest>
            ) {
                if (response.isSuccessful) {

                    // If everything is OK, let the user know that.
                    Toast.makeText(this@MainActivity, "Call OK.", Toast.LENGTH_LONG).show();}
                else
                    Snackbar.make(findViewById(android.R.id.content),
                        "Call error with HTTP status code " + response.code() + "!",
                        Snackbar.LENGTH_INDEFINITE).show()
            }

            override fun onFailure(call: Call<CryptocurrenciesLatest>, t: Throwable) {
                Snackbar.make(findViewById(android.R.id.content),
                    // Throwable will let us find the error if the call failed.
                    "Call failed! " + t.localizedMessage,
                    Snackbar.LENGTH_INDEFINITE).show()
            }

        })
    }
}