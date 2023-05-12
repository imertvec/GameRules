package ru.vagavagus.gamerules.di

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.vagavagus.gamerules.common.Constants
import ru.vagavagus.gamerules.data.remote.Api
import ru.vagavagus.gamerules.data.remote.repository.SportRepositoryImpl
import ru.vagavagus.gamerules.domain.repository.SportRepository
import ru.vagavagus.gamerules.domain.use_case.sport_details.FetchSportDetails
import ru.vagavagus.gamerules.domain.use_case.sport_list.ChangeLanguage
import ru.vagavagus.gamerules.domain.use_case.sport_list.FetchCurrentLanguage
import ru.vagavagus.gamerules.domain.use_case.sport_list.FetchSportList
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideSportRepository(api: Api): SportRepository {
        return SportRepositoryImpl(api)
    }


    //MARK: use-cases
    @Provides
    @Singleton
    fun provideFetchSportList(repository: SportRepository): FetchSportList {
        return FetchSportList(repository)
    }

    @Provides
    @Singleton
    fun provideFetchSportDetails(repository: SportRepository): FetchSportDetails {
        return FetchSportDetails(repository)
    }

    @Provides
    @Singleton
    fun provideChangeLanguage(sharedPreferences: SharedPreferences): ChangeLanguage {
        return ChangeLanguage(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFetchCurrentLanguage(sharedPreferences: SharedPreferences): FetchCurrentLanguage {
        return FetchCurrentLanguage(sharedPreferences)
    }
}