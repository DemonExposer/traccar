/*
 * Copyright 2016 - 2020 Anton Tananaev (anton@traccar.org)
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
import org.traccar.Context;
import org.traccar.config.Keys;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CellTower {

	private String radioType;
	private Long cellId;
	private Integer locationAreaCode;
	private Integer mobileCountryCode;
	private Integer mobileNetworkCode;
	private Integer signalStrength;

	public static CellTower from(int mcc, int mnc, int lac, long cid) {
		CellTower cellTower = new CellTower();
		cellTower.setMobileCountryCode(mcc);
		cellTower.setMobileNetworkCode(mnc);
		cellTower.setLocationAreaCode(lac);
		cellTower.setCellId(cid);
		return cellTower;
	}

	public static CellTower from(int mcc, int mnc, int lac, long cid, int rssi) {
		CellTower cellTower = CellTower.from(mcc, mnc, lac, cid);
		cellTower.setSignalStrength(rssi);
		return cellTower;
	}

	public static CellTower fromLacCid(int lac, long cid) {
		return from(
				Context.getConfig().getInteger(Keys.GEOLOCATION_MCC),
				Context.getConfig().getInteger(Keys.GEOLOCATION_MCC), lac, cid);
	}

	public static CellTower fromCidLac(long cid, int lac) {
		return fromLacCid(lac, cid);
	}

	public String getRadioType() {
		return radioType;
	}

	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public Integer getLocationAreaCode() {
		return locationAreaCode;
	}

	public void setLocationAreaCode(Integer locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	public Integer getMobileCountryCode() {
		return mobileCountryCode;
	}

	public void setMobileCountryCode(Integer mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public Integer getMobileNetworkCode() {
		return mobileNetworkCode;
	}

	public void setMobileNetworkCode(Integer mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public void setOperator(long operator) {
		String operatorString = String.valueOf(operator);
		mobileCountryCode = Integer.parseInt(operatorString.substring(0, 3));
		mobileNetworkCode = Integer.parseInt(operatorString.substring(3));
	}

}
