package kr.yapp.teamplay.rxjava_study.network

import io.reactivex.Observable
import kr.yapp.teamplay.rxjava_study.model.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

interface ExampleService {
    @GET("/posts")
    fun getData() : Observable<List<Result>>
}

object ServiceApi {
    val retrofitService : ExampleService by lazy { retrofit.create(
        ExampleService::class.java) }
}