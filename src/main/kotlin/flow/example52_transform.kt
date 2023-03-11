package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * 주어진 flow의 각 값에 변환 기능을 적용한다
 * 변환은 emit한 요소를 변환하거나 건너뛰거나 여러번 emit할 수 있는 유연한 함수이다
 */
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