import kotlinx.coroutines.*


suspend fun doOneTwoThree() = coroutineScope { //코루틴 빌더를 호출하기 위해 존재, 코루틴의 부모
    val job = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    job.join() //suspension point

    launch {
        // this:  코루틴 Receiver. 수신객체
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }
    repeat(1000) {
        launch {
            // this:  코루틴 Receiver. 수신객체
            //println("launch3: ${Thread.currentThread().name}")
            delay(500L)
            //println("2!")
        }
    }
    println("4!")
}

fun main() = runBlocking {
    // this:  코루틴 Receiver. 수신객체
    doOneTwoThree() //suspension point
    println("launch2: ${Thread.currentThread().name}")
    println("5!")
}