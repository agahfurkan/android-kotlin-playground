package com.agah.furkan.core.data

import com.agah.furkan.core.data.model.Error
import com.google.common.truth.Truth.assertThat
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ErrorMapperImplTest {
    private lateinit var errorMapper: ErrorMapper

    @Before
    fun setUp() {
        errorMapper = ErrorMapperImpl()
    }

    @Test
    fun `if exception is unauthorized, errormapper should return UnauthorizedError`() {
        val error =
            errorMapper.mapError(HttpException(Response.error<Any>(401, "".toResponseBody())))
        assertThat(error is Error.UnauthorizedError).isTrue()
    }

    @Test
    fun `if exception is not unauthorized, errormapper should return CommonErrror`() {
        val error =
            errorMapper.mapError(HttpException(Response.error<Any>(500, "".toResponseBody())))
        assertThat(error is Error.CommonError).isTrue()
    }

}