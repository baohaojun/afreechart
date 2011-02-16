/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * ----------
 * Title.java
 * ----------
 * 
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 *
 * Original Author:  shiraki  (for Icom Systech Co., Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by David Berry and Contributors.
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 14-Nov-2001 : Package com.jrefinery.common.ui.* changed to
 *               com.jrefinery.ui.* (DG);
 * 07-Feb-2002 : Changed blank space around title from Insets --> Spacer, to
 *               allow for relative or absolute spacing (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 14-Oct-2002 : Changed the event listener storage structure (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 22-Sep-2003 : Spacer cannot be null. Added nullpointer checks for this (TM);
 * 08-Jan-2003 : Renamed AbstractTitle --> Title and moved to separate
 *               package (DG);
 * 26-Oct-2004 : Refactored to implement Block interface, and removed redundant
 *               constants (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 * 02-Feb-2005 : Changed Spacer --> RectangleInsets for padding (DG);
 * 03-May-2005 : Fixed problem in equals() method (DG);
 * 19-Sep-2008 : Added visibility flag (DG);
 *
 */

package org.afree.chart.title;

import java.io.Serializable;

import org.afree.ui.HorizontalAlignment;
import org.afree.util.ObjectUtilities;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.VerticalAlignment;
import org.afree.chart.block.AbstractBlock;
import org.afree.chart.block.Block;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.graphics.geom.RectShape;
import android.graphics.Canvas;

/**
 * The base class for all chart titles. A chart can have multiple titles,
 * appearing at the top, bottom, left or right of the chart.
 * <P>
 * Concrete implementations of this class will render text and images, and hence
 * do the actual work of drawing titles.
 */
public abstract class Title extends AbstractBlock implements Block, Cloneable,
        Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -6675162505277817221L;

    /** The default title position. */
    public static final RectangleEdge DEFAULT_POSITION = RectangleEdge.TOP;

    /** The default horizontal alignment. */
    public static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;

    /** The default vertical alignment. */
    public static final VerticalAlignment DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.CENTER;

    /** Default title padding. */
    public static final RectangleInsets DEFAULT_PADDING = new RectangleInsets(
            1, 1, 1, 1);

    /**
     * A flag that controls whether or not the title is visible.
     * 
     * @since JFreeChart 1.0.11
     */
    public boolean visible;

    /** The title position. */
    private RectangleEdge position;

    /** The horizontal alignment of the title content. */
    private HorizontalAlignment horizontalAlignment;

    /** The vertical alignment of the title content. */
    private VerticalAlignment verticalAlignment;

    /**
     * A flag that can be used to temporarily disable the listener mechanism.
     */
    private boolean notify;

    /**
     * Creates a new title, using default attributes where necessary.
     */
    protected Title() {
        this(Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
    }

    /**
     * Creates a new title, using default attributes where necessary.
     * 
     * @param position
     *            the position of the title (<code>null</code> not permitted).
     * @param horizontalAlignment
     *            the horizontal alignment of the title (<code>null</code> not
     *            permitted).
     * @param verticalAlignment
     *            the vertical alignment of the title (<code>null</code> not
     *            permitted).
     */
    protected Title(RectangleEdge position,
            HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment) {

        this(position, horizontalAlignment, verticalAlignment,
                Title.DEFAULT_PADDING);

    }

    /**
     * Creates a new title.
     * 
     * @param position
     *            the position of the title (<code>null</code> not permitted).
     * @param horizontalAlignment
     *            the horizontal alignment of the title (LEFT, CENTER or RIGHT,
     *            <code>null</code> not permitted).
     * @param verticalAlignment
     *            the vertical alignment of the title (TOP, MIDDLE or BOTTOM,
     *            <code>null</code> not permitted).
     * @param padding
     *            the amount of space to leave around the outside of the title (
     *            <code>null</code> not permitted).
     */
    protected Title(RectangleEdge position,
            HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment, RectangleInsets padding) {

        // check arguments...
        if (position == null) {
            throw new IllegalArgumentException("Null 'position' argument.");
        }
        if (horizontalAlignment == null) {
            throw new IllegalArgumentException(
                    "Null 'horizontalAlignment' argument.");
        }

        if (verticalAlignment == null) {
            throw new IllegalArgumentException(
                    "Null 'verticalAlignment' argument.");
        }
        if (padding == null) {
            throw new IllegalArgumentException("Null 'spacer' argument.");
        }

        this.visible = true;
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        setPadding(padding);
        this.notify = true;

    }

    /**
     * Returns a flag that controls whether or not the title should be drawn.
     * The default value is <code>true</code>.
     * 
     * @return A boolean.
     * 
     * @see #setVisible(boolean)
     * 
     * @since JFreeChart 1.0.11
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Sets a flag that controls whether or not the title should be drawn, and
     * sends a {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param visible
     *            the new flag value.
     * 
     * @see #isVisible()
     * 
     * @since JFreeChart 1.0.11
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Returns the position of the title.
     * 
     * @return The title position (never <code>null</code>).
     */
    public RectangleEdge getPosition() {
        return this.position;
    }

    /**
     * Sets the position for the title and sends a {@link TitleChangeEvent} to
     * all registered listeners.
     * 
     * @param position
     *            the position (<code>null</code> not permitted).
     */
    public void setPosition(RectangleEdge position) {
        if (position == null) {
            throw new IllegalArgumentException("Null 'position' argument.");
        }
        if (this.position != position) {
            this.position = position;
        }
    }

    /**
     * Returns the horizontal alignment of the title.
     * 
     * @return The horizontal alignment (never <code>null</code>).
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    /**
     * Sets the horizontal alignment for the title and sends a
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param alignment
     *            the horizontal alignment (<code>null</code> not permitted).
     */
    public void setHorizontalAlignment(HorizontalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Null 'alignment' argument.");
        }
        if (this.horizontalAlignment != alignment) {
            this.horizontalAlignment = alignment;
        }
    }

    /**
     * Returns the vertical alignment of the title.
     * 
     * @return The vertical alignment (never <code>null</code>).
     */
    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }

    /**
     * Sets the vertical alignment for the title, and notifies any registered
     * listeners of the change.
     * 
     * @param alignment
     *            the new vertical alignment (TOP, MIDDLE or BOTTOM,
     *            <code>null</code> not permitted).
     */
    public void setVerticalAlignment(VerticalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Null 'alignment' argument.");
        }
        if (this.verticalAlignment != alignment) {
            this.verticalAlignment = alignment;
        }
    }

    /**
     * Returns the flag that indicates whether or not the notification mechanism
     * is enabled.
     * 
     * @return The flag.
     */
    public boolean getNotify() {
        return this.notify;
    }

    /**
     * Draws the title on a graphics device (such as the screen or a
     * printer).
     * 
     * @param canvas
     *            the graphics device.
     * @param area
     *            the area allocated for the title (subclasses should not draw
     *            outside this area).
     */
    public abstract void draw(Canvas canvas, RectShape area);
    /**
     * Tests an object for equality with this title.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Title)) {
            return false;
        }
        Title that = (Title) obj;
        if (this.visible != that.visible) {
            return false;
        }
        if (this.position != that.position) {
            return false;
        }
        if (this.horizontalAlignment != that.horizontalAlignment) {
            return false;
        }
        if (this.verticalAlignment != that.verticalAlignment) {
            return false;
        }
        if (this.notify != that.notify) {
            return false;
        }
        return super.equals(obj);
    }
    /**
     * Returns a hashcode for the title.
     *
     * @return The hashcode.
     */
    public int hashCode() {
        int result = 193;
        result = 37 * result + ObjectUtilities.hashCode(this.position);
        result = 37 * result
                + ObjectUtilities.hashCode(this.horizontalAlignment);
        result = 37 * result + ObjectUtilities.hashCode(this.verticalAlignment);
        return result;
    }
}
