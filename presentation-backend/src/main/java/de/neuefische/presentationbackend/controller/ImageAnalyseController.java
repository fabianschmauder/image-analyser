package de.neuefische.presentationbackend.controller;

import de.neuefische.presentationbackend.model.ImageAnalyseResult;
import de.neuefische.presentationbackend.service.ImageAnalyseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/image/analyse")
public class ImageAnalyseController {

  private final ImageAnalyseService imageAnalyseService;

  public ImageAnalyseController(ImageAnalyseService imageAnalyseService) {
    this.imageAnalyseService = imageAnalyseService;
  }

  @PostMapping
  public ImageAnalyseResult analyseImage(@RequestParam(value = "imageFile") MultipartFile imageData){
    return imageAnalyseService.analyseImage(imageData);
  }
}
