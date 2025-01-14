/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WifiAccessPoint {

	private String macAddress;
	private Integer signalStrength;
	private Integer channel;

	public static WifiAccessPoint from(String macAddress, int signalStrength) {
		WifiAccessPoint wifiAccessPoint = new WifiAccessPoint();
		wifiAccessPoint.setMacAddress(macAddress);
		wifiAccessPoint.setSignalStrength(signalStrength);
		return wifiAccessPoint;
	}

	public static WifiAccessPoint from(String macAddress, int signalStrength, int channel) {
		WifiAccessPoint wifiAccessPoint = from(macAddress, signalStrength);
		wifiAccessPoint.setChannel(channel);
		return wifiAccessPoint;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

}
