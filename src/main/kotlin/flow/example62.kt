package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//buffer로 버퍼를 추가해 보내는 측이 더 이상 기다리지 않게 한다
fun simple5(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simple5().buffer() //소비측의 300ms를 기다리지 않고 바로 다음 데이터를 불러온다
            .collect { value ->
                delay(300)
                println(value)
            }
    }
    print("Collected in $time ms")
}