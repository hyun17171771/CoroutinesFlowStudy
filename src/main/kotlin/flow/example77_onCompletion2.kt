package flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

//onCompletion은 종료 처리를 할 때 예외가 발생되었는 지 여부를 알 수 있다
//반면 try catch 절에서는 catch에서 문제 발생한 것은 알아도 finally에서 예외가 발생했는 지 여부는 알 수 없다
fun ex77(): Flow<Int> = flow {
    emit(1)
    //throw RuntimeException()
}

fun main() = runBlocking {
    ex77()
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed exceptionally")
            } else {
                println("Flow completed")
            }
        }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}