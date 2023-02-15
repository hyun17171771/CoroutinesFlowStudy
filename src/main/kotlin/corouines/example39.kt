import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

//공유 객체
suspend fun massiveRun(action: suspend() -> Unit) {
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

/**
 * counter 값이 100000이 나오지 않는 이유
 * 현재 스레드에서 counter 변수의 값을 증가시키는 동 다른 스레드에서도 counter 값을 보고 증가시킬 수 있다
 * 이 때 서로 증가시킨 값이 반영되지 않을 수 있다.
 * 즉 volatile은 가시성은 해결하지만 동시에 읽고 수정해서 생기는 문제를 해결하지 못한다.
 */

@Volatile //변수를 베인 메모리에 저장하겠다는 것을 명시
var counter = 0

/*fun main() = runBlocking {
    //withContext - 수행이 완료될 때까지 기다리는 코루틴 빌더
    withContext(Dispatchers.Default) {
        massiveRun {
            counter++
        }
    }
    println("Counter = $counter")
}*/


/**
 * 스레드 안전한 자료구조 사용
 * AtomicInteger 사용
 */
val counter2 = AtomicInteger()
/*
fun main() = runBlocking {
    //withContext - 수행이 완료될 때까지 기다리는 코루틴 빌더
    withContext(Dispatchers.Default) {
        massiveRun {
            counter2.incrementAndGet()
        }
    }
    println("Counter = $counter2")
}
*/

/**
 * 스레드 안전한 자료구조 사용
 * newSingleThreadContext 이용해서 스레드를 만들고 해당 스레도 사용
 * 특정 스레드 하나만 만들어서 그 스레드만 사용하게 함
 */
var counter3 = 0
val counterContext = newSingleThreadContext("CounterContext")
fun main() = runBlocking {
    //withContext - 수행이 완료될 때까지 기다리는 코루틴 빌더
/*    withContext(counterContext) { //전체 코드를 하나의 스레드에서
        massiveRun {
            counter3++
        }
    }*/
    withContext(Dispatchers.Default) {
        massiveRun {
            withContext(counterContext) {//더하는 코드만 하나의 스레드에서
                counter3++
            }
        }
    }
    println("Counter = $counter3")
}


