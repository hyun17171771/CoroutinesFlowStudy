package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


fun log1(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple1() : Flow<Int> = flow { //플로우 내에서는 컨텍스트를 바꿀 수 없음 - 예외 발생
    //withContext(Dispatchers.Default) {
        log1("flow를 시작합니다")
        for (i in 1..10) {
            emit(i)
        }
    //}
}

fun simple2() : Flow<Int> = flow {
    for(i in 1..10) {
        delay(100L)
        log1("값 ${i}를 emit합니다.")
        emit(i)
    } //업스트림
}.flowOn(Dispatchers.Default)// flowOn 연산자를 통해 컨텍스트를 바꿀 수 있다


fun simple3() : Flow<Int> = flow {
    for(i in 1..10) {
        delay(100L)
        log1("값 ${i}를 emit합니다.")
        emit(i)
    } //업스트림
}
    .map {//다운스트림
        it * 2
    }.flowOn(Dispatchers.Default)




fun main() = runBlocking<Unit> {
        simple3()
            .collect { value -> log("${value}를 받음.")} //다운스트림
}