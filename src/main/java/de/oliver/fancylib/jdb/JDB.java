package de.oliver.fancylib.jdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * The JDB class provides a simple JSON document-based storage system in a specified directory.
 */
public class JDB {

    private final static Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    private final @NotNull String basePath;
    private final @NotNull File baseFolder;

    /**
     * Constructs a new JDB instance with the specified base path.
     *
     * @param basePath the base directory path where documents will be stored
     */
    public JDB(@NotNull String basePath) {
        this.basePath = basePath;
        this.baseFolder = new File(basePath);
    }

    /**
     * Retrieves a document from the specified path, deserializing it into the given class type.
     *
     * @param <T>   the type of the object to be returned
     * @param path  the relative path (excluding .json extension) where the document is located
     * @param clazz the class type to which the document should be deserialized
     * @return a JDocument containing the deserialized object and its path, or null if the file does not exist
     * @throws IOException if an I/O error occurs during file reading
     */
    public <T> T get(@NotNull String path, @NotNull Class<T> clazz) throws IOException {
        File file = new File(baseFolder, basePath + path + ".json");
        if (!file.exists()) {
            return null;
        }

        BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());

        return GSON.fromJson(bufferedReader, clazz);
    }

    /**
     * Retrieves all documents from the specified directory path, deserializing them into the given class type.
     *
     * @param <T>   the type of objects to be returned
     * @param path  the relative directory path containing the documents
     * @param clazz the class type to which the documents should be deserialized
     * @return a List of JDocument objects containing the deserialized objects and their paths, or null if the directory or files do not exist
     * @throws IOException if an I/O error occurs during file reading
     */
    public <T> List<T> getAll(@NotNull String path, @NotNull Class<T> clazz) throws IOException {
        File folder = new File(baseFolder, basePath + path);
        if (!folder.exists()) {
            return new ArrayList<>();
        }

        File[] files = folder.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<T> documents = new ArrayList<>(files.length);
        for (File file : files) {
            documents.add(get(path + "/" + file.getName().replace(".json", ""), clazz));
        }

        return documents;
    }

    /**
     * Saves the given value as a document at the specified path.
     *
     * @param <T>   the type of the object to be saved
     * @param path  the relative path (excluding .json extension) where the document will be saved
     * @param value the object to be saved as a JSON document
     * @throws IOException if an I/O error occurs during file writing
     */
    public <T> void set(@NotNull String path, @NotNull T value) throws IOException {
        File file = new File(baseFolder, basePath + path + ".json");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String json = GSON.toJson(value);

        Files.write(file.toPath(), json.getBytes());
    }

    /**
     * Deletes the document(s) at the specified path.
     *
     * @param path the relative path (excluding .json extension) where the document(s) are located
     */
    public void delete(@NotNull String path) {
        File file = new File(baseFolder, basePath + path + ".json");
        if (file.exists()) {
            file.delete();
        }
    }
}
