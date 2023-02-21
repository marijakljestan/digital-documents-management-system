package com.agency.backend.storage;

import com.agency.backend.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static com.agency.backend.AgencyApplication.LOGGER_ERROR;
import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class FileSystemStorageService implements StorageService {

    private final String ROOT_LOCATION = "docs/";
    private final Path rootLocation = Paths.get(ROOT_LOCATION);

    @Override
    public String store(MultipartFile file, String path) {
        LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: store - start.");
        try {
            if (file.isEmpty()){
                LOGGER_ERROR.error("FILE SYSTEM STORAGE SERVICE: store - Empty file.");
                throw new StorageException("Failed to store empty file.");
            }
            byte[] bytes = file.getBytes();
            String rootPath = ROOT_LOCATION + path;
            Path filePath = Files.write(Paths.get(rootPath), bytes);
            LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: store - end.");
            return filePath.toString();
        }
        catch (IOException e) {
            LOGGER_ERROR.error("FILE SYSTEM STORAGE SERVICE: store - " + e.getMessage());
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: loadAll...");
        try {
            return Files.walk(this.rootLocation, 2)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            LOGGER_ERROR.error("FILE SYSTEM STORAGE SERVICE: loadAll - " + e.getMessage());
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: load...");
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: loadAsResource...");
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new StorageException("Could not read file: " + filename);
        }
        catch (MalformedURLException e) {
            LOGGER_ERROR.error("FILE SYSTEM STORAGE SERVICE: loadAsResource - " + e.getMessage());
            throw new StorageException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: deleteAll...");
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: init - start.");
            Files.createDirectories(rootLocation);
            LOGGER_INFO.info("FILE SYSTEM STORAGE SERVICE: init - end.");
        }
        catch (IOException e) {
            LOGGER_ERROR.error("FILE SYSTEM STORAGE SERVICE: init - " + e.getMessage());
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
