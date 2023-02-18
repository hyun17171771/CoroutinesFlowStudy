package flow


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//flatMapConcat은 첫번째 요소에 대해서 플레트닝을 하고 나서 두번째 요소를 한다
fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First") // 1: First / 2: First / 3: First
    delay(500)
    emit("$i: Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat {// requestFlow(1) .. requestFlow(2)
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime}")
        }
}