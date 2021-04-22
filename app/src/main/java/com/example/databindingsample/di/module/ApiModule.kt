package com.example.databindingsample.di.module

import com.example.databindingsample.data.randomcharacter.repository.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit) : CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }
}