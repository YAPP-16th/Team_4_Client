package kr.yapp.teamplay.data.teamcreate

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TeamCreateService {
    @POST("/clubs")
    fun createTeam(@Header("accessToken") accessToken: String, @Body createClubRequest: CreateClubRequest) : Single<Void>
}
