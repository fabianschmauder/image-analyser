package de.neuefische.presentationbackend.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

  @Value("${amazon.aws.accesskey}")
  private String accessKey;

  @Value("${amazon.aws.secretkey}")
  private String secretKey;

  @Value("${amazon.aws.region}")
  private String region;


  @Bean
  public AmazonRekognition rekognitionClient() {
    var credentialsProvider = getCredentialsProvider();
    return AmazonRekognitionAsyncClientBuilder.standard()
        .withCredentials(credentialsProvider)
        .withRegion(region).build();
  }

  private AWSCredentialsProvider getCredentialsProvider() {
    if (accessKey == null || accessKey.isBlank()) {
      return new InstanceProfileCredentialsProvider(true);
    }
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
  }
}
