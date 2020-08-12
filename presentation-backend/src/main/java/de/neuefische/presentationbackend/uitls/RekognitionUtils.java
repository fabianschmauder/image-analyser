package de.neuefische.presentationbackend.uitls;

import com.amazonaws.services.rekognition.model.Emotion;
import com.amazonaws.services.rekognition.model.FaceDetail;

import java.util.List;

public class RekognitionUtils {


  public static boolean hasEmotion(FaceDetail faceDetail, String emotionType) {
    List<Emotion> emotions = faceDetail.getEmotions();
    return emotions.stream().anyMatch(emotion -> emotionType.equals(emotion.getType()) && emotion.getConfidence() > 50.0);
  }

  public static boolean hasBeard(FaceDetail faceDetail) {
    return faceDetail.getBeard().getValue();
  }

}
