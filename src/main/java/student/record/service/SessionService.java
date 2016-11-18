package student.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import student.record.web.rest.YoutubeVideo;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class SessionService {

    private static final String SESSION_GOOGLE_AUTHORIZE_CODE = "googleAuthorizeCode";
    private static final String SESSION_YOUTUBE_VIDEOS = "youtubeVideos";

    @Autowired
    private HttpSession httpSession;


    public void addGoogleAuthorizeCode(String code) {
        httpSession.removeAttribute(SESSION_GOOGLE_AUTHORIZE_CODE);
        httpSession.setAttribute(SESSION_GOOGLE_AUTHORIZE_CODE, code);
    }

    public String getGoogleAuthorizeCode() {
        return (String) httpSession.getAttribute(SESSION_GOOGLE_AUTHORIZE_CODE);
    }

    public void addYoutubeUploads(List<YoutubeVideo> videos) {
        httpSession.removeAttribute(SESSION_YOUTUBE_VIDEOS);
        httpSession.setAttribute(SESSION_YOUTUBE_VIDEOS, videos);
    }

    public List<YoutubeVideo> getYoutubeUploads() {
        return (List<YoutubeVideo>) httpSession.getAttribute(SESSION_YOUTUBE_VIDEOS);
    }
}
