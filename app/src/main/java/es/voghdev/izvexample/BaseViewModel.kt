package es.voghdev.izvexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    open class State

    val state: LiveData<State>
        get() = viewState

    protected val viewState = MutableLiveData<State>()
}
