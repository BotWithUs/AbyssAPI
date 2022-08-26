package kraken.plugin.api;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class GameSnapshot {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int[] pixels;

    public GameSnapshot(int x, int y, int width, int height, int[] pixels) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage toBufferedImage() {
        BufferedImage screenshot = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = screenshot.getGraphics();
        ByteBuffer buffer = ByteBuffer.allocate(pixels.length);
        for (int pixel : pixels) {
            buffer.put((byte) pixel);
        }
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                graphics.setColor(new Color((buffer.get() & 0xff), (buffer.get() & 0xff),
                        (buffer.get() & 0xff)));
                buffer.get();
                graphics.drawRect(w, height - h, 1, 1);
            }
        }
        return screenshot;
    }
}
