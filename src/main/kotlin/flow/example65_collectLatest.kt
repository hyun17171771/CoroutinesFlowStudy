package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis



fun main() = runBlocking {
    val time = measureTimeMillis {
        simple5().collectLatest { value -> //첫번째 값이 도착한 후 수신 측에서 처리하기 전에 두번째 값이 도착하면 첫번째 값을 리셋하는 형태, 1을 처리하는 도중 2가 오면 1 리셋, 2를 처리하는 동안 3이오면 2리셋
            println("값 ${value}를 처리하기 시작합니다.")
            delay(300)
            println(value)
            println("처리 완료하였습니다.")
        }
    }
    println("Collected in $time ms")
}