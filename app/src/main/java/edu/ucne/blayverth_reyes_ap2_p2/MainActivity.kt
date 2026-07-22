package edu.ucne.blayverth_reyes_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.blayverth_reyes_ap2_p2.presentation.navigation.AppNavDisplay
import edu.ucne.blayverth_reyes_ap2_p2.presentation.navigation.Screen
import edu.ucne.blayverth_reyes_ap2_p2.ui.theme.Blayverth_Reyes_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Blayverth_Reyes_AP2_P2Theme {
                val backStack = rememberNavBackStack(Screen.List)

                Scaffold { innerPadding ->
                    AppNavDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

