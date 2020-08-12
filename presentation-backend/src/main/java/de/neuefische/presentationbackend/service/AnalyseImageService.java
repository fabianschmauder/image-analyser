package de.neuefische.presentationbackend.service;

import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import de.neuefische.presentationbackend.model.ImageAnalyseResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import static de.neuefische.presentationbackend.uitls.RekognitionUtils.hasBeard;
import static de.neuefische.presentationbackend.uitls.RekognitionUtils.hasEmotion;

@Service
public class AnalyseImageService {

  private final RegoknitionService rekognitionService;

  public AnalyseImageService(RegoknitionService rekognitionService) {
    this.rekognitionService = rekognitionService;
  }

  public ImageAnalyseResult analyseFile(MultipartFile file) {
    FaceDetail faceDetail = detectFace(file);

    return new ImageAnalyseResult(
        hasBeard(faceDetail),
        hasEmotion(faceDetail, "HAPPY")
    );
  }

  private FaceDetail detectFace(MultipartFile file) {
    DetectFacesResult detectFacesResult = rekognitionService.detectFaces(file);
    if (detectFacesResult.getFaceDetails().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no face found");
    }
    return detectFacesResult.getFaceDetails().get(0);
  }

}
