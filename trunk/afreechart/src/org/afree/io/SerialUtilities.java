/* ========================================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ========================================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * --------------------
 * SerialUtilities.java
 * --------------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arik Levin;
 *                   Sato Yoshiaki (for Icom Systech Co., Ltd);
 *                   Niwano Masayoshi;
 *
 *
 * Changes
 * -------
 * 25-Mar-2003 : Version 1 (DG);
 * 18-Sep-2003 : Added capability to serialize GradientPaint (DG);
 * 26-Apr-2004 : Added read/writePointF() methods (DG);
 * 22-Feb-2005 : Added support for ArcShape - see patch 1147035 by Arik Levin (DG);
 * 29-Jul-2005 : Added support for AttributedString (DG);
 *
 * ------------- AFREECHART 0.0.1 ---------------------------------------------
 * 19-Nov-2010 : port JCommon 1.0.16 to Android as "AFreeChart"
 */

package org.afree.io;


//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.GradientPaint;
//import java.awt.Paint;
//import java.awt.Shape;
//import java.awt.Stroke;
//import java.awt.geom.ArcShape;
//import java.awt.geom.OvalShape;
//import java.awt.geom.PathShape;
//import java.awt.geom.LineShape;
//import java.awt.geom.PathIterator;
//import java.awt.geom.PointF;
//import java.awt.geom.RectShape;
//import java.awt.Color;
//import java.awt.GradientPaint;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.afree.graphics.PaintType;

import android.graphics.Paint;

/**
 * A class containing useful utility methods relating to serialization.
 *
 * @author David Gilbert
 */
public class SerialUtilities {

    /**
     * Private constructor prevents object creation.
     */
    private SerialUtilities() {
    }


    /**
     * Serialises a <code>Paint</code> object.
     *
     * @param paint  the paint object (<code>null</code> permitted).
     * @param stream  the output stream (<code>null</code> not permitted).
     *
     * @throws IOException if there is an I/O error.
     */
    public static void writePaintType(final PaintType paint,
                                  final ObjectOutputStream stream)
        throws IOException {

        if (stream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        if (paint != null) {
            stream.writeBoolean(false);
            stream.writeObject(paint.getClass());
            if (paint instanceof Serializable) {
                stream.writeObject(paint);
            }
//            else if (paint instanceof GradientPaint) {
//                final GradientPaint gp = (GradientPaint) paint;
//                stream.writeFloat((float) gp.getPoint1().getX());
//                stream.writeFloat((float) gp.getPoint1().getY());
//                stream.writeObject(gp.getColor1());
//                stream.writeFloat((float) gp.getPoint2().getX());
//                stream.writeFloat((float) gp.getPoint2().getY());
//                stream.writeObject(gp.getColor2());
//                stream.writeBoolean(gp.isCyclic());
//            }
        }
        else {
            stream.writeBoolean(true);
        }

    }

    /**
     * Reads a <code>Paint</code> object that has been serialised by the
     * {@link SerialUtilities#writePaintType(Paint, ObjectOutputStream)} method.
     *
     * @param stream  the input stream (<code>null</code> not permitted).
     *
     * @return The paint object (possibly <code>null</code>).
     *
     * @throws IOException  if there is an I/O problem.
     * @throws ClassNotFoundException  if there is a problem loading a class.
     */
    public static PaintType readPaintType(final ObjectInputStream stream)
        throws IOException, ClassNotFoundException {

        if (stream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        PaintType result = null;
        final boolean isNull = stream.readBoolean();
        if (!isNull) {
            final Class c = (Class) stream.readObject();
//            if (isSerializable(c)) {
//                result = (Paint) stream.readObject();
//            }
//            else if (c.equals(GradientPaint.class)) {
//                final float x1 = stream.readFloat();
//                final float y1 = stream.readFloat();
//                final Color c1 = (Color) stream.readObject();
//                final float x2 = stream.readFloat();
//                final float y2 = stream.readFloat();
//                final Color c2 = (Color) stream.readObject();
//                final boolean isCyclic = stream.readBoolean();
//                result = new GradientPaint(x1, y1, c1, x2, y2, c2, isCyclic);
//            }
        }
        return result;

    }


}

