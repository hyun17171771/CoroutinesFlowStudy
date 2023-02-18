package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


//conflate를 이용하면 중간의 값을 융화할 수 있다. 처리보다 빨리 발생한 데이터의 중간 값들을 누락한다
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple5().conflate()
            .collect { value ->
                delay(300)
                println(value)
            }
    }
    println("Collected in $time ms")
}