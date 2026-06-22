package io.github.mucute.qwq.nodedev.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.mucute.qwq.nodedev.navigation.Navigation
import io.github.mucute.qwq.nodedev.screen.MainScreen
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeDevTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NodeDevTheme {
                Navigation()
            }
        }
    }

}