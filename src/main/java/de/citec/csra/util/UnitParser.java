/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.util;

import java.util.List;
import org.openbase.bco.dal.remote.unit.UnitRemote;
import org.openbase.bco.dal.remote.unit.UnitRemoteFactoryImpl;
import org.openbase.bco.registry.unit.remote.UnitRegistryRemote;
import org.openbase.jul.exception.CouldNotPerformException;
import rst.domotic.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class UnitParser implements StringParser<UnitRemote> {

	@Override
	public UnitRemote getValue(String tgt) throws IllegalArgumentException {
		try {
			UnitRegistryRemote unitRegistry = Remotes.get().getUnitRegistry();
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
}
