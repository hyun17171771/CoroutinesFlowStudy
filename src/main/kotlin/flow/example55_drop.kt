package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//drop 연산자는 처음 몇개의 결과를 버린다
fun main() = runBlocking<Unit> {
/*    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.drop(5).collect {
        println(it)
    }*/

    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.dropWhile { it < 5 }.collect {
        println(it)
    }
}