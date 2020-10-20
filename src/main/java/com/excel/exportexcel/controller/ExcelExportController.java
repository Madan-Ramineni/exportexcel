package com.excel.exportexcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ExcelExportController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel() throws IOException {
        Resource resource = resourceLoader.getResource("classpath: test.xlsx");
        MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
//        HttpHeaders httpHeaders = new HttpHeaders();̥
//        httpHeaders.add("Content-disposition", "attachment; filename=test.xlsx");
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=test.xlsx").contentType(mediaType).body((resource));
    }


}
