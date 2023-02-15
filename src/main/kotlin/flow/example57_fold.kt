package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking

//fold 연산자는 reduce와 유사하고 초기값이 있다는 차이점만 있다
fun main() = runBlocking {
    val value = (1..10)
        .asFlow()
        .fold(10) { a, b ->
            a + b
        }
    println(value)
}