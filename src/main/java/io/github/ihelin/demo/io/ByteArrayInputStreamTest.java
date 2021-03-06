package io.github.ihelin.demo.io;

import io.github.ihelin.demo.utils.IOSupport;

import java.io.ByteArrayInputStream;

/**
 * @author iHelin
 * @since 2019-01-30 15:31
 */
public class ByteArrayInputStreamTest {

    private static final int LENGTH = 5;


    public static void main(String[] args) {
        // 创建ByteArrayInputStream字节流，内容是ArrayLetters数组
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOSupport.ARRAY_LETTERS);
        // 从字节流中读取5个字节
        for (int i = 0; i < LENGTH; i++) {
            // 若能继续读取下一个字节，则读取下一个字节
            if (byteArrayInputStream.available() >= 0) {
                // 读取“字节流的下一个字节”
                int tmp = byteArrayInputStream.read();
                System.out.printf("%d : 0x%s\n", i, Integer.toHexString(tmp));
            }
        }
        // 若“该字节流”不支持标记功能，则直接退出
        if (!byteArrayInputStream.markSupported()) {
            System.out.println("make not supported!");
            return;
        }

        // 标记“字节流中下一个被读取的位置”。即--标记“0x66”，因为因为前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”
        // (01), ByteArrayInputStream类的mark(0)函数中的“参数0”是没有实际意义的。
        // (02), mark()与reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
        byteArrayInputStream.mark(0);

        // 跳过5个字节。跳过5个字节后，字节流中下一个被读取的值应该是“0x6B”。
        long actual = byteArrayInputStream.skip(5);
        System.out.println("实际跳过的字节数为：" + actual);
        // 从字节流中读取5个数据。即读取“0x6B, 0x6C, 0x6D, 0x6E, 0x6F”
        byte[] buf = new byte[LENGTH];
        byteArrayInputStream.read(buf, 0, LENGTH);
        // 将buf转换为String字符串。“0x6B, 0x6C, 0x6D, 0x6E, 0x6F”对应字符是“klmno”
        String str1 = new String(buf);
        System.out.printf("str1 = %s\n", str1);

        // 重置“字节流”：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即0x66。
        byteArrayInputStream.reset();
        // 从“重置后的字节流”中读取5个字节到buf中。即读取“0x66, 0x67, 0x68, 0x69, 0x6A”
        byteArrayInputStream.read(buf, 0, LENGTH);
        // 将buf转换为String字符串。“0x66, 0x67, 0x68, 0x69, 0x6A”对应字符是“fghij”
        String str2 = new String(buf);
        System.out.printf("str2 = %s\n", str2);
    }
}
