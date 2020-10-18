package view

import io.data2viz.axis.Orient
import io.data2viz.axis.axis
import io.data2viz.geom.size
import javafx.scene.Parent
import tornadofx.*

fun main(args: Array<String>) = launch<KalmanApp>(args)

class KalmanApp : App(MyView::class)

class MyView : View() {
    override val root: Parent = group {
        canvas(width, height) {
            visualize {
                size = size(chartWidth, chartHeight)
                group {
                    transform { translate(x= margins.left, y= margins.top) }

                    group {
                        transform { translate(x= -10.0) }
                        axis(Orient.LEFT, yScale) { tickFormat={it.round().toString()} }
                    }

                    group {
                        transform { translate(y= chartHeight+10.0) }
                        axis(Orient.BOTTOM, xScale) { tickFormat={it.round().toString()} }
                    }

                    group {

                    }
                }
            }
        }
    }

    init { title="MultiDimensionalKalmanFilter" }
}
