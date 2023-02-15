import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun  doThree() {
    println("launch1: ${Thread.currentThread().name}")
    delay(1000L)
    println("3!")
}
fun doOne() {
    println("launch1: ${Thread.currentThread().name}")
    println("1!")
}
suspend fun  doTwo() {
    println("launch1: ${Thread.currentThread().name}")
    delay(500L)
    println("2!")
}

fun main() = runBlocking {
    launch {
        doThree()
    }
    launch {
        doOne()
    }
    doTwo()
}

