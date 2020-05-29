package ru.somarov.marathon.backend.main.core.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.somarov.marathon.backend.main.core.api.response.BaseResponse
import ru.somarov.marathon.backend.main.core.api.entity.Marathon
import ru.somarov.marathon.backend.main.core.api.entity.Runner
import ru.somarov.marathon.backend.main.core.api.entity.Sponsor

interface RemoteService {
    @GET("runners/")
    suspend fun getRunners(@Query("page") page: Int? = null,
                          @Query("page_size") pageSize: Int? = null,
                          @Query("ordering") order: String? = null): Call<BaseResponse<Runner>>

    @GET("marathons/")
    suspend fun getMarathons(@Query("page") page: Int? = null,
                        @Query("page_size") pageSize: Int? = null,
                        @Query("runner_id") runnerId: Int? = null,
                        @Query("ordering") order: String? = null): Call<BaseResponse<Marathon>>

    @GET("sponsors/")
    suspend fun getSponsors(/*@Path("id") id: String*/): Call<BaseResponse<Sponsor>>
}