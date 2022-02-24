package com.example.cryptopricechecker



    import okhttp3.OkHttpClient
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory

    object RestClient {

        private val BASE_URL = "https://pro-api.coinmarketcap.com"
        private var mRetrofit: Retrofit? = null

        val builder = OkHttpClient.Builder()



        val client: Retrofit
            get() {
                if (mRetrofit == null) {
                    builder.interceptors().add(AuthenticationInterceptorInterceptor())
                    val c = builder.build()
                    mRetrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(c)
                        .build()
                }
                return this.mRetrofit!!
            }
    }
