/*
 * Copyright 2016 - 2021 Anton Tananaev (anton@traccar.org)
 * Copyright 2016 Andrey Kunitsyn (andrey@traccar.org)
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.predicate.PeriodRule;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.component.CalendarComponent;
import org.traccar.storage.QueryIgnore;
import org.traccar.storage.StorageName;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

@StorageName("tc_calendars")
public class Calendar extends ExtendedModel {

	private String name;
	private byte[] data;
	private net.fortuna.ical4j.model.Calendar calendar;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data.clone();
	}

	public void setData(byte[] data) throws IOException, ParserException {
		CalendarBuilder builder = new CalendarBuilder();
		calendar = builder.build(new ByteArrayInputStream(data));
		this.data = data.clone();
	}

	@QueryIgnore
	@JsonIgnore
	public net.fortuna.ical4j.model.Calendar getCalendar() {
		return calendar;
	}

	public boolean checkMoment(Date date) {
		if (calendar != null) {
			Period period = new Period(new DateTime(date), Duration.ZERO);
			Filter<CalendarComponent> filter = new Filter<>(new PeriodRule<>(period));
			Collection<CalendarComponent> events = filter.filter(calendar.getComponents(CalendarComponent.VEVENT));
			return events != null && !events.isEmpty();
		}
		return false;
	}

}
