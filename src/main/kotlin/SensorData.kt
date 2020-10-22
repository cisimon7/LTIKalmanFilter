import io.data2viz.random.RandomDistribution
import koma.end
import koma.internal.KomaRandom
import koma.mat
import koma.matrix.Matrix
import koma.pow
import koma.randn

object SensorData {
    val R_n = mat[ pow(1.7, 2), 0, 0 end 0, 1, 0 end 0, 0, pow(1.8, 2) ]

    val n1 = RandomDistribution.normal(.0, 1.7)
    val n2 = RandomDistribution.normal(.0, 1.0)
    val n3 = RandomDistribution.normal(.0, 1.8)
    private val noise = mat[ n1(), n2(), n3() ].transpose()

    val Z = { x: Matrix<Double> -> x + noise }  //TODO: crosscheck
}