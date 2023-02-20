package channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//버퍼 사이즈를 랑데뷰(Channel.RENDEZVOUS)로 지정
/**
 * 랑데뷰는 버퍼 사이즈를 0으로 지정하는 것이다. 생성자에 사이즈를 전달하지 않으면 랑데뷰가 디폴트이다. 이외에도 사이즈 대신 사용할 수 있는 다른 설정값이 있다.
 * UNLIMITED - 무제한으로 설정 - 링크드리스트 형태
 * CONFLATED - 오래된 값이 지워짐 - 처리못한 값은 지워짐
 * BUFFERED - 64개의 버퍼. 오버플로우엔 suspend
 */
fun main() = runBlocking {
    val channel = Channel<Int>(Channel.RENDEZVOUS) // 0
    launch {
        for (x in 1..20) {
            println("${x} 전송중")
            channel.send(x)
        }
        channel.close()
    }

    for (x in channel) {
        println("${x} 수신")
        delay(100L)
    }
    println("완료")
}