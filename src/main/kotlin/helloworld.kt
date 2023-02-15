import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("launch: ${Thread.currentThread().name}")
        delay(100L)
        println("World!")
    }
    println("runBlocking: ${Thread.currentThread().name}")
    //delay(500L) //이 코루틴 스콥이 쉬고 있는 동안에 다른 코루틴이 수행할 수 있도록 양보
    Thread.sleep(500) //현재 스레드가 여전히 주도권을 가지고 있음
    println("Hello")
}