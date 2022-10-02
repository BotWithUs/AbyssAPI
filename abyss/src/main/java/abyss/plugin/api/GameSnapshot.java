package abyss.plugin.api;

public class GameSnapshot {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final byte[] pixels;

    public GameSnapshot(int x, int y, int width, int height, byte[] pixels) {
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

    public byte[] getPixels() {
        return pixels;
    }
}
