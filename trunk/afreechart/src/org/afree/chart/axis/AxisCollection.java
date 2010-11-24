/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *    AFreeChart: http://code.google.com/p/afreechart/
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -------------------
 * AxisCollection.java
 * -------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 * Changes
 * -------
 * 03-Nov-2003 : Added standard header (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 */

package org.afree.chart.axis;

import java.util.List;

import org.afree.ui.RectangleEdge;

/**
 * A collection of axes that have been assigned to the TOP, BOTTOM, LEFT or
 * RIGHT of a chart. This class is used internally by AFreeChart, you won't
 * normally need to use it yourself.
 */
public class AxisCollection {

    /** The axes that need to be drawn at the top of the plot area. */
    private List axesAtTop;

    /** The axes that need to be drawn at the bottom of the plot area. */
    private List axesAtBottom;

    /** The axes that need to be drawn at the left of the plot area. */
    private List axesAtLeft;

    /** The axes that need to be drawn at the right of the plot area. */
    private List axesAtRight;

    /**
     * Creates a new empty collection.
     */
    public AxisCollection() {
        this.axesAtTop = new java.util.ArrayList();
        this.axesAtBottom = new java.util.ArrayList();
        this.axesAtLeft = new java.util.ArrayList();
        this.axesAtRight = new java.util.ArrayList();
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the top of
     * the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtTop() {
        return this.axesAtTop;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the bottom
     * of the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtBottom() {
        return this.axesAtBottom;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the left of
     * the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtLeft() {
        return this.axesAtLeft;
    }

    /**
     * Returns a list of the axes (if any) that need to be drawn at the right of
     * the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtRight() {
        return this.axesAtRight;
    }

    /**
     * Adds an axis to the collection.
     * 
     * @param axis
     *            the axis (<code>null</code> not permitted).
     * @param edge
     *            the edge of the plot that the axis should be drawn on (
     *            <code>null</code> not permitted).
     */
    public void add(Axis axis, RectangleEdge edge) {
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }
        if (edge == null) {
            throw new IllegalArgumentException("Null 'edge' argument.");
        }
        if (edge == RectangleEdge.TOP) {
            this.axesAtTop.add(axis);
        } else if (edge == RectangleEdge.BOTTOM) {
            this.axesAtBottom.add(axis);
        } else if (edge == RectangleEdge.LEFT) {
            this.axesAtLeft.add(axis);
        } else if (edge == RectangleEdge.RIGHT) {
            this.axesAtRight.add(axis);
        }
    }

}
