package com.agah.furkan.x

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.util.ext.launchAndCollectIn
import com.agah.furkan.ui.components.SearchTextField
import com.agah.furkan.x.databinding.ActivityMainBinding
import com.agah.furkan.x.model.UiState

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var storeAdapter: StoreListAdapter? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.searchView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val searchText = viewModel.uiState.collectAsStateWithLifecycle().value.searchText
                AppTheme {
                    SearchTextField(value = searchText, onFocusChanged = {
                    }, onValueChange = {
                        viewModel.onSearchTextChanged(it)
                    })
                }
            }
        }
        initRecyclerView()
        initUiStateObserver()
    }

    private fun initUiStateObserver() {
        viewModel.uiState.launchAndCollectIn(this) { state ->
            handleStoreList(state)
        }
    }

    private fun handleStoreList(state: UiState) {
        storeAdapter?.submitList(state.storeList)
    }

    private fun initRecyclerView() {
        storeAdapter = StoreListAdapter()
        binding.rvStoreList.adapter = storeAdapter
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
