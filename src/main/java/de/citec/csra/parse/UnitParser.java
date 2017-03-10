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
import java.util.List;
import org.openbase.bco.dal.remote.unit.UnitRemote;
import org.openbase.bco.dal.remote.unit.UnitRemoteFactoryImpl;
import org.openbase.bco.registry.unit.remote.UnitRegistryRemote;
import org.openbase.jul.exception.CouldNotPerformException;
import org.openbase.jul.exception.NotAvailableException;
import rst.domotic.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class UnitParser implements StringParser<UnitRemote> {

	private final long TIMEOUT = 500;
	
	@Override
	public UnitRemote getValue(String tgt) throws IllegalArgumentException {
		try {
			UnitRegistryRemote unitRegistry = Remotes.get().getUnitRegistry(TIMEOUT);
			List<UnitConfig> cfg = unitRegistry.getUnitConfigsByLabel(tgt);
			if(cfg.isEmpty()){
				throw new IllegalArgumentException("no unit with label'" + tgt + "'");
			}
			else {
				UnitConfig u = cfg.get(0);
                UnitRemote unitRemote = UnitRemoteFactoryImpl.getInstance().newInitializedInstance(u);
                unitRemote.waitForData();
				return unitRemote;
			}

		} catch (InstantiationException | InterruptedException | CouldNotPerformException ex) {
			throw new IllegalArgumentException("device registry not available.", ex);
		}
	}

	@Override
	public Class<UnitRemote> getTargetClass() {
		return UnitRemote.class;
	}

	@Override
	public String getString(UnitRemote obj) {
		try {
			return obj.getLabel();
		} catch (NotAvailableException ex) {
			throw new IllegalArgumentException("unit not available.", ex);
		}
	}
}
