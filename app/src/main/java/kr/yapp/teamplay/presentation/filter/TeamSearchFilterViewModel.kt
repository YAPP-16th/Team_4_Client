/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.presentation.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.club.ClubRepositoryImpl
import kr.yapp.teamplay.domain.entity.TeamCharacter
import kr.yapp.teamplay.domain.repository.ClubRepository
import kr.yapp.teamplay.presentation.BaseViewModel

class TeamSearchFilterViewModel(
    private val clubRepository: ClubRepository =
        ClubRepositoryImpl()
) : BaseViewModel() {

    private val _characters = MutableLiveData<List<TeamCharacter>>()
    val characters: LiveData<List<TeamCharacter>>
        get() = _characters

    fun getTeamCharacters() {
        clubRepository.getTeamCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<TeamCharacter>> {
                override fun onSuccess(list: List<TeamCharacter>) {
                    _characters.value = list
                }

                override fun onSubscribe(d: Disposable) {
                    /* explicitly empty */
                }

                override fun onError(e: Throwable) {
                    showError(e.message.toString())
                }

            })
    }
}
