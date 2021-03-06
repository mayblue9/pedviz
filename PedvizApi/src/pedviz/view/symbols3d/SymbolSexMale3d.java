/* Copyright � 2007 by Christian Fuchsberger and Lukas Forer info@pedvizapi.org.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License <http://www.pedvizapi.org/gpl.txt>
 * for more details. 
 */

package pedviz.view.symbols3d;

import java.awt.Color;

import javax.media.j3d.GeometryArray;
import javax.vecmath.Color4f;
import javax.vecmath.Point3f;

/**
 * Draws the symbol for a male individual.
 * 
 * @author lukas forer
 * 
 */
public class SymbolSexMale3d extends Symbol3D {

    public SymbolSexMale3d() {
	super(0);
    }

    public SymbolSexMale3d(int style) {
	super(style);
    }

    @Override
    public GeometryArray[] createShape(Point3f p, float size, Color fill) {

	switch (style) {
	case STYLE_TURM:
	    GeometryArray array = ShapeCreator3D.createBox(p.x, p.y, p.z, size);
	    Color4f color = new Color4f(fill);
	    array.setColors(0, ShapeCreator3D.getColorArray(color, array
		    .getVertexCount()));
	    GeometryArray[] result = new GeometryArray[5];
	    result[0] = array;
	    int i = 1;
	    int c = 1;
	    if (colors != null) {
		for (Color subColor : colors) {
		    if (subColor != null) {
			result[i] = ShapeCreator3D.createBox(p.x, p.y + size
				* c, p.z, size);
			c++;
			Color4f mcolor = new Color4f(subColor);
			result[i].setColors(0, ShapeCreator3D.getColorArray(
				mcolor, result[i].getVertexCount()));
		    } else {
			result[i] = null;
		    }
		    i++;
		}
	    }
	    return result;
	case STYLE_NORMAL:
	default:

	    if (colors == null || (colors[0] == null && colors[1] == null)) {
		GeometryArray full = ShapeCreator3D.createBox(p.x, p.y, p.z,
			size);
		Color4f fullcolor = new Color4f(fill);
		full.setColors(0, ShapeCreator3D.getColorArray(fullcolor, full
			.getVertexCount()));
		return new GeometryArray[] { full };
	    } else {
		float x = p.x + (size / 4f), y = p.y;
		GeometryArray[] halfs = new GeometryArray[2];
		for (int j = 0; j < 2; j++) {
		    if (j == 1)
			x = p.x - (size / 4f);
		    GeometryArray half = ShapeCreator3D.createBox(x, y, p.z,
			    size / 2f, size, size);
		    Color4f halfcolor = colors[j] != null ? new Color4f(
			    colors[j]) : new Color4f(fill);
		    half.setColors(0, ShapeCreator3D.getColorArray(halfcolor,
			    half.getVertexCount()));
		    halfs[j] = half;
		}
		return halfs;
	    }

	}
    }

    @Override
    public int getPriority() {
	return 0;
    }

}
