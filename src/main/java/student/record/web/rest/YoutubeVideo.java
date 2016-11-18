package student.record.web.rest;

import com.google.api.services.youtube.model.PlaylistItem;

public class YoutubeVideo {

    private String title;
    private String videoId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public static YoutubeVideo from(PlaylistItem item) {
        YoutubeVideo video = new YoutubeVideo();
        video.title = item.getSnippet().getTitle();
        video.videoId = item.getContentDetails().getVideoId();
        return video;
    }

}
