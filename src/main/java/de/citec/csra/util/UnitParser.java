/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.util;

import de.citec.csra.util.Remotes;
import de.citec.csra.util.StringParser;
import de.citec.dal.remote.unit.DALRemoteService;
import de.citec.dal.remote.unit.UnitRemoteFactory;
import de.citec.dm.remote.DeviceRegistryRemote;
import de.citec.jul.exception.CouldNotPerformException;
import java.util.List;
import rst.homeautomation.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class UnitParser implements StringParser<DALRemoteService> {

	@Override
	public DALRemoteService getValue(String tgt) throws IllegalArgumentException {
		try {
			DeviceRegistryRemote dev = Remotes.get().getDevices();
			List<UnitConfig> cfg = dev.getUnitConfigsByLabel(tgt);
			if(cfg.isEmpty()){
				throw new IllegalArgumentException("no unit with label'" + tgt + "'");
			}
			else {
				UnitConfig u = cfg.get(0);
				return UnitRemoteFactory.getInstance().createAndInitUnitRemote(u);
			}
			
		} catch (InstantiationException | InterruptedException | CouldNotPerformException ex) {
			throw new IllegalArgumentException("device registry not available.", ex);
		}
	}
}
