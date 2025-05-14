package com.example.dipantau.data.remote

import com.example.dipantau.utils.Constants
import com.example.dipantau.utils.SessionManager
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Konfigurasi Retrofit untuk API
 */
object RetrofitConfig {

    /**
     * Membuat interceptor autentikasi yang menambahkan token pada setiap request
     */
    private fun getAuthInterceptor(sessionManager: SessionManager): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val token = sessionManager.getToken()

            val requestBuilder = originalRequest.newBuilder()

            // Tambahkan token jika tersedia
            if (!token.isNullOrEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }

            // Tambahkan header content type dan accept
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("Accept", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    /**
     * Fungsi untuk mendapatkan instance ApiService
     */
    fun getApiService(sessionManager: SessionManager): ApiService {
        // Logging interceptor untuk debug
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (Constants.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        // Setup OkHttpClient dengan interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor(sessionManager))
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        // Gson builder dengan format tanggal yang sesuai
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()

        // Setup Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        // Return implementasi ApiService
        return retrofit.create(ApiService::class.java)
    }
}