package io.github.ihelin.demo.io.socket.nio.chatroom;

import java.io.IOException;

/**
 * @author iHelin
 * @since 2019-05-06 23:54
 */
public class BClient {

    public static void main(String[] args) throws IOException {
        new NioClient().start("BClient");
    }
}
