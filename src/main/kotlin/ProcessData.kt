import koma.create
import koma.end
import koma.extensions.toDoubleArray
import koma.fill
import koma.mat
import view.short

object ProcessData {

    val Q_n = mat[ 1, 0, 0 end 0, 1, 0 end 0, 0, 1 ]    //TODO: to be fixed

    val A = mat[ 0.7, 0.5, 0 end -0.5, 0.7, 0 end 0, 0, .9 ]
    val B = mat[ 1, 1, 1 ].transpose()
    val Xo = mat[ .1, .2, .3 ].transpose()

    private val yRef = mat[ 4, 3, 2, 4, 0, 1, 2, 4, 2, 3, 1, 1, 0, 4, 0, 0, 0, 0, 1, 4 ].transpose()
    private val C = mat[ 0, -1, 1 ]
    private const val Q_o = 0.5

    val Q = fill(20, 20) { i, j ->
        when {
            i == j -> Q_o
            i < j -> .0
            else -> (C * A.pow(i-j) * B)/*.short()*/.getDouble(0,0)
        }
    }

    private val Phi = create(List(20) { i -> (C * A.pow(i))/*.short()*/.toDoubleArray() }.toTypedArray())

    val U = (Q.pinv()*(yRef - (Phi * Xo)))/*.short()*/
}