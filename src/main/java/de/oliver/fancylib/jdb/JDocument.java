package de.oliver.fancylib.jdb;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a document that holds a map of key-value pairs with support for nested keys.
 */
public class JDocument {
    private final @NotNull Map<String, Object> data;

    public JDocument(@NotNull Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Retrieves a value from the document using the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the value associated with the given key, or null if the key is not found
     */
    public Object get(String key) {
        return getValue(key, Object.class);
    }

    /**
     * Checks if the document contains a value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return true if the given key exists in the document, otherwise false
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     * Retrieves the set of keys from a map value associated with a given key in the document.
     *
     * @param key the dot-separated key used to locate the map value in the document (e.g. "foo.bar.baz")
     * @return a set of keys from the map associated with the given key, or an empty set if the key
     * is not found or the value is not a map
     */
    public Set<String> getKeys(String key) {
        Map<String, Object> map = (Map<String, Object>) getValue(key, Map.class);
        return map != null ? map.keySet() : new HashSet<>();
    }

    /**
     * Retrieves a string value from the document using the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the string value associated with the given key, or an empty string if the key is not found or the value is not a string
     */
    public String getString(String key) {
        return (String) getValueOrDefault(key, String.class, "");
    }

    /**
     * Retrieves a boolean value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the boolean value associated with the given key, or false if the key is not found or the value is not a boolean
     */
    public boolean getBoolean(String key) {
        return (boolean) getValueOrDefault(key, Boolean.class, false);
    }

    /**
     * Retrieves a byte value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the byte value associated with the given key, or 0 if the key is not found or the value is not a byte
     */
    public byte getByte(String key) {
        return (byte) getValueOrDefault(key, Byte.class, (byte) 0);
    }

    /**
     * Retrieves a short value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the short value associated with the given key, or 0 if the key is not found or the value is not a short
     */
    public short getShort(String key) {
        return (short) getValueOrDefault(key, Short.class, (short) 0);
    }

    /**
     * Retrieves an integer value associated with the given key from the document.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the integer value associated with the given key, or 0 if the key is not found or the value is not an integer
     */
    public int getInt(String key) {
        return (int) getValueOrDefault(key, Integer.class, 0);
    }

    /**
     * Retrieves a long value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the long value associated with the given key, or 0 if the key is not found or the value is not a long
     */
    public long getLong(String key) {
        return (long) getValueOrDefault(key, Long.class, 0L);
    }

    /**
     * Retrieves a float value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the float value associated with the given key, or 0 if the key is not found or the value is not a float
     */
    public float getFloat(String key) {
        return (float) getValueOrDefault(key, Float.class, 0f);
    }

    /**
     * Retrieves a double value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the double value associated with the given key, or 0 if the key is not found or the value is not a double
     */
    public double getDouble(String key) {
        return (double) getValueOrDefault(key, Double.class, 0d);
    }

    private Object getValue(String key, Class<?> clazz) {
        String[] parts = key.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < parts.length; i++) {
            Object value = current.get(parts[i]);
            if (value == null || (i < parts.length - 1 && !(value instanceof Map))) {
                return null;
            }
            if (i == parts.length - 1) {
                return clazz.isInstance(value) ? value : null;
            }
            current = (Map<String, Object>) value;
        }
        return null;
    }

    private <T> T getValueOrDefault(String key, Class<T> clazz, T defaultValue) {
        T value = (T) getValue(key, clazz);
        return value != null ? value : defaultValue;
    }
}