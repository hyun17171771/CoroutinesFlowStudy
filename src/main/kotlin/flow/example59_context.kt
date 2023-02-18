package flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//플로우는 현재 코루틴 컨텍스트에서 호출된다
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple() : Flow<Int> = flow {
    log("flow를 시작합니다")
    for(i in 1..10) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO){
        simple()
            .collect { value -> log("${value}를 받음.")}
    }
}