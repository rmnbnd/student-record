package student.record.web;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.PlaylistItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class YoutubeResource {

    GoogleAuthorizationCodeFlow flow;

    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    GoogleClientSecrets clientSecrets;

    @RequestMapping(value = "youtube/google-authorize", method = RequestMethod.GET)
    public ResponseEntity<String> googleAuthorize() throws Exception {
        return new ResponseEntity<>(authorize(), HttpStatus.OK);
    }

    @RequestMapping(value = "/google.do", method = RequestMethod.GET, params = "code")
    public ModelAndView oauth2Callback(@RequestParam(value = "code") String code, ModelAndView mv) {
        return new ModelAndView();
    }

    @GetMapping("youtube/uploads")
    public ResponseEntity<List<PlaylistItem>> getYoutubeUploads()
            throws URISyntaxException, IOException {
//        List<PlaylistItem> youtubeUploads = YoutubeMyUploads.getYoutubeUploads();
        TokenResponse response =
                new AuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                        new GenericUrl("https://accounts.google.com/o/oauth2/auth"), "some code")
                        .setRedirectUri("http://google.com")
                        .set("client_id", "")
                        .set("client_secret", "")
//                        .set("response_type", "code")
                        .set("scope", "https://www.googleapis.com/auth/youtube.readonly")
                        .execute();

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
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
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/api/google.do");
        return authorizationUrl.build();
    }

}
