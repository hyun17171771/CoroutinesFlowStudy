package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    listOf(1, 2, 3, 4, 5).asFlow().collect { value ->
        println(value)
    }
    (6..10).asFlow().collect {
        println(it)
    }
    flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect { print(it)}
}