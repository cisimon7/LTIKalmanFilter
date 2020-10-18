import koma.end
import koma.eye
import koma.mat
import koma.pow

object KalmanFilter {

    // Data
    val process = ProcessData
    val sensor = SensorData

    // Kalman Gain
    var K = mat[ pow(1.7, 2), 0, 0 end 0, 1, 0 end 0, 0, pow(1.8, 2) ]
    var P_1 = mat[ 0, 0, 0 end 0, 0, 0 end 0, 0, 0 ]
    var X_1 = mat[ 0, 0, 0 ] //TODO: check initial state

    // Process Matrices
    val F = process.A
    val G = process.B
    val u = process.U

    // Sensor Matrices
    val Z = sensor.Z
    val H = eye(3)

    // Covariance matrices
    val Q_n = process.Q_n
    val R_n = sensor.R_n

    // Initial States
    var X = process.X_o
    var P = mat[ 0, 0, 0 end 0, 0, 0 end 0, 0, 0 ]  //TODO: Check covariance matrix of state

    fun predict(){
        //Extrapolate the state
        X_1 = (F*X) + (G*u)

        //Extrapolate Uncertainty
        P_1 = F*P*F.transpose() + Q_n
    }

    fun correct() {
        //Compute Kalman gain
        K = P_1 * H.transpose() * (H*P_1*H.T() + R_n).inv()

        //Update the estimate with the measurement
        X = X_1 + K * (Z(X_1) - H*X_1)

        //Update the estimate uncertainty
        P = (eye(3) - K*H)* P_1*(eye(3) - K*H).T() + K*R_n*K.transpose()
    }

    fun run() {

    }
}

/**
 * https://www.kalmanfilter.net/multiSummary.html
 * */