package de.oliver.fancylib.jdb;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JDocumentTest {

    /**
     * The JDocumentTest class contains unit tests for the JDocument class.
     * The get method in the JDocument class is being tested here.
     */

    @Test
    public void testGet_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        JDocument jDocument = new JDocument(data);

        Object result = jDocument.get("key1");

        assertNotNull(result);
        assertEquals("value1", result.toString());
    }

    @Test
    public void testGet_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        Object result = jDocument.get("key1");

        assertNull(result);
    }

    @Test
    public void testGet_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        Object result = jDocument.get("key1.key2");

        assertNotNull(result);
        assertEquals("value2", result.toString());
    }

    @Test
    public void testGet_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        Object result = jDocument.get("key1.key3");

        assertNull(result);
    }

    /**
     * The contains method in the JDocument class is being tested here.
     * It checks whether a given key is present in the JDocument's data or not.
     */

    @Test
    public void testContains_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.contains("key1");

        assertTrue(result);
    }

    @Test
    public void testContains_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        boolean result = jDocument.contains("key1");

        assertFalse(result);
    }

    @Test
    public void testContains_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.contains("key1.key2");

        assertTrue(result);
    }

    @Test
    public void testContains_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.contains("key1.key3");

        assertFalse(result);
    }

    /**
     * The getKeys method in the JDocument class is being tested here.
     * It retrieves the keys of the nested Map present within the data.
     */

    @Test
    public void testGetKeys_Success_SingleKey() {
        Map<String, Object> innerData = new HashMap<>();
        innerData.put("innerKey1", "value1");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", innerData);

        JDocument jDocument = new JDocument(data);

        Set<String> keys = jDocument.getKeys("key1");

        assertNotNull(keys);
        assertEquals(1, keys.size());
        assertTrue(keys.contains("innerKey1"));
    }

    @Test
    public void testGetKeys_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        Set<String> keys = jDocument.getKeys("key1");

        assertNotNull(keys);
        assertTrue(keys.isEmpty());
    }

    @Test
    public void testGetKeys_Success_MultipleKeys() {
        Map<String, Object> innerData = new HashMap<>();
        innerData.put("innerKey1", "value1");
        innerData.put("innerKey2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", innerData);

        JDocument jDocument = new JDocument(data);

        Set<String> keys = jDocument.getKeys("key1");

        assertNotNull(keys);
        assertEquals(2, keys.size());
        assertTrue(keys.contains("innerKey1"));
        assertTrue(keys.contains("innerKey2"));
    }

    /**
     * The getString method in the JDocument class is being tested here.
     * It returns a String value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetString_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        JDocument jDocument = new JDocument(data);

        String result = jDocument.getString("key1");

        assertNotNull(result);
        assertEquals("value1", result);
    }

    @Test
    public void testGetString_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        String result = jDocument.getString("key1");

        assertNotNull(result);
        assertEquals("", result);
    }

    @Test
    public void testGetString_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        String result = jDocument.getString("key1.key2");

        assertNotNull(result);
        assertEquals("value2", result);
    }

    @Test
    public void testGetString_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", "value2");

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        String result = jDocument.getString("key1.key3");

        assertNotNull(result);
        assertEquals("", result);
    }

    /**
     * The getBoolean method in the JDocument class is being tested here.
     * It retrieves a boolean value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetBoolean_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", true);
        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.getBoolean("key1");

        assertTrue(result);
    }

    @Test
    public void testGetBoolean_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        boolean result = jDocument.getBoolean("key1");

        assertFalse(result);
    }

    @Test
    public void testGetBoolean_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", true);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.getBoolean("key1.key2");

        assertTrue(result);
    }

    @Test
    public void testGetBoolean_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", true);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        boolean result = jDocument.getBoolean("key1.key3");

        assertFalse(result);
    }

    /**
     * The getByte method in the JDocument class is being tested here.
     * It retrieves a byte value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetByte_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", (byte) 1);
        JDocument jDocument = new JDocument(data);

        byte result = jDocument.getByte("key1");

        assertEquals((byte) 1, result);
    }

    @Test
    public void testGetByte_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        byte result = jDocument.getByte("key1");

        assertEquals((byte) 0, result);
    }

    @Test
    public void testGetByte_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", (byte) 2);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        byte result = jDocument.getByte("key1.key2");

        assertEquals((byte) 2, result);
    }

    @Test
    public void testGetByte_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", (byte) 2);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        byte result = jDocument.getByte("key1.key3");

        assertEquals((byte) 0, result);
    }

    /**
     * The getShort method in the JDocument class is being tested here.
     * It retrieves a short value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetShort_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", (short) 1);
        JDocument jDocument = new JDocument(data);

        short result = jDocument.getShort("key1");

        assertEquals((short) 1, result);
    }

    @Test
    public void testGetShort_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        short result = jDocument.getShort("key1");

        assertEquals((short) 0, result);
    }

    @Test
    public void testGetShort_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", (short) 2);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        short result = jDocument.getShort("key1.key2");

        assertEquals((short) 2, result);
    }

    @Test
    public void testGetShort_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", (short) 2);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        short result = jDocument.getShort("key1.key3");

        assertEquals((short) 0, result);
    }

    /**
     * The getInt method in the JDocument class is being tested here.
     * It retrieves an integer value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetInt_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", 123);
        JDocument jDocument = new JDocument(data);

        int result = jDocument.getInt("key1");

        assertEquals(123, result);
    }

    @Test
    public void testGetInt_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        int result = jDocument.getInt("key1");

        assertEquals(0, result);
    }

    @Test
    public void testGetInt_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 456);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        int result = jDocument.getInt("key1.key2");

        assertEquals(456, result);
    }

    @Test
    public void testGetInt_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 456);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        int result = jDocument.getInt("key1.key3");

        assertEquals(0, result);
    }

    /**
     * The getLong method in the JDocument class is being tested here.
     * It retrieves a long value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetLong_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", 123L);
        JDocument jDocument = new JDocument(data);

        long result = jDocument.getLong("key1");

        assertEquals(123L, result);
    }

    @Test
    public void testGetLong_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        long result = jDocument.getLong("key1");

        assertEquals(0L, result);
    }

    @Test
    public void testGetLong_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 456L);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        long result = jDocument.getLong("key1.key2");

        assertEquals(456L, result);
    }

    @Test
    public void testGetLong_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 456L);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        long result = jDocument.getLong("key1.key3");

        assertEquals(0L, result);
    }

    /**
     * The getFloat method in the JDocument class is being tested here.
     * It retrieves a float value of the specified key from the JDocument's data.
     */
    @Test
    public void testGetFloat_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", 1.23f);
        JDocument jDocument = new JDocument(data);

        float result = jDocument.getFloat("key1");

        assertEquals(1.23f, result);
    }

    @Test
    public void testGetFloat_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        float result = jDocument.getFloat("key1");

        assertEquals(0f, result);
    }

    @Test
    public void testGetFloat_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 4.56f);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        float result = jDocument.getFloat("key1.key2");

        assertEquals(4.56f, result);
    }

    @Test
    public void testGetFloat_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 4.56f);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        float result = jDocument.getFloat("key1.key3");

        assertEquals(0f, result);
    }

    /**
     * The getDouble method in the JDocument class is being tested here.
     * It retrieves a double value of the specified key from the JDocument's data.
     */

    @Test
    public void testGetDouble_Success_SingleKey() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", 1.23d);
        JDocument jDocument = new JDocument(data);

        double result = jDocument.getDouble("key1");

        assertEquals(1.23d, result);
    }

    @Test
    public void testGetDouble_Failure_KeyNotFound() {
        JDocument jDocument = new JDocument(Collections.emptyMap());

        double result = jDocument.getDouble("key1");

        assertEquals(0d, result);
    }

    @Test
    public void testGetDouble_Success_NestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 4.56d);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        double result = jDocument.getDouble("key1.key2");

        assertEquals(4.56d, result);
    }

    @Test
    public void testGetDouble_Failure_NonExistentNestedKey() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("key2", 4.56d);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", nestedData);

        JDocument jDocument = new JDocument(data);

        double result = jDocument.getDouble("key1.key3");

        assertEquals(0d, result);
    }
}
