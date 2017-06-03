package iWays.Task.Models;

public class Avatar {
    private final int avatarId;
    private final String contentType;
    private final String url;
    private final Link links;

    public Avatar(int avatarId, String contentType, String url, Link links) {
        this.avatarId = avatarId;
        this.contentType = contentType;
        this.url = url;
        this.links = links;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getUrl() {
        return url;
    }

    public Link getLinks() {
        return links;
    }
}
