package io.github.ihelin.demo.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 一、使用 NIO 完成网络通信的三个核心：
 * <p>
 * 1. 通道（Channel）：负责连接
 * <p>
 * java.nio.channels.Channel 接口：
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2. 缓冲区（Buffer）：负责数据的存取
 * <p>
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 * @author iHelin
 * @since 2017/11/22 22:21
 */
public class NonBlockingNIOTest {

    private static final int port = 9898;

    /**
     * 客户端
     * 由于使用@Test无法键盘输入，故改为main方法
     *
     * @author iHelin
     * @since 2017/11/22 22:22
     */
    public static void main(String[] args) throws IOException {
        //1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
        //2. 切换为非阻塞模式
        socketChannel.configureBlocking(false);
        //3. 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4. 发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + str).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //5. 关闭通道
        socketChannel.close();
    }

    /**
     * 服务端
     *
     * @author iHelin
     * @since 2017/11/22 23:00
     */
    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. 切换为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(port));
        //4. 获取选择器
        Selector selector = Selector.open();
        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                //8. 获取“准备就绪”的是事件
                SelectionKey selectionKey = keyIterator.next();
                //9. 判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. 切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12. 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14. 读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                //15. 取消选择键 SelectionKey
                keyIterator.remove();
            }
        }
    }
}
