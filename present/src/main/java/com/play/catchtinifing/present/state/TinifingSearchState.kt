package com.play.catchtinifing.present.state

import com.play.catchtinifing.domain.tinifing.model.Tinifing
import com.play.catchtinifing.present.err.Errors

sealed class TinifingSearchState {
    object Loading : TinifingSearchState()
    data class Success(val tinifing: Tinifing) : TinifingSearchState()
    class Failure(val error: Errors) : TinifingSearchState()
}
