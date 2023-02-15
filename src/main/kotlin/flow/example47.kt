package flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


//위 아래 본질 적으로 값음
fun main() = runBlocking<Unit> {
    flowOf(1,2,3,4,5).collect { value ->
        println(value)
    }
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.collect { println(it) }
}