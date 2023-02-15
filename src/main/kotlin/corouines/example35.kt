import kotlinx.coroutines.*
import kotlin.random.Random

//CEH (Coroutine Exception Handler)

suspend fun printRandom1() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun printRandom2() {
    delay(500L)
    throw ArithmeticException()
}

val ceh = CoroutineExceptionHandler {_, exception -> //첫번째 인자는 coroutineContext 보통 받지 않고 언더바 사용 - 어느 컨텍스트에서 오류가 발생했는 지 중요하지 않기 때문
    println("Something happend: $exception")
}

/*
fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch(ceh) {
        launch { printRandom1() }
        launch { printRandom2() }
    }
    job.join()
}*/

//runBlocking에서는 CEH를 사용할 수 없다. runBlocking은 자식이 예외로 종료되면 항상 종료되고 CEH를 호출하지 않는다
/*fun main() = runBlocking {//1 최상단 코루틴
    val job = launch(ceh) {//2
        val a = async { printRandom1() } //3
        val b = async { printRandom2() } //3
    }
    job.join()
}*/

//SupervisorJob은 예외에 의한 취소를 아래쪽으로 내려가게 한다.
/**
 * 보통의 경우는 자식이 예외를 일으키면 그 예외가 부모로 올라가서 캔슬되고 다른 자식들도 캔들된다
 * 아래 경우는 자식에게만 영향을 주기 때문에 job2는 예외를 발생시켜도 그 부모가 캔슬되지 않고 job1은 그대로 실행된다
 */
fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + ceh)
    val job1 = scope.launch { printRandom1()}
    val job2 = scope.launch { printRandom2()}
    joinAll(job1, job2)
}
