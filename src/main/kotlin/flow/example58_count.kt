package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking

/**
 * count는 종단 연산자이다
 * filter, map와 같은 중간 연산자 결과를 가져올 수 없고 collect나 다른 종단 연산자와 함께 써서 결과를 받아야 한다
 */
fun main() = runBlocking {
    val counter = (1..10)
        .asFlow()
        .count {//종단 연산자, 특정 값, 컬렉션 결과
            (it % 2) == 0
        }
    println(counter)
}