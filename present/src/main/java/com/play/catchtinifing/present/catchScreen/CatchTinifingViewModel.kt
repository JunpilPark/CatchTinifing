package com.play.catchtinifing.present.catchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.play.catchtinifing.domain.repository.TinifingRepository
import com.play.catchtinifing.present.err.Errors
import com.play.catchtinifing.present.state.TinifingSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchTinifingViewModel @Inject constructor(
    private val repository: TinifingRepository
) : ViewModel() {
    private val tinifingIdFlow = MutableSharedFlow<String?>()
    private val loadingFlow = MutableSharedFlow<TinifingSearchState.Loading>()

    @ExperimentalCoroutinesApi
    private val _findingTinifingFlow =
        tinifingIdFlow.flatMapLatest {
            if(it == null) {
                flowOf(null)
            } else {
                if(it.isEmpty()) {
                    flowOf(TinifingSearchState.Failure(Errors.EmptyTextFieldDataError))
                } else {
                    repository.findTinifing(it.toInt())
                        .map { tinifing -> TinifingSearchState.Success(tinifing) }
                }
                .catch { e ->
                    this.emit(TinifingSearchState.Failure(Errors.InvalidTinifingId))
                }
            }
        }

    @FlowPreview
    @ExperimentalCoroutinesApi
    val findingTinifingFlow = flowOf(loadingFlow, _findingTinifingFlow)
        .flattenMerge()

    fun catch(tinifingId: String) {
        viewModelScope.launch {
            loadingFlow.emit(TinifingSearchState.Loading)
            tinifingIdFlow.emit(tinifingId)
        }
    }

    fun consumeSearchResult() {
        viewModelScope.launch {
            tinifingIdFlow.emit(null)
        }
    }
}