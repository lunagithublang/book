package dev.arena.book_network.services.file;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface FileService {
    String saveFile(@NonNull UUID accountId, @NonNull MultipartFile file) throws IOException;
}
