import koma.*
import koma.extensions.get
import koma.extensions.toDoubleArray
import view.short

object ProcessData {

    val Q_n = mat[ 1, 0, 0 end 0, 1, 0 end 0, 0, 1 ]    //TODO: to be fixed

    val A = mat[ 0.7, 0.5, 0 end -0.5, 0.7, 0 end 0, 0, .9 ]
    val B = mat[ 1, 1, 1 ].transpose()
    val X_o = mat[ .1, .2, .3 ].transpose()

    private val Y_ref = mat[ 4, 3, 2, 4, 0, 1, 2, 4, 2, 3, 1, 1, 0, 4, 0, 0, 0, 0, 1, 4 ].transpose()
    private val C = mat[ 0, -1, 1 ]
    private const val Q_o = 0.5

    private val Q = fill(20, 20) { i, j ->
        when {
            i == j -> Q_o
            i < j -> .0
            else -> (C * A.pow(i-j) * B).short()[0,0]
        }
    }

    private val Phi = create(List(20) { i -> (C * A.pow(i)).short().toDoubleArray() }.toTypedArray())

    val U = (Q.pinv()*(Y_ref - (Phi * X_o))).short()
}