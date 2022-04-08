package com.play.catchtinifing.present.err

sealed class Errors {
    object EmptyTextFieldDataError: Errors()
    object InvalidTinifingId: Errors()
}


