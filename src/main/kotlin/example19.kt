import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// launch에서 자원을 할당한 경우(ex 파일 열고 닫을 때), finally 사용
suspend fun doOneTwoThree3() = coroutineScope {
    val job1 = launch {
        try {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        } finally {
            //취소됐을 때 여기 코드 실행
            println("job1 is finishing!")
        }
    }

    val job2 =  launch {
        try {
            println("launch1: ${Thread.currentThread().name}")
            println("1!")
        } finally {
            println("job2 is finishing!")
        }
    }

    val job3 = launch {
        try {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        } finally {
            println("job3 is finishing!")
        }
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree3()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}