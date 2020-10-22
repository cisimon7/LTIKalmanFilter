package view

import io.data2viz.geom.Point
import io.data2viz.scale.Scales
import io.data2viz.viz.Margins
import koma.extensions.get
import koma.matrix.Matrix

val margins = Margins(40.5, 30.5, 50.5, 70.5)

const val width = 1_000.0
const val height = 700.0
val chartWidth = width - margins.hMargins
val chartHeight = height - margins.vMargins

val states = KalmanFilter.run()
val K = states.drop(1).map { (k, _) -> Triple(k[0,0], k[1,1], k[2,2]) }

val k1 = K.mapIndexed { idx, triple -> Point(idx.toDouble(), triple.first) }
val k2 = K.mapIndexed { idx, triple -> Point(idx.toDouble(), triple.second) }
val k3 = K.mapIndexed { idx, triple -> Point(idx.toDouble(), triple.third) }

val xScale = Scales.Continuous.linear {
    domain = listOf((k1 + k2 + k3).map { it.x }.minOrNull()!!, (k1 + k2 + k3).map { it.x }.maxOrNull()!!)
    range = listOf(.0, chartWidth)
}

val yScale = Scales.Continuous.linear {
    domain = listOf(.0, 1.0)
    range = listOf(chartHeight, .0)
}