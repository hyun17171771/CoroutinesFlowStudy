import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

//뮤텍스 사용한 동시성 제어

suspend fun massiveRun1(action: suspend() -> Unit) {
    val n = 100 // 시작할 코루틴의 갯수
    val k = 1000 //코루틴 내에서 반복할 횟수
    val elapsed = measureTimeMillis {
        coroutineScope {
            repeat(n) {//scope for coroutines
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("$elapsed ms동안 ${n * k}개의 액션을 수행했습니다.")
}

val mutex = Mutex() //임계영역에 동시 접근하는 것을 제어함
var counter4 = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun1 {
            mutex.withLock {
                counter4++
            }
        }
    }
    println("Counter = $counter4")
}