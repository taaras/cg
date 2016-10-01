package figures

/**
 * Created by Тарас on 28.09.2016.
 */

import utils.GraphUtils

open class Arc(radius: Int, center: Point, var startDegree: Int, var endDegree: Int) : Circle(radius, center) {

    override fun definePoints() {
        points = GraphUtils.getArcPoints(startDegree, endDegree, center, radius)
    }
}
