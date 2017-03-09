/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.parse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openbase.bco.dal.remote.unit.ColorableLightRemote;
import org.openbase.bco.dal.remote.unit.DimmerRemote;
import org.openbase.bco.registry.location.remote.LocationRegistryRemote;
import org.openbase.bco.registry.remote.Registries;
import org.openbase.bco.registry.unit.remote.UnitRegistryRemote;
import org.openbase.jul.exception.CouldNotPerformException;
import org.openbase.jul.exception.InitializationException;
import rst.domotic.unit.UnitConfigType.UnitConfig;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class Remotes {

	private final Map<UnitConfig, ColorableLightRemote> lights = new HashMap<>();
	private final Map<UnitConfig, DimmerRemote> dimmers = new HashMap<>();
	private LocationRegistryRemote locations;
	private UnitRegistryRemote unitRegistry;
	private final static Logger LOG = Logger.getLogger(Remotes.class.getName());

	private static Remotes instance;

	private Remotes() {
	}

	public static Remotes get() {
		if (instance == null) {
			instance = new Remotes();
		}
		return instance;
	}

	public ColorableLightRemote getColorableLight(UnitConfig u, long timeout) throws InitializationException, InterruptedException, CouldNotPerformException {
		if (this.lights.containsKey(u)) {
			return this.lights.get(u);
		} else {
			LOG.log(Level.FINE, "initializing ambient light remote for unit ''{0}''", u.getLabel());
			ColorableLightRemote remote = new ColorableLightRemote();
			remote.init(u);
			remote.activate(false);
			remote.waitForData(timeout, TimeUnit.MILLISECONDS);
			this.lights.put(u, remote);
			return remote;
		}
	}

	public DimmerRemote getDimmableLight(UnitConfig u, long timeout) throws InitializationException, InterruptedException, CouldNotPerformException {
		if (this.dimmers.containsKey(u)) {
			return this.dimmers.get(u);
		} else {
			LOG.log(Level.FINE, "initializing dimmer remote for unit ''{0}''", u.getLabel());
			DimmerRemote remote = new DimmerRemote();
			remote.init(u);
			remote.activate(false);
			remote.waitForData(timeout, TimeUnit.MILLISECONDS);
			this.dimmers.put(u, remote);
			return remote;
		}
	}

	public LocationRegistryRemote getLocationRegistry(long timeout) throws InstantiationException, InitializationException, InterruptedException, CouldNotPerformException {
		if (this.locations == null) {
			LOG.log(Level.FINE, "initializing location registry remote");
			this.locations = Registries.getLocationRegistry();
			this.locations.waitForData(timeout, TimeUnit.MILLISECONDS);
		}
		return this.locations;
	}

	public UnitRegistryRemote getUnitRegistry(long timeout) throws InstantiationException, InitializationException, InterruptedException, CouldNotPerformException {
		if (this.unitRegistry == null) {
			LOG.log(Level.FINE, "initializing device registry remote");
			this.unitRegistry = Registries.getUnitRegistry();
			this.unitRegistry.waitForData(timeout, TimeUnit.MILLISECONDS);
		}
		return this.unitRegistry;
	}
}
