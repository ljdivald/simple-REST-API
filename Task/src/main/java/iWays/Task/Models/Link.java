package iWays.Task.Models;

public class Link {
    private final int linkId;
    private final String rel;
    private final String href;

    public Link(int linkId, String rel, String href) {
        this.linkId = linkId;
        this.rel = rel;
        this.href = href;
    }

    public int getLinkId() {
        return linkId;
    }

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }
}
