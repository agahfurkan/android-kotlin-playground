package com.agah.furkan.core.data

import com.agah.furkan.core.data.model.BaseResponse
import com.agah.furkan.core.data.model.Error
import com.agah.furkan.core.data.model.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RepositoryUtilKtTest {
    private lateinit var errorMapper: ErrorMapper

    @Before
    fun setup() {
        errorMapper = ErrorMapperImpl()
    }

    @Test
    fun `if baseResponse isSuccess is false, suspendCall should return ResultSuccess`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        BaseResponse().apply {
                            isSuccess = false
                        }
                    }
                }
            )
            assert(result is Result.Failure)
        }

    @Test
    fun `if baseResponse isSuccess is true, suspendCall should return ResultSuccess`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        BaseResponse().apply {
                            isSuccess = true
                        }
                    }
                }
            )
            assert(result is Result.Success)
        }

    @Test
    fun `if baseResponse is null, suspendCall should return ResultFailure`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        null
                    }
                }
            )
            assert(result is Result.Failure)
        }

    @Test
    fun `if baseResponse is null, suspendCall should return ResultFailure with NullResponseError`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        null
                    }
                }
            )
            assert((result as Result.Failure).error is Error.NullResponseError)
        }

    @Test
    fun `if baseResponse is null, suspendCall should return ResultFailure with NetworkError`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        BaseResponse().apply {
                            isSuccess = false
                            message = "some message"
                        }
                    }
                }
            )
            assert((result as Result.Failure).error is Error.NetworkError)
        }

    @Test
    fun `if exception thrown during service call, suspendCall should return Failure`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        throw NullPointerException()
                    }
                }
            )
            assert(result is Result.Failure)
        }

    @Test
    fun `if unauthorized response received from service call, suspendCall should return Failure with UnauthorizedError`() =
        runBlocking {
            val result = suspendCall(
                coroutineContext = Dispatchers.IO,
                errorMapper = errorMapper,
                mapOnSuccess = {},
                call = {
                    runBlocking {
                        throw HttpException(Response.error<Any>(401, "".toResponseBody()))
                    }
                }
            )
            assertThat((result as Result.Failure).error is Error.UnauthorizedError).isTrue()
        }
}