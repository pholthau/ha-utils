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
	
	@Override
	public ColorableLightRemote initialize(UnitConfig key) {
		try {
			LOG.log(Level.INFO, "initializing ambient light remote for unit ''{0}''", key.getLabel());
			ColorableLightRemote ambiremote = new ColorableLightRemote();
			ambiremote.init(key);
			ambiremote.activate();
			return ambiremote;
		} catch (InterruptedException | CouldNotPerformException ex) {
			LOG.log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
