package com.openhabbo.api.game.groups.types.threads;

import java.util.List;

public interface ForumThread {
    int getId();

    String getTitle();

    int getAuthorId();

    int getAuthorTimestamp();

    boolean isLocked();

    boolean isPinned();

    List<ForumThreadReply> getReplies();
}
