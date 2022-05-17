package dev.adds.placetopay.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.adds.placetopay.model.database.ShopDatabase
import dev.adds.placetopay.util.Constants
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShopDatabase::class.java, Constants.SHOP_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTransactionDao(shopDatabase: ShopDatabase) = shopDatabase.getTransactionDao()

    @Singleton
    @Provides
    fun provideCartDao(shopDatabase: ShopDatabase) = shopDatabase.getCartDao()
}