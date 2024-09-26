package de.oliver.fancylib.jdb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JDBTest {

    //This class tests the get operation of the JDB class,
    //which is supposed to retrieve and deserialize a JSON document
    //from a given path in the file system.

    private final String basePath = "./test_files/";

    public static void cleanUpDirectory(String path) throws IOException {
        Path directory = Paths.get(path);
        if (Files.exists(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        cleanUpDirectory(basePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        cleanUpDirectory(basePath);
    }

    @Test
    public void testGetObject() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "test_file";
        jdb.set(path, "Test message");

        // Act
        String result = jdb.get(path, String.class);

        // Assert
        assertEquals("Test message", result);
    }

    @Test
    public void testGetObjectNonExisting() throws IOException {
        // Prepare
        JDB jdb = new JDB("./test_files/");
        String path = "does_not_exist";

        // Act
        Object result = jdb.get(path, Object.class);

        // Assert
        assertNull(result);
    }

    @Test
    public void testGetAllObjects() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "test_files";
        jdb.set(path + "/obj1", "Test message 1");
        jdb.set(path + "/obj2", "Test message 2");
        jdb.set(path + "/obj3", "Test message 3");

        // Act
        List<String> result = jdb.getAll(path, String.class);

        // Assert
        assertEquals(3, result.size());
        assertTrue(result.contains("Test message 1"));
        assertTrue(result.contains("Test message 2"));
        assertTrue(result.contains("Test message 3"));
    }

    @Test
    public void testGetAllObjectsNonExisting() throws IOException {
        // Prepare
        JDB jdb = new JDB("./test_files/");
        String path = "does_not_exist";

        // Act
        List<Object> result = jdb.getAll(path, Object.class);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSetNewObject() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "new_object";
        String value = "New message";

        // Act
        jdb.set(path, value);
        String result = jdb.get(path, String.class);

        // Assert
        assertEquals(value, result);
    }

    @Test
    public void testSetExistingObject() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "existing_object";
        String value = "Existing message";
        jdb.set(path, "Old message");

        // Act
        jdb.set(path, value);
        String result = jdb.get(path, String.class);

        // Assert
        assertEquals(value, result);
    }

    @Test
    public void testSetObjectNull() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "null_object";

        // Act
        jdb.set(path, null);
        String result = jdb.get(path, String.class);

        // Assert
        assertNull(result);
    }

    @Test
    public void testDeleteWhenFileExists() throws IOException {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "existing_file";
        String value = "Test message";
        jdb.set(path, value);

        // Act
        jdb.delete(path);
        String result = jdb.get(path, String.class);

        // Assert
        assertNull(result);

        File file = new File(basePath + path + ".json");
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteWhenFileNotExists() {
        // Prepare
        String basePath = "./test_files/";
        JDB jdb = new JDB(basePath);
        String path = "non_existing_file";

        // Act
        jdb.delete(path);

        // Assert
        File file = new File(basePath + path + ".json");
        assertFalse(file.exists());
    }
}
