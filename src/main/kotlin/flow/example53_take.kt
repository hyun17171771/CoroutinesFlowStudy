package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * take는 값을 몇 개 가져올 지 결정한다
 * takeWhile은 조건을 만족하는 동안만 값을 가져오게 할 수 있음
 */

fun main() = runBlocking<Unit> {
/*    (1..20).asFlow().transform {
        emit(it) // 1 (100ms) 2
        emit(someCalc(it)) // 2 4
    }.take(5) //take 연산자는 몇 개의 수행 결과만 취한다
        .collect {//1 2 2 4
            println(it)
        }*/

    (1..20).asFlow().transform {
        emit(it) // 1 (100ms) 2
        emit(someCalc(it)) // 2 4
    }.takeWhile {
        it < 15
    } //takeWhile 연산자는 조건에 만족하는 동안만 값을 가져오게 할 수 있다
        .collect {//1 2 2 4
            println(it)
        }
}