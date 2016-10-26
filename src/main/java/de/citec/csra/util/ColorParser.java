/* 
 * Copyright (C) 2016 Bielefeld University, Patrick Holthaus
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
 */
package de.citec.csra.util;

import rst.vision.HSBColorType.HSBColor;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class ColorParser implements StringParser<HSBColor> {

	@Override
	public HSBColor getValue(String val) throws IllegalArgumentException {
		String[] hsv= val.split(",");
		if(hsv.length != 3){
			throw new IllegalArgumentException("Illegal HSB value: " + val);
		}
		
		HSBColor color = HSBColor.newBuilder().
				setHue(Double.valueOf(hsv[0])).
				setSaturation(Double.valueOf(hsv[1])).
				setBrightness(Double.valueOf(hsv[2])).build();
		
		return color;
	}

	@Override
	public Class<HSBColor> getTargetClass() {
		return HSBColor.class;
	}
}
