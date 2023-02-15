import kotlinx.coroutines.*

suspend fun doCount() = coroutineScope {
    val job1 = launch(Dispatchers.Default) { //취소 불가능한 잡        var i = 1
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while(i <= 10 && isActive) { //취소 가능한 코드 추가
            val currentTime = System.currentTimeMillis()
            if(currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }

    delay(200L)
    job1.cancelAndJoin()
    println("doCount Done!")
}

fun main() = runBlocking {
    doCount()
}