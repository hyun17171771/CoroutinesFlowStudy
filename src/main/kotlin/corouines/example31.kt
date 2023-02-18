import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val elapsed = measureTimeMillis {
        val job = launch {//부모
            launch {//자식1 - Job()을 인수로 넣으면 이 코루틴을 기다리지 않고 부모가 종료됨
                println("launch1: ${Thread.currentThread().name}")
                delay(5000L)
            }

            launch {//자식2
                println("launch2: ${Thread.currentThread().name}")
                delay(10L)
            }
        }
        job.join()
    }
    println(elapsed)
}