package com.singularity_code.singularitycodebase.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.singularity_code.singularitycodebase.data.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.ui.activity.providerdemo.ProviderDemoActivity
import com.singularity_code.singularitycodebase.ui.activity.providerdemo.ProviderDemoViewModel
import com.singularity_code.singularitycodebase.ui.activity.roomdemo.RoomDemoActivity
import com.singularity_code.singularitycodebase.util.theme.SingularityCodebaseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SingularityCodebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = {
                                startActivity(
                                    Intent(this@MainActivity, ProviderDemoActivity::class.java)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Provider Demo")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                startActivity(
                                    Intent(this@MainActivity, RoomDemoActivity::class.java)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Room Database Demo")
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
            }
        }
    }
}