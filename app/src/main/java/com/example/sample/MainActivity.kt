package com.example.sample


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Counter(viewModel)
        }
    }
}

/**
 * composeで使用するviewModel
 * 実装側でobserveAsStateを使用すれば良いため
 * 既存実装を変更しなくてもよさそう
 * viewModelをcomposeで使用するときはappCompat1.3-rc01が必要
 */
class CounterViewModel : ViewModel() {
    var count = MutableLiveData(0)

    fun countUp() {
        count.value = count.value?.plus(1)
    }
}

/**
 * jetpack composeでカウントアップする例
 * ライブデータをobserveAsStateでCompose的な監視可能なobjectに変換
 * (viewModelと二回も初期値設定しないとだめなの？)
 * observeAsStateはruntime-livedata:1.0.0-beta03で使用可能
 */
@Composable
fun Counter(viewModel: CounterViewModel) {
    val count: Int by viewModel.count.observeAsState(0)
    Counter(count, {viewModel.countUp()})
}

@Composable
fun Counter(count: Int, countup: () -> Unit) {
    Column() {
        Button(onClick = countup) {
            Text(text = "count up")
        }
        Text(text = "$count")
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}