package utils

/**
 * Created by Тарас on 28.09.2016.
 */

object MatrixUtils {
    fun multiply(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {

        val aRows = a.size
        val aColumns = a[0].size
        val bRows = b.size
        val bColumns = b[0].size

        if (aColumns != bRows) {
            throw IllegalArgumentException("A:Rows: $aColumns did not match B:Columns $bRows.")
        }

        val c = Array(aRows) { DoubleArray(bColumns) }
        for (i in c.indices) {
            for (j in 0..c[i].size - 1) {
                c[i][j] = 0.00000
            }
        }

        for (i in 0..aRows - 1) { // aRow
            for (j in 0..bColumns - 1) { // bColumn
                for (k in 0..aColumns - 1) { // aColumn
                    c[i][j] += a[i][k] * b[k][j]
                }
            }
        }

        return c
    }
}
