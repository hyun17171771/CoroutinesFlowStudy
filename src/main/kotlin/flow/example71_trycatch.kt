package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun simple0(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun simple00(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }
        .map {value ->
            check(value <= 1) { "Crashed on $value"} //플로우 내에서 예외발생시에도 처리 가능
            "string $value"
        }


/*fun main() = runBlocking {
    try {
        simple00().collect {value ->
            println(value)
            //check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}*/

/**
 * 빌더 코드 블록 내에서 예외를 처리하는 것은 예외 투명성을 어기는 것이다. (밖에서 예외가 발생했는 지 알수 없기 때문에)
 * 플로우에서는 catch 연산자를 이용하는 것은 권한다
 * catch 블록에서 예외를 새로운 데이터로 만들어 emit을 하거나, 다시 예외를 던지거나 로그를 남길 수 있다
 */

fun main() = runBlocking {
   simple00()
       .catch { e -> emit("Caught $e") } //emit on exception
       .collect { value -> println(value) }
}