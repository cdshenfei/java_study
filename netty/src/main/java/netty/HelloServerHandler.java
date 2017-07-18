package netty;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

	/**
	 * 接收消息时触发
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
		ctx.writeAndFlush("Recevied youer message !\n");
	}

	/**
	 * 建立连接时触发
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端成功连接到服务端 : " + ctx.channel().remoteAddress() + " is active !");
		ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
		super.channelActive(ctx);
	}
	
}