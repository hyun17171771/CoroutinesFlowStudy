import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//공유 객체
suspend fun massiveRun(action: suspend() -> Unit) {
    val n = 100 // 시작할 코루틴의 갯수
    val k = 1000 //코루틴 내에서 반복할 횟수
    val elapsed = measureTimeMillis { 
        coroutineScope { //scope for coroutines
            launch {
                repeat(k) { action() }
            }

        }
    }
    println("$elapsed ms동안 ${n * k}개의 액션을 수행했습니다.")
}

var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            counter++
        }
    }
    println("Counter = $counter")
}