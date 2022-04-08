package com.play.catchtinifing.domain.error

sealed class Errors(message: String?, throwable: Throwable?) : Exception(message) {
    class InvalidCatchTinifingId(message: String?, throwable: Throwable? = null): Errors(message, throwable)
}
