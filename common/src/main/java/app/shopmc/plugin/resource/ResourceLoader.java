package app.shopmc.plugin.resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class ResourceLoader<T> {

    private final Class<?> loadingClass;
    private final Path dataDirectory;

    protected ResourceLoader(final Class<?> loadingClass, final Path dataDirectory) {
        this.loadingClass = loadingClass;
        this.dataDirectory = dataDirectory;
    }

    protected abstract T loadResource(final Path resourcePath) throws ResourceLoaderException;

    public boolean saveDefault(final String resourceName) throws ResourceLoaderException {
        if (!Files.exists(this.dataDirectory)) {
            try {
                Files.createDirectories(this.dataDirectory);
            } catch (final Exception exception) {
                throw new ResourceLoaderException(ResourceLoaderException.Reason.DIRECTORY_NOT_CREATED, exception);
            }
        }

        final Path resourcePath = this.dataDirectory.resolve(resourceName);
        if (!Files.exists(resourcePath)) {
            try (final InputStream in = this.loadingClass.getClassLoader().getResourceAsStream(resourceName)) {
                Files.copy(Objects.requireNonNull(in), resourcePath);
                return true;
            } catch (final Exception exception) {
                throw new ResourceLoaderException(ResourceLoaderException.Reason.DEFAULT_FILE_NOT_SAVED, exception);
            }
        }

        return false;
    }

    public T load(final String resourceName) throws ResourceLoaderException {
        return this.loadResource(this.dataDirectory.resolve(resourceName));
    }

}