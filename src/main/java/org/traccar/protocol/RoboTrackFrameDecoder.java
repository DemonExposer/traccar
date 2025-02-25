/*
 * Copyright 2018 Anton Tananaev (anton@traccar.org)
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

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.traccar.BaseFrameDecoder;

public class RoboTrackFrameDecoder extends BaseFrameDecoder {

	private int messageLength(ByteBuf buf) {
		switch (buf.getUnsignedByte(buf.readerIndex())) {
			case RoboTrackProtocolDecoder.MSG_ID:
				return 69;
			case RoboTrackProtocolDecoder.MSG_ACK:
				return 3;
			case RoboTrackProtocolDecoder.MSG_GPS:
			case RoboTrackProtocolDecoder.MSG_GSM:
			case RoboTrackProtocolDecoder.MSG_IMAGE_START:
				return 24;
			case RoboTrackProtocolDecoder.MSG_IMAGE_DATA:
				return 8 + buf.getUnsignedShortLE(buf.readerIndex() + 1);
			case RoboTrackProtocolDecoder.MSG_IMAGE_END:
				return 6;
			default:
				return Integer.MAX_VALUE;
		}
	}

	@Override
	protected Object decode(
			ChannelHandlerContext ctx, Channel channel, ByteBuf buf) throws Exception {

		int length = messageLength(buf);

		if (buf.readableBytes() >= length) {
			return buf.readRetainedSlice(length);
		}

		return null;
	}

}
