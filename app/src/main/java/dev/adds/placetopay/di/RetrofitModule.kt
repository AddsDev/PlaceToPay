package dev.adds.placetopay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adds.placetopay.provider.services.products.IProductService
import dev.adds.placetopay.provider.services.wallets.IGatewayService
import dev.adds.placetopay.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun providePlaceToPay(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.PLACE_TO_PAY_API)
            .addConverterFactory(GsonConverterFactory.create())
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