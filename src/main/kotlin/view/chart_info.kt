package view

import io.data2viz.geom.Point
import io.data2viz.random.RandomDistribution
import io.data2viz.scale.Scales
import io.data2viz.viz.Margins

val margins = Margins(40.5, 30.5, 50.5, 70.5)

const val width = 1_000.0
const val height = 700.0
val chartWidth = width - margins.hMargins
val chartHeight = height - margins.vMargins

val gPoints = listOf(Point(.0, .0))

val noise = RandomDistribution.normal(.0, .5)

val noiseGenerator = { gPoints.toList().random().let { (x, y) -> Point(x + noise(), y + noise()) } }

val xScale = Scales.Continuous.linear {
    domain = listOf(gPoints.map { it.x }.minOrNull()!!, gPoints.map { it.x }.maxOrNull()!!)
    range = listOf(.0, chartWidth)
}

val yScale = Scales.Continuous.linear {
    domain = listOf(gPoints.map { it.y }.minOrNull()!!, gPoints.map { it.y }.maxOrNull()!!)
    range = listOf(chartHeight, .0)
}