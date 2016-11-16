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
import com.google.api.services.youtube.model.PlaylistItem;
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class YoutubeResource {

    @Autowired
    private SessionService sessionService;

    GoogleAuthorizationCodeFlow flow;

    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    GoogleClientSecrets clientSecrets;

    @RequestMapping(value = "youtube/google-authorize", method = RequestMethod.GET)
    public ResponseEntity<String> googleAuthorize() throws Exception {
        return new ResponseEntity<>(authorize(), HttpStatus.OK);
    }

    @RequestMapping(value = "youtube/save-authorize-code", method = RequestMethod.GET, params = "code")
    public String oauth2Callback(@RequestParam(value = "code") String code) {
        sessionService.addGoogleAuthorizeCode(code);
        return "Thank you. You can close this page.";
    }

    @GetMapping("youtube/uploads")
    public ResponseEntity<List<PlaylistItem>> getYoutubeUploads()
            throws URISyntaxException, IOException {
        TokenResponse response = flow.newTokenRequest(sessionService.getGoogleAuthorizeCode())
                .setRedirectUri("http://localhost:8080/api/youtube/save-authorize-code").execute();
        Credential credential = flow.createAndStoreCredential(response, "userID");
        List<PlaylistItem> youtubeUploads = YoutubeMyUploads.getYoutubeUploads(credential);
        return new ResponseEntity<>(youtubeUploads, HttpStatus.OK);
    }

    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
            web.setClientId("");
            web.setClientSecret("");
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton("https://www.googleapis.com/auth/youtube.readonly")).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/api/youtube/save-authorize-code");
        return authorizationUrl.build();
    }

}
