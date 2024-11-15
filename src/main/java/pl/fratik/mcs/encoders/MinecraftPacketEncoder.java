/*
 * Copyright (c) 2023 fratik
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package pl.fratik.mcs.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pl.fratik.mcs.Main;
import pl.fratik.mcs.packets.ResponsePacket;

public class MinecraftPacketEncoder extends MessageToByteEncoder<ResponsePacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ResponsePacket msg, ByteBuf out) throws Exception {
        msg.encode(out, ctx.pipeline().get(Main.class).getProtVer());
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ResponsePacket msg, boolean preferDirect) throws Exception {
        return ctx.alloc().heapBuffer(msg.calculateLength(ctx.pipeline().get(Main.class).getProtVer()));
    }
}
