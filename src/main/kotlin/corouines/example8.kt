import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun  doThree() {
    println("launch3: ${Thread.currentThread().name}") //2
    delay(1000L)
    println("3!")
}
fun doOne() {
    println("launch1: ${Thread.currentThread().name}")
    println("1!")
}
suspend fun  doTwo() {
    println("launch2: ${Thread.currentThread().name}") //1
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

