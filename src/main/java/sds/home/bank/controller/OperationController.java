package sds.home.bank.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @GetMapping("/alive")
    public String test() {
        return "Hello World!";
    }
    @PostMapping("/upload-file")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        return content;
    }
}
