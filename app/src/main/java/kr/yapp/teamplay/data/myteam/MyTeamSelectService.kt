package kr.yapp.teamplay.data.myteam

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface MyTeamSelectService {

    @GET("/v1/users/user/clubs")
    fun getMyTeams(@Header("accessToken") accessToken: String) : Single<MyTeamResponse>

}
