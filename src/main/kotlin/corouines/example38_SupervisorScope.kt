import kotlinx.coroutines.*

//코루틴 스코프 + 슈퍼바이저 잡 = SupervisorScope
suspend fun supervisoredFunc() = supervisorScope { //supervisorScope는 예외가 발생할 수 있는 곳에 무조건 CEH를 붙여야 한다
    launch { printRandom1() }
    launch(ceh) { printRandom2() }
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val     job = scope.launch {
        supervisoredFunc()
    }
    job.join()
}