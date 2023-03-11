package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// 기존 try-catch절 다음 finally - 예외 발생이든 성공이든 finally에서 처리
//onCompletion 연산자를 선언해서 완료를 처리할 수 있다
fun ex1(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
/*    ex1()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }*/
    ex1()
        .map {
            if (it > 2) {
                throw IllegalStateException()
            }
            it + 1
        } //1, 2, 예외
        .catch { e -> emit(-99) } //예외처리를 하지 않으면 예외처리만 발생하고 onCompletion이 실행되지 않음
        .onCompletion { println("Done") } //완료 처리
        .collect { value -> println(value) }
}