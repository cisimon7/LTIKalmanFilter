import koma.end
import koma.extensions.get
import koma.eye
import koma.mat
import koma.matrix.Matrix

object KalmanFilter {

    /*Data*/
    private val process = ProcessData
    private val sensor = SensorData

    /*Initialize States*/
    var K = mat[ 0, 0, 0 end 0, 0, 0 end 0, 0, 0 ]
    var P1 = mat[ 0, 0, 0 end 0, 0, 0 end 0, 0, 0 ]
    var X1 = mat[ 0, 0, 0 ].transpose()

    /*Process Matrices*/
    val F = process.A
    val G = process.B
    val u = process.U

    /*Sensor Matrices*/  //TODO: check
    val Z = sensor.Z
    val H = eye(3)

    /*Covariance matrices*/
    val Q = process.Q_n
    val R = sensor.R_n

    /*Entry States*/ //TODO: check
    var X = mat[ .1, .2, .3 ].transpose()
    var P = mat[ 30, 0, 0 end 0, 60, 0 end 0, 0, 25 ]

    private fun predict(i: Int) {
        /*Extrapolate the state*/
        X1 = (F*X) + (G*u[i,0])

        /*Extrapolate Uncertainty*/
        P1 = F*P*F.transpose() + Q
    }

    private fun correct() {
        /*Compute Kalman gain*/
        K = P1 * H.transpose() * (H*P1*H.T() + R).inv()

        /*Update the estimate with the measurement*/
        X = X1 + K * (Z(X1) - H*X1)

        /*Update the estimate uncertainty*/
        P = (eye(3) - K*H) * P1 * (eye(3) - K*H).T() + (K*R*K.T())
    }

    fun run(): Array<Pair<Matrix<Double>, Matrix<Double>>> {
        val states = mutableListOf(Pair(K, X1))

        /* Size of reading: 20 */
        repeat(20) { i ->
            predict(i)
            correct()
            states.add(Pair(K, X1))
        }

        return states.toTypedArray()
    }
}

/**
 * https://www.kalmanfilter.net/multiSummary.html
 * */