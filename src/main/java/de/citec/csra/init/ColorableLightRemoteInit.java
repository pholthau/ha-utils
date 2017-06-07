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
package de.citec.csra.init;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openbase.bco.dal.remote.unit.ColorableLightRemote;
import org.openbase.jul.exception.CouldNotPerformException;
import rst.domotic.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class ColorableLightRemoteInit implements Initializer<UnitConfig, ColorableLightRemote> {

	private final static Logger LOG = Logger.getLogger(ColorableLightRemoteInit.class.getName());
	
	@Deprecated
	@Override
	public ColorableLightRemote initialize(UnitConfig key) {
		try {
			LOG.log(Level.INFO, "initializing ambient light remote for unit ''{0}''", key.getLabel());
			ColorableLightRemote ambiremote = new ColorableLightRemote();
			ambiremote.init(key);
			ambiremote.activate(true);
			return ambiremote;
		} catch (InterruptedException | CouldNotPerformException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
