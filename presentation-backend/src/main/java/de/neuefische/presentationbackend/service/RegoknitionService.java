package de.neuefische.presentationbackend.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.ByteBuffer;

@Service
public class RegoknitionService {
  private final AmazonRekognition rekognition;


  public RegoknitionService(AmazonRekognition rekognition) {
    this.rekognition = rekognition;
  }


  public DetectFacesResult detectFaces(MultipartFile file) {
    try {
      var image = new Image().withBytes(ByteBuffer.wrap(file.getBytes()));
      DetectFacesRequest request = new DetectFacesRequest()
          .withImage(image)
          .withAttributes("ALL");
      return rekognition.detectFaces(request);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no valid image");
    }
  }
}
