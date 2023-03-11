import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

//CoroutineScope - CoroutineScope는 인자로 CoroutineContext를 받는데 코루틴 엘리먼트를 하나만 넣어도 좋고 여러 엘리먼트를 합쳐서 코루틴컨텍스트를 만들어도 된다
suspend fun printRandom6() {
    delay(500L)
    println(Random.nextInt(0, 500))
}

fun main() {
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch(Dispatchers.IO){
        launch { printRandom5() }
    }
    Thread.sleep(1000L) //main이 runBlocking이 아니기 때문에 Thread.sleep 사용
}