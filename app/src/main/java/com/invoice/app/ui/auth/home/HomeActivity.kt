package com.invoice.app.ui.auth.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.invoice.app.ui.BaseActivity
import com.invoice.app.ui.auth.home.nav.HomeNavHost
import com.invoice.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeNavHost()
            }
        }
    }
}