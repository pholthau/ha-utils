/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.init;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dc.bco.registry.location.remote.LocationRegistryRemote;
import org.dc.jul.exception.CouldNotPerformException;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class LocationRegistryRemoteInit implements Initializer<String, LocationRegistryRemote> {

	private final static Logger LOG = Logger.getLogger(LocationRegistryRemoteInit.class.getName());

	@Override
	public LocationRegistryRemote initialize(String key) {
		try {
			LOG.log(Level.INFO, "initializing location registry remote");
			LocationRegistryRemote remote = new LocationRegistryRemote();
			remote.init();
			remote.activate();
			return remote;
		} catch (InterruptedException | CouldNotPerformException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
 