package dev.adds.placetopay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adds.placetopay.provider.services.products.IProductService
import dev.adds.placetopay.provider.services.wallets.IGatewayService
import dev.adds.placetopay.util.Constants
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun providePlaceToPay(): Retrofit{
        val client = OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECT_TIME_OUT_API, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIME_OUT_API, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, Constants.POOL_TIME_OUT_API, TimeUnit.MINUTES))
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.PLACE_TO_PAY_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductService(): IProductService{
        return Retrofit.Builder()
            .baseUrl(Constants.PRODUCT_MOCK_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IProductService::class.java)

    }

    /***
     * TODO(Se debe de cambiar por un interceptor y OkHttpClient)
     * Se deja por el momento asi xd
     */
    @Singleton
    @Provides
    fun provideGatewayService(retrofit: Retrofit): IGatewayService{
        return retrofit.create(IGatewayService::class.java)
    }
}