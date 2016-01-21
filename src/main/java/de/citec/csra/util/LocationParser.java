/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.util;

import org.dc.jul.exception.CouldNotPerformException;

/**
 *
 * @author Patrick Holthaus
 * (<a href=mailto:patrick.holthaus@uni-bielefeld.de>patrick.holthaus@uni-bielefeld.de</a>)
 */
public class LocationParser implements StringParser<String> {

	@Override
	public String getValue(String tgt) throws IllegalArgumentException {
		try {
			if (Remotes.get().getLocations().getLocationConfigsByLabel(tgt).size() > 0) {
				return tgt;
			} else {
				throw new IllegalArgumentException("location '" + tgt + "' not available.");
			}
		} catch (InstantiationException | InterruptedException | CouldNotPerformException | IllegalArgumentException ex) {
			throw new IllegalArgumentException("location registry not available.", ex);
		}
	}

	@Override
	public Class<String> getTargetClass() {
		return String.class;
	}
}
