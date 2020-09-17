package com.kush.letsgetchecked.retrofit.serviceBuilders

import android.content.Context
import com.kush.letsgetchecked.api.CountryAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Kush on 17/09/2020.
 */
public class CountriesServiceBuilders {

    private var mContext: Context?                          = null
    private var retrofit: Retrofit?                 = null
    private var retrofitBuilder: Retrofit.Builder?  = null

    private var URL_FOR_ALL_COUNTRIES: String?      = null

    constructor(mContext: Context?) {
        this.mContext                        = mContext
        val gettingOkHttpClient = getOkHttp()
        URL_FOR_ALL_COUNTRIES                = CountryAPI.MAIN_URL
        retrofitBuilder                      = Retrofit.Builder()
                                                .baseUrl(URL_FOR_ALL_COUNTRIES)
                                                .client(gettingOkHttpClient)
                                                .addConverterFactory(GsonConverterFactory.create())
    }

    fun <S> buildService(serviceType: Class<S>?): S {
        retrofit = retrofitBuilder!!.build()
        return retrofit!!.create(serviceType)
    }

    private fun getOkHttp(): OkHttpClient {
        val logger: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return okHttpClient
    }
}