import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  {
    runBlocking { //계층적, 구조적 - 부모가 취소되면 자식 코루틴도 취소된다
        launch {
            println("launch: ${Thread.currentThread().name}")
            delay(200L) //suspension point
            println("3!")
        }
        launch {
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
        println("runBlocking: ${Thread.currentThread().name}")
        delay(500L) // suspension point
        println("2!")
    }
    print("4!") //런블로킹 안에 있는 다른 코루틴이 전부 끝마치기 전까지 시작하지 않음
}

