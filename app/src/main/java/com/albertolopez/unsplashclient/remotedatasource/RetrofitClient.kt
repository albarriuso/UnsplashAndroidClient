package com.albertolopez.unsplashclient.remotedatasource

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This class provides a retrofit client for Unsplash api.
 */
@Module
class RetrofitClient {

    @Singleton
    @Provides
    fun providePhotosWebService(): WebService {
        val gson = GsonBuilder().disableHtmlEscaping().setDateFormat(DATE_UTC).create()

        val client = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor(UNSPLASH_ACCES_KEY)).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(WebService::class.java)
    }

    class HeaderInterceptor(private val clientId: String) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "Client-ID $clientId")
                .build()
            return chain.proceed(request)
        }
    }

    companion object {
        const val DATE_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        const val BASE_URL = "https://api.unsplash.com/"
        const val UNSPLASH_ACCES_KEY = ""
        const val TIMEOUT_IN_SECONDS = 30L
    }
}