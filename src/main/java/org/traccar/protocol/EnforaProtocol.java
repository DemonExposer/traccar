/*
 * Copyright 2015 - 2019 Anton Tananaev (anton@traccar.org)
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
package org.traccar.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.traccar.BaseProtocol;
import org.traccar.PipelineBuilder;
import org.traccar.TrackerServer;
import org.traccar.model.Command;

public class EnforaProtocol extends BaseProtocol {

	public EnforaProtocol() {
		setSupportedDataCommands(
				Command.TYPE_CUSTOM,
				Command.TYPE_ENGINE_STOP,
				Command.TYPE_ENGINE_RESUME);
		addServer(new TrackerServer(false, getName()) {
			@Override
			protected void addProtocolHandlers(PipelineBuilder pipeline) {
				pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, -2, 2));
				pipeline.addLast(new EnforaProtocolEncoder(EnforaProtocol.this));
				pipeline.addLast(new EnforaProtocolDecoder(EnforaProtocol.this));
			}
		});
		addServer(new TrackerServer(true, getName()) {
			@Override
			protected void addProtocolHandlers(PipelineBuilder pipeline) {
				pipeline.addLast(new EnforaProtocolEncoder(EnforaProtocol.this));
				pipeline.addLast(new EnforaProtocolDecoder(EnforaProtocol.this));
			}
		});
	}

}
