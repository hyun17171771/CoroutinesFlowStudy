package flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

//catch 연산자는 업스트림(catch 연산자를 쓰기 전의 코드)(에만 영향을 미치고 다운스트림에는 영향을 미치지 않는다. 이를 catch 투명성이라고 한다
fun ex(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    ex()
        .catch { e -> println("Caught $e") } //does not catch downstream exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" } //다운스트림의 예외를 캐치하지 못함
            println(value)
        }
}