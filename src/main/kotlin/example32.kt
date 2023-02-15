import kotlinx.coroutines.*

//코루틴 엘리먼트 결합 + 연산으로 합치면 된다.

@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking<Unit> {
    launch {// A (부모 컨텍스트)
        launch(Dispatchers.IO + CoroutineName("launch1")) {// 부모의 코루틴 컨텍스트 + Coroutine Context A+B+C (부모까지 포함된 결합)
            println("launch1: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(5000L)
        }
        launch(Dispatchers.Default + CoroutineName("launch2")) {
            println("launch1: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(10L)
        }
    }
}