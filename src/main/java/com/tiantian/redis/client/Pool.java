package com.tiantian.redis.client;

import com.tiantian.redis.client.handler.CommandHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Pool {

	public void init() {

		String host = "127.0.0.1";
		int port = 6379;
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new CommandHandler());
				}
			});

			ChannelFuture f = b.connect(host, port).sync(); // (5)

			f.channel().closeFuture().sync();

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}

	}
}
