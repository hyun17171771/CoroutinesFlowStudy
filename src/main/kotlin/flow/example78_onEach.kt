package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

//addEventListener 대신 플로우의 onEach를 사용해 이벤트를 처리할 수 있다.

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    events()
        .onEach { event -> println("Event: $event") } //onEach는 collect가 실행되기 전엔 실행되지 않음
        .collect() //스트림이 끝날 때 까지 기다리게  but 이벤트는 계속 발생하므로 이 이벤트가 끝날 때까지 기다리게 됨
    println("Done")
    //유아이, 네트워크 호출 불가능
}