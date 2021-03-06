/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * ----------------
 * DomainOrder.java
 * ----------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Jul-2004 : Version 1 (DG);
 *
 */

package org.afree.data;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate sorting order if any (ascending, descending or none).
 */
public final class DomainOrder implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 4902774943512072627L;

    /** No order. */
    public static final DomainOrder NONE = new DomainOrder("DomainOrder.NONE");

    /** Ascending order. */
    public static final DomainOrder ASCENDING = new DomainOrder(
            "DomainOrder.ASCENDING");

    /** Descending order. */
    public static final DomainOrder DESCENDING = new DomainOrder(
            "DomainOrder.DESCENDING");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     * 
     * @param name
     *            the name.
     */
    private DomainOrder(String name) {
        this.name = name;
    }

    /**
     * Returns a string representing the object.
     * 
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     * 
     * @param obj
     *            the other object.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DomainOrder)) {
            return false;
        }
        DomainOrder that = (DomainOrder) obj;
        if (!this.name.equals(that.toString())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     * 
     * @return The hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException
     *             if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(DomainOrder.ASCENDING)) {
            return DomainOrder.ASCENDING;
        } else if (this.equals(DomainOrder.DESCENDING)) {
            return DomainOrder.DESCENDING;
        } else if (this.equals(DomainOrder.NONE)) {
            return DomainOrder.NONE;
        }
        return null;
    }

}
