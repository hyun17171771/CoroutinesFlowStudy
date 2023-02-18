package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

//flatMapMerge는 첫 요소의 플레트닝을 시작하며 이어 다음 요소의 플레트닝을 시작한다(두번째 요소를 기다리지 않는다)
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapMerge {// requestFlow(1) .. requestFlow(2)
            requestFlow(it) // 1:First, 2:First, 3:First .. :Second, :Second, :Second
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime}")
        }
}