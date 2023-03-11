import kotlinx.coroutines.*


//예상 - 실제
/*
fun main()  {
    runBlocking { //계층적, 구조적 - 부모가 취소되면 자식 코루틴도 취소된다
        launch {
            println("launch: ${Thread.currentThread().name}") //1 -2
            delay(200L) //suspension point
            println("3!") //4 - 4
        }
        launch {
            println("launch2: ${Thread.currentThread().name}") //2 - 3
            delay(1000L)
            println("1!") //6 - 6
        }
        println("runBlocking: ${Thread.currentThread().name}") //3 - 1
        delay(500L) // suspension point
        println("2!") //5 - 5
    }
    print("4!") //런블로킹 안에 있는 다른 코루틴이 전부 끝마치기 전까지 시작하지 않음
}
*/

fun main(args: Array<String>) = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}


