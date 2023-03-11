package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().filter {
        (it % 2) == 0 //술어 - 이 조건만 살아남음
    }.collect {
        println(it) //짝수
    }

    (1..20).asFlow().filterNot {
        (it % 2) == 0 // 이 조건만 제외
    }.collect {
        println(it) //홀수
    }
}

