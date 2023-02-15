import kotlinx.coroutines.*
import kotlin.random.Random

//CoroutineScope - 어느 코루틴 스콥에도 속하지 않은 스코프, 간편하게 쓸 수 있지만 어떤 계층에도 속하지 않고 영원히 동작하게 된다는 문제가 있기 때문에 잘 사용하지 않음
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