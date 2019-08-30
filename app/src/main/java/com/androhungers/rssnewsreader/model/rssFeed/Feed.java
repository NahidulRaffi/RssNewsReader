package com.androhungers.rssnewsreader.model.rssFeed;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class Feed implements Serializable {
    @Element(name = "channel", required = false)
    private Channel mChannel;

    public Channel getmChannel() {
        return mChannel;
    }

    public Feed() {
    }

    public Feed(Channel mChannel) {
        this.mChannel = mChannel;
    }
}
