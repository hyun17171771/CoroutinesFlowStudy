package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

suspend fun someCalc(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it) // 1 (100ms) 2
        emit(someCalc(it)) // 2 4
    }.collect {//1 2 2 4
        println(it)
    }
}