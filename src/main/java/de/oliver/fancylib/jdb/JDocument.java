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
        String[] parts = key.split("\\.");

        Map<String, Object> current = data;
        for (int i = 0; i < parts.length; i++) {
            Object obj = current.get(parts[i]);
            if (obj == null) {
                return null;
            }

            if (i == parts.length - 1) {
                return obj;
            }

            if (!(obj instanceof Map)) {
                return null;
            }

            current = (Map<String, Object>) obj;
        }

        return null;
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
        Object obj = get(key);
        if (obj == null) {
            return new HashSet<>();
        }

        if (!isType(obj, Map.class)) {
            return new HashSet<>();
        }

        Map<String, Object> map = (Map<String, Object>) data.get(key);

        return map.keySet();
    }

    /**
     * Retrieves a string value from the document using the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the string value associated with the given key, or an empty string if the key is not found or the value is not a string
     */
    public String getString(String key) {
        Object obj = get(key);
        if (obj == null) {
            return "";
        }

        return (String) obj;
    }

    /**
     * Retrieves a boolean value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the boolean value associated with the given key, or false if the key is not found or the value is not a boolean
     */
    public boolean getBoolean(String key) {
        Object obj = get(key);
        if (obj == null) {
            return false;
        }

        if (!isType(obj, Boolean.class)) {
            return false;
        }

        return (boolean) obj;
    }

    /**
     * Retrieves a byte value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the byte value associated with the given key, or 0 if the key is not found or the value is not a byte
     */
    public byte getByte(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Byte.class)) {
            return 0;
        }

        return (byte) obj;
    }

    /**
     * Retrieves a short value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the short value associated with the given key, or 0 if the key is not found or the value is not a short
     */
    public short getShort(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Short.class)) {
            return 0;
        }

        return (short) obj;
    }

    /**
     * Retrieves an integer value associated with the given key from the document.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the integer value associated with the given key, or 0 if the key is not found or the value is not an integer
     */
    public int getInt(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Integer.class)) {
            return 0;
        }

        return (int) obj;
    }

    /**
     * Retrieves a long value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the long value associated with the given key, or 0 if the key is not found or the value is not a long
     */
    public long getLong(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Long.class)) {
            return 0;
        }

        return (long) obj;
    }

    /**
     * Retrieves a float value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the float value associated with the given key, or 0 if the key is not found
     * or the value is not a float
     */
    public float getFloat(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Float.class)) {
            return 0;
        }

        return (float) obj;
    }

    /**
     * Retrieves a double value associated with the given key.
     *
     * @param key the dot-separated key used to locate the value in the document (e.g. "foo.bar.baz")
     * @return the double value associated with the given key, or 0 if the key is not found or the value is not a double
     */
    public double getDouble(String key) {
        Object obj = get(key);
        if (obj == null) {
            return 0;
        }

        if (!isType(obj, Double.class)) {
            return 0;
        }

        return (double) obj;
    }

    private boolean isType(@NotNull Object obj, Class<?> clazz) {
        return clazz.isInstance(obj);
    }
}