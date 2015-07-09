package com.tiantian.redis.client.handler;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class CommandHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger.getLogger(CommandHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

	}

}