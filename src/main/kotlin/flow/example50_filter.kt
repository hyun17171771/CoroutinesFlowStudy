package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().filter {
        (it % 2) == 0 //술어
    }.collect {
        println(it) //짝수
    }

    (1..20).asFlow().filterNot {
        (it % 2) == 0
    }.collect {
        println(it) //홀수
    }
}

