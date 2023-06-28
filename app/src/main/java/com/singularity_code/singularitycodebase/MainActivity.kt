package com.singularity_code.singularitycodebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularity_code.codebase.CODE_BASE
import com.singularity_code.singularitycodebase.domain.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.ui.theme.SingularityCodebaseTheme

class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SingularityCodebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = vm.sampleText.collectAsState("").value,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = {
                                vm.sampleProvider.update(
                                    GetSamplePLD(
                                        credential = "Singularity Indonesia"
                                    )
                                )
                            }
                        ) {
                            Text(text = "Load")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SingularityCodebaseTheme {
        Greeting("Android")
    }
}