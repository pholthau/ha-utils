/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.init;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.openbase.bco.dal.remote.unit.DimmerRemote;
import org.openbase.jul.exception.CouldNotPerformException;
import rst.domotic.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class DimmerRemoteInit implements Initializer<UnitConfig, DimmerRemote> {

	private final static Logger LOG = Logger.getLogger(DimmerRemoteInit.class.getName());
	
	@Override
	public DimmerRemote initialize(UnitConfig key) {
		try {
			LOG.log(Level.INFO, "initializing dimmer remote for unit ''{0}''", key.getLabel());
			DimmerRemote dimmerremote = new DimmerRemote();
			dimmerremote.init(key);
			dimmerremote.activate();
			return dimmerremote;
		} catch (InterruptedException | CouldNotPerformException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
