package de.neuefische.presentationbackend.service;

import de.neuefische.presentationbackend.model.ImageAnalyseResult;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageAnalyseService {

  public ImageAnalyseResult analyseImage(MultipartFile imageFile) {

    return new ImageAnalyseResult(
        Math.random() > 0.5,
        Math.random() > 0.5
    );
  }
}
