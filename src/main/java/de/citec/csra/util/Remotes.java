/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dc.bco.dal.remote.unit.AmbientLightRemote;
import org.dc.bco.dal.remote.unit.DimmerRemote;
import org.dc.bco.registry.device.remote.DeviceRegistryRemote;
import org.dc.bco.registry.location.remote.LocationRegistryRemote;
import org.dc.jul.exception.CouldNotPerformException;
import org.dc.jul.exception.InitializationException;
import rst.homeautomation.unit.UnitConfigType;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class Remotes {

	private final Map<UnitConfigType.UnitConfig, AmbientLightRemote> lights = new HashMap<>();
	private final Map<UnitConfigType.UnitConfig, DimmerRemote> dimmers = new HashMap<>();
	private LocationRegistryRemote locations;
	private DeviceRegistryRemote devices;
	private final static Logger log = Logger.getLogger(Remotes.class.getName());

	private static Remotes instance;
	private Remotes(){}

	public static Remotes get(){
		if(instance == null){
			instance = new Remotes();
		}
		return instance;
	}

	public AmbientLightRemote getAmbientLight(UnitConfigType.UnitConfig u) throws InitializationException, InterruptedException, CouldNotPerformException {
		if (this.lights.containsKey(u)) {
			return this.lights.get(u);
		} else {
			log.log(Level.INFO, "initializing ambient light remote for unit ''{0}''", u.getLabel());
			AmbientLightRemote ambiremote = new AmbientLightRemote();
			ambiremote.init(u);
			ambiremote.activate();
			this.lights.put(u, ambiremote);
			return ambiremote;
		}
	}

	public DimmerRemote getDimmer(UnitConfigType.UnitConfig u) throws InitializationException, InterruptedException, CouldNotPerformException {
		if (this.dimmers.containsKey(u)) {
			return this.dimmers.get(u);
		} else {
			log.log(Level.INFO, "initializing dimmer remote for unit ''{0}''", u.getLabel());
			DimmerRemote ambiremote = new DimmerRemote();
			ambiremote.init(u);
			ambiremote.activate();
			this.dimmers.put(u, ambiremote);
			return ambiremote;
		}
	}

	public LocationRegistryRemote getLocations() throws InstantiationException, InitializationException, InterruptedException, CouldNotPerformException {
		if (this.locations == null) {
			this.locations = new LocationRegistryRemote();
			this.locations.init();
			this.locations.activate();
		}
		return this.locations;
	}

	public DeviceRegistryRemote getDevices() throws InstantiationException, InitializationException, InterruptedException, CouldNotPerformException {
		if (this.devices == null) {
			this.devices = new DeviceRegistryRemote();
			this.devices.init();
			this.devices.activate();
		}
		return this.devices;
	}
}
