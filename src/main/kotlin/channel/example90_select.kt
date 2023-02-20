package channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

//먼저 끝나는 요청을 처리하는 것이 중요할 수 있다. 이 경우 select를 쓸 수 있다

/**
 * 채널에 대해 onReceive를 사용하는 것 이외에도 아래의 상황에서 사용할 수 있다.
 * Job - onJoin - 누구의 launch가 빨리 끝나는가
 * Deferred - onAwait -
 * SendChannel - onSend
 * ReceiveChannel - onReceive, onReceiveCatching
 * delay - onTimeout
 */

//리턴값이 리시브 채널
fun CoroutineScope.sayFast() = produce<String> {//코루틴 스코프 + 샌드채널
    while (true) {
        delay(100L)
        send("패스트")
    }
}

fun CoroutineScope.sayCampus() = produce<String> {
    while (true) {
        delay(150L)
        send("캠퍼스")
    }
}

fun main() = runBlocking {
    val fasts = sayFast()
    val campuses = sayCampus()
    repeat (5) {//5번 동안 select
        select<Unit> {//먼저 끝내는 애만 듣겠다
            fasts.onReceive {
                println("fast: $it")
            }
            campuses.onReceive {
                println("campus: $it")
            }
            //asyncJob.onAwait
        }
    }
    coroutineContext.cancelChildren()
}