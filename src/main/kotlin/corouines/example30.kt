import kotlinx.coroutines.*

fun main() = runBlocking {//증부모
    val job = launch { //부모
        launch(Job()) {//launch를 만들 때 Job을 지정해주면 더이상 상위 코루틴과 부모 자식 관계가 수립되지 않음, 상위 코루틴은 이 코드 블럭을 기다려주지 않고 다른 코루틴이 취소되도 취소되지 않음
            println(coroutineContext[Job])
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }

        launch {
            println(coroutineContext[Job])
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
    }
    delay(500L)
    job.cancelAndJoin() //이 경우 3!이 출력됨(취소되지 않음으로)
    //delay(1000L) - 해당 코드 주석처리할 경우 3!이 출력되지 않음(자식이 아니기 때문에 기다리지 않고 종료함)
}
