import kotlinx.coroutines.*

suspend fun doCount2() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) {
            val currentTime = System.currentTimeMillis()
            if(currentTime >= nextTime) {
                println(i)
                nextTime = currentTime   + 100L
                i++
            }
        }
    }
    job1.cancel()
}

fun main() = runBlocking {
/*    withTimeout(500L) { //try-catch 문을 항상 넣어서 처리해야 함
        doCount2()
    }*/
    val result = withTimeoutOrNull(1500L) {
        doCount2()
        true
    } ?: false
    println(result)
}