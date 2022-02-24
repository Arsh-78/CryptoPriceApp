package com.example.cryptopricechecker

import okhttp3.Interceptor
import okhttp3.Response


class AuthenticationInterceptorInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            // TODO: Use your API Key provided by CoinMarketCap Professional API Developer Portal.
            .addHeader("X-CMC_PRO_API_KEY", "eaa534bb-592a-4e72-8e7b-0b738f5f9e7a")
            .build()

        return chain.proceed(newRequest)
    }
}