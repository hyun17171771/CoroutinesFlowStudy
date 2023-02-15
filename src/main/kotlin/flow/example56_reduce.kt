package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * collect, reduce, fold, toList, toSet과 같은 연산자는 플로우를 끝내는 함수라 종단 연산자라고 한다
 * reduce흔히 map과 reduce로 함께 소개되는 함수형 언어의 오래된 메커니즘이다. 첫번째 값을 결과에 넣은 후 각 값을 가져와 누진적으로 계산한다
 *
 */

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
        .reduce { a, b ->
            a + b
        }
    println(value)
}