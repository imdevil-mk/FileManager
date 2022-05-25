package com.imdevil.filemanager.di

import android.app.Application
import com.imdevil.filemanager.MediaStoreQuery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMediaStoreQuery(application: Application) = MediaStoreQuery(application)
}