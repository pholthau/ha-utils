/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.init;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dc.bco.dal.remote.unit.AmbientLightRemote;
import org.dc.jul.exception.CouldNotPerformException;
import rst.homeautomation.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class AmbientLightRemoteInit implements Initializer<UnitConfig, AmbientLightRemote> {

	private final static Logger LOG = Logger.getLogger(AmbientLightRemoteInit.class.getName());
	
	@Override
	public AmbientLightRemote initialize(UnitConfig key) {
		try {
			LOG.log(Level.INFO, "initializing ambient light remote for unit ''{0}''", key.getLabel());
			AmbientLightRemote ambiremote = new AmbientLightRemote();
			ambiremote.init(key);
			ambiremote.activate();
			return ambiremote;
		} catch (InterruptedException | CouldNotPerformException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
