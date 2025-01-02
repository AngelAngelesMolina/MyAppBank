package com.example.mibankapp.data.di

import android.content.Context
import com.example.mibankapp.common.Constants
import com.example.mibankapp.data.local.SecurePreferences
import com.example.mibankapp.data.remote.ImageApi
import com.example.mibankapp.data.remote.LoginApi
import com.example.mibankapp.data.repository.ImageRepositoryImp
import com.example.mibankapp.data.repository.LoginRepositoryImp
import com.example.mibankapp.domain.repository.ImageRepository
import com.example.mibankapp.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): LoginApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(api: LoginApi): LoginRepository {
        return LoginRepositoryImp(api)
    }
    @Provides
    @Singleton
    fun provideRetrofitImage(): ImageApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_IMG)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: ImageApi): ImageRepository {
        return ImageRepositoryImp(api)
    }
    @Provides
    fun provideSecurePreferences(@ApplicationContext context: Context): SecurePreferences {
        return SecurePreferences(context)
    }


}