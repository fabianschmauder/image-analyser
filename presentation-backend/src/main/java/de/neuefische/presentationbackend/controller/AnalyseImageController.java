package de.neuefische.presentationbackend.controller;

import de.neuefische.presentationbackend.model.ImageAnalyseResult;
import de.neuefische.presentationbackend.service.AnalyseImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/image/analyse")
public class AnalyseImageController {

  private final AnalyseImageService service;

  public AnalyseImageController(AnalyseImageService service) {
    this.service = service;
  }

  @PostMapping
  public ImageAnalyseResult analyseResult(@RequestParam(value = "imageFile") MultipartFile imageData) {
    return service.analyseFile(imageData);
  }
}
