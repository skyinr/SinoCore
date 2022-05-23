package games.moegirl.sinocraft.sinocore.api.utility;

import net.minecraftforge.fml.ModList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * A class to get classes jar or directory.
 */
public record ModFiles(Path root, FileSystem fs) {

    // forge "union" scheme
    private static final FileSystemProvider UFSP = FileSystemProvider.installedProviders().stream()
            .filter(f -> "union".equalsIgnoreCase(f.getScheme())).findFirst().orElseThrow();

    private static final Map<String, ModFiles> CACHE = new HashMap<>();

    /**
     * Get classes path
     * @return path
     * @throws URISyntaxException if this URL is not formatted strictly according to
     *                            RFC2396 and cannot be converted to a URI.
     * @throws NullPointerException if this modid is not loaded.
     */
    public static ModFiles getFiles(String modid) throws URISyntaxException {
        if (CACHE.containsKey(modid)) {
            return CACHE.get(modid);
        }
        Class<?> modClass = ModList.get().getModObjectById(modid).orElseThrow().getClass();
        URI uri = modClass.getProtectionDomain().getCodeSource().getLocation().toURI();
        FileSystem fs;
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            fs = FileSystems.getFileSystem(uri);
        } else {
            fs = UFSP.getFileSystem(uri);
        }
        ModFiles files = new ModFiles(fs.getPath("/"), fs);
        CACHE.put(modid, files);
        return files;
    }

    /**
     * Return a stream contains all files in the package
     * @param packageName package name
     * @return the {@link Stream} of {@link Path}
     * @throws IOException if an I/O error is thrown when accessing the starting file.
     */
    public Stream<Path> forPackage(String packageName) throws IOException {
        return Files.walk(root.resolve("/" + packageName.replace(".", "/")));
    }

    /**
     * Return a stream contains all files in the package
     * @param packageName package name
     * @param depth the maximum number of directory levels to visit
     * @return the {@link Stream} of {@link Path}
     * @throws IOException if an I/O error is thrown when accessing the starting file.
     */
    public Stream<Path> forPackage(String packageName, int depth) throws IOException {
        return Files.walk(root.resolve("/" + packageName.replace(".", "/")), depth);
    }
}
