package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

//flatMapLatest는 다음 요소의 플레트닝을 시작하며 이전에 진행 중이던 플레트닝을 취소한다
fun main() = runBlocking {
    val startTIme = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100)}
        .flatMapLatest {//requestFlow(1).cancel() , requestFlow(2).cancel, requestFlow(3)
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTIme} ms from start")
        }
}