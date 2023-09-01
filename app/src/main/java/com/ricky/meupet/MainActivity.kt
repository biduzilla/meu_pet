package com.ricky.meupet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.ricky.meupet.common.DataStoreUtil
import com.ricky.meupet.navigation.AppNavigation
import com.ricky.meupet.ui.theme.MeuPetTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreUtil = DataStoreUtil(applicationContext)
        var darkMode by mutableStateOf(false)

        lifecycleScope.launch {
            dataStoreUtil.getTheme().collect{
                darkMode = it
            }
        }
        setContent {
            MeuPetTheme(darkTheme = darkMode) {
                AppNavigation()
            }
        }
    }
}

