package de.neuefische.presentationbackend.controller;

import de.neuefische.presentationbackend.model.ImageAnalyseResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnalyseImageControllerTest {


  @Value("classpath:images/BeardImage.png")
  private Resource beardImage;

  @Value("classpath:images/NoPersonImage.jpeg")
  private Resource noPersonImage;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  @Test
  @DisplayName("Analyse Controller should detect Beard and Happy")
  public void detectBeardAndHappy() {
    //GIVEN
    var data = new LinkedMultiValueMap<String, Object>();
    data.add("imageFile", beardImage);
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    //WHEN
    ResponseEntity<ImageAnalyseResult> response = testRestTemplate.exchange(
        "http://localhost:" + port + "/api/image/analyse",
        HttpMethod.POST,
        new HttpEntity<>(data, headers),
        ImageAnalyseResult.class);


    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getBody(), is(new ImageAnalyseResult(true, true)));
  }

  @Test
  @DisplayName("Analyse should return Bad request when no person exists")
  public void noPersonBadRequest() {
    //GIVEN
    var data = new LinkedMultiValueMap<String, Object>();
    data.add("imageFile", noPersonImage);
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    //WHEN
    ResponseEntity<ImageAnalyseResult> response = testRestTemplate.exchange(
        "http://localhost:" + port + "/api/image/analyse",
        HttpMethod.POST,
        new HttpEntity<>(data, headers),
        ImageAnalyseResult.class);


    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
  }

}
