package flow

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun log0(msg: String) = println("${Thread.currentThread().name}: $msg")

//launchIn을 이용하면 별도의 코루틴에서 플로우를 런칭할 수 있음
fun main() = runBlocking {
    events()
        .onEach { event -> log("Event: $event") }
        .launchIn(this) //새로운 코루틴
    log("Done")
    // 유아이 작업
    // 네트워크 호출
}