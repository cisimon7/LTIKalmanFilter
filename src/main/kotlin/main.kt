import koma.extensions.get
import view.short

fun main(args: Array<String>) {
    val states = KalmanFilter.run()

    /*states.forEach { (k, x) ->
        println("${x.short()}")
    }*/

    /*states.drop(1).map { (k, x) -> Pair(k.short(), x.short()) }.forEach { (k, _) ->
        println("${k[0,0]}, ${k[1,1]}, ${k[2,2]}")
    }*/

    println(states[1])
}