package kr.yapp.teamplay.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> get() = _showError

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun Disposable.addDisposable() = addTo(compositeDisposable)

    protected fun showError(message: String) {
        _showError.value = message
    }

}
