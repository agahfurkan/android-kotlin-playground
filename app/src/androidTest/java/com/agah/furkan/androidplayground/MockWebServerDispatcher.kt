package com.agah.furkan.androidplayground

import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatcher {
    internal inner class RequestDispatcher : Dispatcher() {
        val context = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication)
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/api/Login/Login" ->
                    MockResponse().setResponseCode(200).setBody("{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0NCIsImp0aSI6IjdlODg2Y2QzLTVhZGItNDUxOC1iMGM5LTM0YmNkMzY3MTcyMCIsImlhdCI6IjUuMTAuMjAyMyAxODo1NTo0NSIsInVzZXJuYW1lIjoidGVzdCIsInBhc3N3b3JkIjoidGVzdCIsImV4cCI6MTY5NjYxODU0NSwiaXNzIjoidGVzdDIiLCJhdWQiOiJ0ZXN0MyJ9.1bBRZsF6-vhAA9dQllHsAJ5inBGCVYY3OaLn2ciUUxo\",\"userId\":6,\"isSuccess\":true,\"message\":null}")

                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
                .setBody("")
        }
    }
}
