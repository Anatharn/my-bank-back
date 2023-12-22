package sds.home.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sds.home.bank.service.OperationService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/alive")
    public String test() {
        return "ok";
    }
    @PostMapping("/upload-file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("url") String url) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        operationService.importFileContent(content, url);
        return ResponseEntity.ok().build();
    }
}
