package com.example.learning_andriod

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning_andriod.ui.theme.Learning_AndriodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Learning_AndriodTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            Log.e("tag", "test")
            val intent = Intent(context, TestActivity::class.java)
            context.startActivity(intent)
        }
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Learning_AndriodTheme {
        Greeting("Android")
    }
}