package com.agah.furkan.data


class ErrorMapperImpl : ErrorMapper {
    override fun mapError(throwable: Throwable?): com.agah.furkan.data.model.Error {
        return com.agah.furkan.data.model.Error.CommonError(throwable?.message ?: "")
        /*return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    500 -> {

                    }
                    404 -> {

                    }
                }
            }
            is IOException -> {

            }
        }*/
    }
}
