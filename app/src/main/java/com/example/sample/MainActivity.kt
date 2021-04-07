package com.example.sample


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

/**
 * jetpack composeでカウントアップする例
 */
@Composable
fun Counter() {
    //rememberでcomposeの状態を作成
    //countの値が変更されると、適宜使用しているcomposeのみ更新する
    var count by remember { mutableStateOf(0) }
    Column() {
        Button(onClick = {count++}) {
            Text(text = "count up")
        }
        Text(text = "$count")
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Counter()
}