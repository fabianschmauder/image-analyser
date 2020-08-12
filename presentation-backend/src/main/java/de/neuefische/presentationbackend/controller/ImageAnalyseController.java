package de.neuefische.presentationbackend.controller;

import de.neuefische.presentationbackend.model.ImageAnalyseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/image/analyse")
public class ImageAnalyseController {

  @PostMapping
  public ImageAnalyseResult analyseImage(@RequestParam(value = "imageFile") MultipartFile imageData){
    return new ImageAnalyseResult();
  }
}
