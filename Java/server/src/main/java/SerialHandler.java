import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SerialHandler extends ChannelInboundHandlerAdapter {

    private static ConcurrentLinkedDeque<SocketChannel> channels = new ConcurrentLinkedDeque<>();
    private String name;
    private List<File> serverFileList = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        name = "Alex";
        System.out.println("Client: " + name + " connected!");
        channels.add((SocketChannel) ctx.channel());

        File dir = new File(NettyServer.serverPath);
        if (!dir.exists()) {
            throw new RuntimeException("directory resource not exists on client");
        }
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            serverFileList.add(file);
        }

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof File) {
            System.out.println("Upload File");
            File file = new File(NettyServer.serverPath + "/" + ((File) msg).getName());
            file.createNewFile();

        } else {
            System.out.println(msg);
            for (SocketChannel channel : channels) {
                // echo
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                channel.writeAndFlush(format.format(new Date()) + "-[" + name + "]: " + msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // disconnect
        System.out.println("client: " + name + " leave");
        channels.remove((SocketChannel) ctx.channel());
    }
}
