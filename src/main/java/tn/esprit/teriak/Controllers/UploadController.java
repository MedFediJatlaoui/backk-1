package tn.esprit.teriak.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.teriak.Entities.TableRow;
import tn.esprit.teriak.Services.FileProcessingService;

import java.io.IOException;
import java.util.List;

@RestController
public class UploadController {

    private final FileProcessingService fileProcessingService;

    public UploadController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<TableRow> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return fileProcessingService.processFile(file);
    }
}
