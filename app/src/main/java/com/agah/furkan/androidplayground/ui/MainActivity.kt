package com.agah.furkan.androidplayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.agah.furkan.androidplayground.SharedViewModel
import com.agah.furkan.androidplayground.ui.main.MainScreen
import com.agah.furkan.core.session.SessionListener
import com.agah.furkan.core.session.SessionManager
import com.agah.furkan.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), SessionListener {
    private val sharedViewModel by viewModels<SharedViewModel>()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager.addSessionListener(this)

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    override fun sessionStarted() {

    }

    override fun sessionEnded() {
        sharedViewModel.sessionEnded()
    }
}
