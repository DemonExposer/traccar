/*
 * Copyright 2016 - 2019 Anton Tananaev (anton@traccar.org)
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

import java.util.ArrayList;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Network {

	private Integer homeMobileCountryCode;
	private Integer homeMobileNetworkCode;
	private String radioType = "gsm";
	private String carrier;
	private Boolean considerIp = false;
	private Collection<CellTower> cellTowers;
	private Collection<WifiAccessPoint> wifiAccessPoints;

	public Network() {
	}

	public Network(CellTower cellTower) {
		addCellTower(cellTower);
	}

	public Network(WifiAccessPoint wifiAccessPoint) {
		addWifiAccessPoint(wifiAccessPoint);
	}

	public Integer getHomeMobileCountryCode() {
		return homeMobileCountryCode;
	}

	public void setHomeMobileCountryCode(Integer homeMobileCountryCode) {
		this.homeMobileCountryCode = homeMobileCountryCode;
	}

	public Integer getHomeMobileNetworkCode() {
		return homeMobileNetworkCode;
	}

	public void setHomeMobileNetworkCode(Integer homeMobileNetworkCode) {
		this.homeMobileNetworkCode = homeMobileNetworkCode;
	}

	public String getRadioType() {
		return radioType;
	}

	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Boolean getConsiderIp() {
		return considerIp;
	}

	public void setConsiderIp(Boolean considerIp) {
		this.considerIp = considerIp;
	}

	public Collection<CellTower> getCellTowers() {
		return cellTowers;
	}

	public void setCellTowers(Collection<CellTower> cellTowers) {
		this.cellTowers = cellTowers;
	}

	public void addCellTower(CellTower cellTower) {
		if (cellTowers == null) {
			cellTowers = new ArrayList<>();
		}
		cellTowers.add(cellTower);
	}

	public Collection<WifiAccessPoint> getWifiAccessPoints() {
		return wifiAccessPoints;
	}

	public void setWifiAccessPoints(Collection<WifiAccessPoint> wifiAccessPoints) {
		this.wifiAccessPoints = wifiAccessPoints;
	}

	public void addWifiAccessPoint(WifiAccessPoint wifiAccessPoint) {
		if (wifiAccessPoints == null) {
			wifiAccessPoints = new ArrayList<>();
		}
		wifiAccessPoints.add(wifiAccessPoint);
	}

}
