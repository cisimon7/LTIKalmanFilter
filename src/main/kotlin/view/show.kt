package view

import io.data2viz.axis.Orient
import io.data2viz.axis.axis
import io.data2viz.color.Colors
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
                        group {
                            path {
                                stroke=Colors.Web.darkred
                                strokeWidth=1.0

                                val pt = k1
                                moveTo(xScale(pt[0].x), yScale(pt[0].y))
                                pt.drop(0).forEach { (x, y) ->
                                    lineTo(xScale(x), yScale(y))
                                }
                            }
                            k1.forEach { (x_, y_) ->
                                circle {
                                    x = xScale(x_)
                                    y = yScale(y_)
                                    fill = Colors.Web.tomato
                                    radius = 3.0
                                }
                            }
                        }
                        group {
                            path {
                                stroke=Colors.Web.darkgreen
                                strokeWidth=1.0

                                val pt = k2
                                moveTo(xScale(pt[0].x), yScale(pt[0].y))
                                pt.drop(0).forEach { (x, y) ->
                                    lineTo(xScale(x), yScale(y))
                                }
                            }
                            k2.forEach { (x_, y_) ->
                                circle {
                                    x = xScale(x_)
                                    y = yScale(y_)
                                    fill = Colors.Web.seagreen
                                    radius = 3.0
                                }
                            }
                        }
                        group {
                            path {
                                stroke=Colors.Web.darkblue
                                strokeWidth=1.0

                                val pt = k3
                                moveTo(xScale(pt[0].x), yScale(pt[0].y))
                                pt.drop(0).forEach { (x, y) ->
                                    lineTo(xScale(x), yScale(y))
                                }
                            }
                            k3.forEach { (x_, y_) ->
                                circle {
                                    x = xScale(x_)
                                    y = yScale(y_)
                                    fill = Colors.Web.blueviolet
                                    radius = 3.0
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    init { title="MultiDimensionalKalmanFilter" }
}
