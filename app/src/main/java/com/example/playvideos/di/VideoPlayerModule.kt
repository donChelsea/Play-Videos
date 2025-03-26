package com.example.playvideos.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.playvideos.domain.MetadataReader
import com.example.playvideos.domain.MetadataReaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class VideoPlayerModule {

    @Provides
    @ViewModelScoped
    fun provideVideoPlayer(app: Application): Player =
        ExoPlayer.Builder(app).build()

    @Provides
    @ViewModelScoped
    fun provideMetadataReader(app: Application): MetadataReader =
        MetadataReaderImpl(app)
}