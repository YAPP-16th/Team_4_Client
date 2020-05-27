package kr.yapp.teamplay.data.teammain

import io.reactivex.Single
import kr.yapp.teamplay.data.teammain.response.GetClubResponse
import kr.yapp.teamplay.data.teammain.response.TeamMainItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamService {

    @GET("/clubs/{clubId}")
    fun getTeamMainInfo(@Path("clubId") clubId: String): Single<TeamMainItemResponse>

    @GET("/clubs")
    fun getAllClub(
        @Query("currentPage") currentPage: Int = 0
    ): Single<GetClubResponse>

}
