package com.androhungers.rssnewsreader.model.rssFeed;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "item", strict = false)
public class FeedItem implements Serializable {
    @Element(name = "pubDate", required = false)
    private String mpubDate;
    @Element(name = "title", required = false)
    private String mtitle;
    @Element(name = "link", required = false)
    private String mlink;
    @Element(name = "description", required = false)
    private String mdescription;

    public FeedItem() {
    }

    public FeedItem(String mdescription, String mlink, String mtitle, String mpubDate) {
        this.mdescription = mdescription;
        this.mlink = mlink;
        this.mtitle = mtitle;
        this.mpubDate = mpubDate;
    }

    public String getMpubDate() {
        return mpubDate;
    }

    public void setMpubDate(String mpubDate) {
        this.mpubDate = mpubDate;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMlink() {
        return mlink;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }
}
