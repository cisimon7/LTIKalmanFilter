import view.short

fun main(args: Array<String>) {

    /* Q matrix */
    println("Q matrix:")
    println(ProcessData.Q.short())

    /* Optimum values for u */
    println("\n\nU matrix")
    println(ProcessData.U.short())
}