package com.example.cryptopricechecker


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET( "v1/cryptocurrency/listings/latest")
    // Annotation @Query is used to define query parameter for request. Finally the request url will
    // look like that https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?convert=EUR.
    fun getAllCryptocurrencies(@Query("convert") currency: String): Call<CryptocurrenciesLatest>
    // The return type for this function is Call with its type CryptocurrenciesLatest.
}