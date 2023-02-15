package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.random.Random

/**
 * Flow - 코틀린에서 쓸 수 있는 비동기 스트림(cold - 요청이 있을 때만 값을 보냄)
 * 플로우는 콜드 스트림이기 때문에 요청 측에서 collect를 호출해야 값을 발생갛기 시작한
 *
 * 핫스트밂 - 0개 이상의 상대를 향해 지속적으로 값을 전달달 */
fun flowSomething(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 500)) //
        delay(10L)
    }
}

/*
fun main() = runBlocking {
    val result = withTimeoutOrNull(500L) {
            flowSomething().collect() { value ->
                println(value)
            }
            true
    } ?: false
    if(!result) {
    println("취소되었습니다.")
    }
}*/

//예외 발생
fun main() = runBlocking<Unit> {
    withTimeout(500L) {
        flowSomething().collect() { value ->
            println(value)
        }
    }
}