package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * asFlow는 컬렉션이나 시퀸스로 플로우를 만들수 있다
 */
fun main() = runBlocking<Unit> {
    listOf(1, 2, 3, 4, 5).asFlow().collect { value ->
        println(value)
    }
    (6..10).asFlow().collect {
        println(it)
    }
    flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect { print(it)}
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
        emit(6)
        emit(7)
        emit(8)
    }.collect { print(it)}
}