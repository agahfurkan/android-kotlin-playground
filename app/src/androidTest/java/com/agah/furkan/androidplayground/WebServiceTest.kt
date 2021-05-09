package com.agah.furkan.androidplayground

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agah.furkan.androidplayground.data.repository.CategoryRepository
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class WebServiceTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var application: Application

    @Before
    fun init() {
        hiltRule.inject()
        SharedPrefUtil.init(application = application)
    }

    @Test
    fun testLoginEndpoint_withValidCredentials() {
        runBlocking {
            val response =
                userRepository.loginUser(UserLoginBody(username = "test", password = "test"))
            assertThat((response as ApiSuccessResponse).data.isSuccess).isTrue()
            assertThat(response.data.token).isNotEmpty()
        }
    }

    @Test
    fun testLoginEndpoint_withInvalidCredentials() {
        runBlocking {
            val response =
                userRepository.loginUser(
                    UserLoginBody(
                        username = Date().toString(),
                        password = "12345"
                    )
                )
            assertThat((response as ApiSuccessResponse).data.isSuccess).isFalse()
            assertThat(response.data.token).isNull()
        }
    }

    @Test
    fun testRegisterEndpoint_withNewUser() {
        runBlocking {
            val response =
                userRepository.registerNewUser(
                    UserRegisterBody(
                        username = Date().toString(),
                        password = "test"
                    )
                )
            assertThat((response as ApiSuccessResponse).data.isSuccess).isTrue()
        }
    }

    @Test
    fun testRegisterEndpoint_withExistingUser() {
        runBlocking {
            val response =
                userRepository.registerNewUser(
                    UserRegisterBody(
                        username = "test",
                        password = "test"
                    )
                )
            assertThat((response as ApiSuccessResponse).data.isSuccess).isFalse()
        }
    }

    @Test
    fun testMainCategoryEndpoint_shouldContainAtLeast10Item() {
        runBlocking {
            val user =
                userRepository.loginUser(UserLoginBody(username = "test", password = "test"))
            SharedPrefUtil.setToken((user as ApiSuccessResponse).data.token!!)
            val category = categoryRepository.fetchMainProductCategories()
            assertThat((category as ApiSuccessResponse).data.categoryList?.size).isGreaterThan(10)
        }
    }
}