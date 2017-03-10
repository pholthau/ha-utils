/* 
 * Copyright (C) 2017 Patrick Holthaus
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
package de.citec.csra.parse;

import de.citec.csra.init.Remotes;
import de.citec.csra.rst.parse.StringParser;
import org.openbase.jul.exception.CouldNotPerformException;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class LocationParser implements StringParser<String> {
	
	private final long TIMEOUT = 500;

	@Override
	public String getValue(String tgt) throws IllegalArgumentException {
		System.out.println(tgt);
		try {
			if (Remotes.get().getLocationRegistry(TIMEOUT).getLocationConfigsByLabel(tgt).size() > 0) {
				return tgt;
			} else {
				throw new IllegalArgumentException("location '" + tgt + "' not available.");
			}
		} catch (InstantiationException | InterruptedException | CouldNotPerformException | IllegalArgumentException ex) {
			throw new IllegalArgumentException("location registry not available.", ex);
		}
	}

	@Override
	public Class<String> getTargetClass() {
		return String.class;
	}
	
	@Override
	public String getString(String obj) {
		return getValue(obj);
	}
}
