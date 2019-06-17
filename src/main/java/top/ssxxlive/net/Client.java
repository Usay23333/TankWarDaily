package top.ssxxlive.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	private Channel channel = null;
	
	public Client() {
		
	}
	
	public void connect(int nThreads) {
		EventLoopGroup group = new NioEventLoopGroup(nThreads);
		Bootstrap bootstrap = new Bootstrap();
		
		ChannelFuture channelFuture = bootstrap.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline()
						.addLast(new TankJoinMsgDecoder())
						.addLast(new TankJoinMsgEncoder())
						.addLast(new ClientChannelHandler());
					}
				})
				.connect("localhost", 8999)
				;
		
		channelFuture.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()) {
					System.out.println("Connect success!");
					channel = channelFuture.channel();
				} else {
					System.out.println("Connect failed!");
				}
			}
			
		});
		
		try {
			channelFuture.sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void sendMsg(String s) {
		channel.writeAndFlush(Unpooled.copiedBuffer(s.getBytes()));
	}
	
	public void closeConnect() {
		sendMsg("bye-bye");
		channel.close();
	}
	
	public static void main(String[] args) {
		new Client().connect(1);
	}
}

class ClientChannelHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//ctx.writeAndFlush(Unpooled.copiedBuffer("hello".getBytes()));
		//ctx.writeAndFlush(new TankJoinMsg(345, 2));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//ByteBuf buf = (ByteBuf)msg;
		//byte[] bys = new byte[buf.readableBytes()];
		//buf.getBytes(buf.readerIndex(), bys);
		//System.out.println(new String(bys));
		System.out.println((TankJoinMsg)msg);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
}
