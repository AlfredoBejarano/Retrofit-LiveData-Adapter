package me.alfredobejarano.livedataconverterdemo.utilities

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.alfredobejarano.retrofitadapters.LiveDataAdapter
import me.alfredobejarano.livedataconverterdemo.BuildConfig
import me.alfredobejarano.livedataconverterdemo.data.ToDoApiService
import me.alfredobejarano.livedataconverterdemo.data.ToDoRepository
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Object that allows simple dependency injection for this demo project.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 21:03
 * @version 1.0
 **/
object Injector {
    /**
     * Provides injection for a [Gson] object.
     */
    private val gson: Gson by lazy {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    /**
     * Provides injection for a [HttpLoggingInterceptor] object.
     */
    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides injection for a [OkHttpClient] object.
     */
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    /**
     * Provides injection for an implementation of the [ToDoApiService] interface.
     */
    private val toDoApiService: ToDoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataAdapter.Factory())
            .build()
            .create(ToDoApiService::class.java)
    }

    /**
     * Provides injection for the [ToDoRepository] class.
     */
    private val toDoRepository: ToDoRepository by lazy {
        ToDoRepository(toDoApiService)
    }

    /**
     * Provides injection for a [ToDoListViewModel.Factory] class.
     */
    val toDoListViewModelFactory by lazy {
        ToDoListViewModel.Factory(toDoRepository)
    }
}