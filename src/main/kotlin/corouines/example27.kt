import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

//코루틴 디스패처

fun main() = runBlocking<Unit> {
    launch {
        println("부모의 콘텍스트 /  ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {// 코어 수에 비례하는 스레드 풀에서 수행, cpu 연산작업이 많이 필요로하는 작업을 하기 위함(스레드를 많이 만들어도 일할 수 있는 것은 한정적임)
        println("Default /  ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {//코어 수 보다 훨씬 많은 스레드를 가지는 스레드 풀. IO 작업은 CPU를 덜 소모하기 때문
        println("IO /  ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {//어디에도 속하지 않음, 어디에서 수행될 수 있을 지 알 수가 없음
        println("Unconfined /  ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("Fast Campus")) { //새 스레드를 만들어서 쓰라 - (스레드 이름) 꼭 스레드를 사용해야할 경우
        println("newSingleThreadContext /  ${Thread.currentThread().name}")
    }
}