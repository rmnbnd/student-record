package student.record.web;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import student.record.service.SessionService;
import student.record.service.YoutubeMyUploads;
import student.record.web.rest.YoutubeVideo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class YoutubeResource {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Autowired
    private SessionService sessionService;
    private GoogleAuthorizationCodeFlow flow;

    @RequestMapping(value = "youtube/google-authorize", method = RequestMethod.GET)
    public ResponseEntity<String> googleAuthorize() throws Exception {
        return new ResponseEntity<>(authorize(), HttpStatus.OK);
    }

    @RequestMapping(value = "youtube/save-authorize-code", method = RequestMethod.GET, params = "code")
    public String oauth2Callback(@RequestParam(value = "code") String code) throws IOException {
        sessionService.addGoogleAuthorizeCode(code);
        TokenResponse response = flow.newTokenRequest(sessionService.getGoogleAuthorizeCode())
                .setRedirectUri("http://localhost:8080/api/youtube/save-authorize-code").execute();
        Credential credential = flow.createAndStoreCredential(response, "userID");
        sessionService.addYoutubeUploads(YoutubeMyUploads.getYoutubeUploads(credential).stream()
                .map(YoutubeVideo::from)
                .collect(Collectors.toList()));
        return "Thank you. You can close this page.";
    }

    @GetMapping("youtube/uploads")
    public ResponseEntity<List<YoutubeVideo>> getYoutubeUploads()
            throws URISyntaxException, IOException {
        return new ResponseEntity<>(sessionService.getYoutubeUploads(), HttpStatus.OK);
    }

    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
            web.setClientId("");
            web.setClientSecret("");
            GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton("https://www.googleapis.com/auth/youtube.readonly")).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/api/youtube/save-authorize-code");
        return authorizationUrl.build();
    }

}
