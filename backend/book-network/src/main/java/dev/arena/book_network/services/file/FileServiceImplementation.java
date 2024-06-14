package dev.arena.book_network.services.file;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImplementation implements FileService {

    @Value("${application.file.upload}")
    private String fileUploadPath;

    @Override
    public String saveFile(@NonNull UUID accountId, @NonNull MultipartFile file) throws IOException {

        final String fileUploadSubPath = "accounts" + File.separator + accountId;

        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;

        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to create the target folder");
                return null;
            }
        }

        final String fileExtension = getFileExtension(file);
        final String fileName = System.currentTimeMillis() + "." + fileExtension;
        // ./uploads/accounts/5c2e193d-11f6-4b9b-a270-cffe77cb7596/12321312313.png
        String targetFilePath = finalUploadPath + File.separator + fileName;

        Path targetPath = Paths.get(targetFilePath);
        Files.write(targetPath, file.getBytes());

        return targetFilePath;
    }

    private String getFileExtension(@NonNull MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || originalFileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = originalFileName.lastIndexOf(".");

        if (lastDotIndex == -1) {
            return "";
        }

        return originalFileName
                .substring(lastDotIndex)
                .toLowerCase();
    }
}
