package de.neuefische.presentationbackend.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import de.neuefische.presentationbackend.model.ImageAnalyseResult;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class ImageAnalyseService {

  private final AmazonRekognition rekognition;

  public ImageAnalyseService(AmazonRekognition rekognition) {
    this.rekognition = rekognition;
  }


  public ImageAnalyseResult analyseImage(MultipartFile imageFile) {
    DetectFacesResult detectFacesResult = detectFaces(imageFile);
    List<FaceDetail> faceDetails = detectFacesResult.getFaceDetails();

    if (faceDetails.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no face detected");
    }

    FaceDetail faceDetail = faceDetails.get(0);

    return new ImageAnalyseResult(
        faceDetail.getBeard().getConfidence() > 0.5,
        faceDetail.getEmotions().stream().anyMatch(emotion -> "HAPPY".equals(emotion.getType()) && emotion.getConfidence() > 0.5)
    );
  }

  private DetectFacesResult detectFaces(MultipartFile imageFile) {
    try {
      Image image = new Image().withBytes(ByteBuffer.wrap(imageFile.getBytes()));
      DetectFacesRequest request = new DetectFacesRequest()
          .withImage(image)
          .withAttributes("ALL");
      return rekognition.detectFaces(request);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not parse image");
    }
  }
}
