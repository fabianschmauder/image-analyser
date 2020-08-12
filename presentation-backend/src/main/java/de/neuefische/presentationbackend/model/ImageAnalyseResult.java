package de.neuefische.presentationbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageAnalyseResult {
  private boolean hasBeard;
  private boolean isHappy;
}
