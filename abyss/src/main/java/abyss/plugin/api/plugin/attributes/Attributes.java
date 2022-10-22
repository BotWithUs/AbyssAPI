package abyss.plugin.api.plugin.attributes;

import abyss.plugin.api.Debug;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Attributes implements Flushable {

    private final Map<String, Object> attributes;

    private final Map<String, BiConsumer<?, ?>> listeners;

    private final ByteArrayOutputStream stream;
    private final DataOutputStream data;

    public Attributes() {
        this.attributes = new HashMap<>();
        this.listeners = new HashMap<>();
        this.stream = new ByteArrayOutputStream();
        this.data = new DataOutputStream(this.stream);
    }
    
    public void onIntChanged(String key, BiConsumer<Integer, Integer> consumer) {
        this.listeners.put('I' + key, consumer);
    }

    public boolean hasBoolean(String key) {
        return attributes.containsKey('Z' + key);
    }

    public boolean hasInt(String key) {
        return attributes.containsKey('I' + key);
    }

    public boolean hasLong(String key) {
        return attributes.containsKey('J' + key);
    }

    public boolean hasFloat(String key) {
        return attributes.containsKey('F' + key);
    }

    public boolean hasDouble(String key) {
        return attributes.containsKey('D' + key);
    }

    public boolean hasString(String key) {
        return attributes.containsKey('S' + key);
    }

    public boolean getBoolean(String key) {
        if (key == null) return false;
        String k = "Z" + key;
        if (!this.attributes.containsKey(k)) {
            return false;
        }
        try {
            return (boolean) this.attributes.get(k);
        } catch (Exception e) {
            Debug.log("Failed to get boolean attribute " + k);
            return false;
        }
    }

    public void setBoolean(String key, boolean value) {
        if (key == null) return;
        Object oldValue = this.attributes.put("Z" + key, value);
        BiConsumer<Boolean, Boolean> consumer = (BiConsumer<Boolean, Boolean>) listeners.get('Z' + key);
        if(consumer != null) {
            consumer.accept(oldValue != null && (boolean) oldValue, value);
        }
    }

    public int getInt(String key) {
        if (key == null) return -1;
        String k = "I" + key;
        if (!this.attributes.containsKey(k)) {
            return -1;
        }
        try {
            return (int) this.attributes.get(k);
        } catch (Exception e) {
            Debug.log("Failed to get integer attribute " + k);
            return -1;
        }
    }

    public void setInt(String key, int value) {
        if (key == null) return;
        Object oldValue = this.attributes.put("I" + key, value);
        BiConsumer<Integer, Integer> consumer = (BiConsumer<Integer, Integer>) listeners.get('I' + key);
        if(consumer != null) {
            consumer.accept(oldValue == null ? 0 : (int) oldValue, value);
        }
    }

    public long getLong(String key) {
        if (key == null) return 0L;
        String k = "J" + key;
        if (!this.attributes.containsKey(k)) {
            return -1L;
        }
        try {
            return (long) this.attributes.get(k);
        } catch (Exception e) {
            Debug.log("Failed to get long attribute " + k);
            return -1L;
        }
    }

    public void setLong(String key, long value) {
        if (key == null) return;
        Object oldValue = this.attributes.put("J" + key, value);
        BiConsumer<Long, Long> consumer = (BiConsumer<Long, Long>) listeners.get('J' + key);
        if(consumer != null) {
            consumer.accept(oldValue == null ? 0L : (long) oldValue, value);
        }
    }

    public float getFloat(String key) {
        if (key == null) return 0.0f;
        String k = 'F' + key;
        if (!this.attributes.containsKey(k)) {
            return 0.0f;
        }
        try {
            return (float) this.attributes.get(k);
        } catch (Exception e) {
            Debug.log("Failed to get long attribute " + k);
            return 0.0f;
        }
    }

    public void setFloat(String key, float value) {
        if (key == null) return;
        Object oldValue = this.attributes.put("F" + key, value);
        BiConsumer<Float, Float> consumer = (BiConsumer<Float, Float>) listeners.get('F' + key);
        if(consumer != null) {
            consumer.accept(oldValue == null ? 0.0f : (float) oldValue, value);
        }
    }

    public double getDouble(String key) {
        if (key == null) return 0.0;
        String k = 'D' + key;
        if (!this.attributes.containsKey(k)) {
            return 0.0;
        }
        try {
            return (double) this.attributes.get(k);
        } catch (Exception e) {
            Debug.log("Failed to get long attribute " + k);
            return 0.0;
        }
    }

    public void setDouble(String key, double value) {
        if (key == null) return;
        Object oldValue = this.attributes.put("D" + key, value);
        BiConsumer<Double, Double> consumer = (BiConsumer<Double, Double>) listeners.get('D' + key);
        if(consumer != null) {
            consumer.accept(oldValue == null ? 0.0 : (double) oldValue, value);
        }
    }

    public String getString(String key) {
        if (key == null) return "";
        String k = 'S' + key;
        if(!hasString(key)) {
            return "";
        }
        return (String) this.attributes.get(k);
    }

    public void setString(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        Object oldValue = this.attributes.put("S" + key, value);
        BiConsumer<String, String> consumer = (BiConsumer<String, String>) listeners.get('S' + key);
        if(consumer != null) {
            consumer.accept(oldValue == null ? "" : (String) oldValue, value);
        }
    }

    public void write(OutputStream out) throws IOException {
        out.write(stream.toByteArray(), 0, stream.size());
        stream.reset();
    }

    public void flushAndWrite(OutputStream out) {
        try {
            flush();
            write(out);
        } catch (IOException e) {
            Debug.printStackTrace("Failed to flush and write attributes map", e);
        }
    }

    public void read(InputStream input) throws IOException {
        if(input.available() < 2) {
            return;
        }
        DataInputStream stream;
        if (!(input instanceof DataInputStream)) {
            stream = new DataInputStream(input);
        } else {
            stream = (DataInputStream) input;
        }
        int size = stream.readUnsignedShort();
        for (int i = 0; i < size; i++) {
            char type = stream.readChar();
            String key = stream.readUTF();
            switch (type) {
                case 'Z' -> this.attributes.put(type + key, stream.readBoolean());
                case 'I' -> this.attributes.put(type + key, stream.readInt());
                case 'J' -> this.attributes.put(type + key, stream.readLong());
                case 'F' -> this.attributes.put(type + key, stream.readFloat());
                case 'D' -> this.attributes.put(type + key, stream.readDouble());
                case 'S' -> this.attributes.put(type + key, stream.readUTF());
            }
        }
    }

    @Override
    public void flush() throws IOException {
        stream.reset();
        data.writeShort(this.attributes.size());
        for (Map.Entry<String, Object> entry : this.attributes.entrySet()) {
            char type = entry.getKey().toCharArray()[0];
            String key = entry.getKey().substring(1);
            data.writeChar(type);
            data.writeUTF(key);
            switch (type) {
                case 'Z' -> data.writeBoolean((boolean) entry.getValue());
                case 'I' -> data.writeInt((int) entry.getValue());
                case 'J' -> data.writeLong((long) entry.getValue());
                case 'F' -> data.writeFloat((float) entry.getValue());
                case 'D' -> data.writeDouble((double) entry.getValue());
                case 'S' -> data.writeUTF((String) entry.getValue());
            }
        }
    }

    public ByteArrayOutputStream getStream() {
        return stream;
    }

    public byte[] toByteArray() {
        return stream.toByteArray();
    }
}