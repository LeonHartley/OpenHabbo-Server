package com.openhabbo.api.game.groups.types.threads;

public interface ForumThreadReply {
    int getId();

    int getIndex();

    String getMessage();

    int getThreadId();

    int getAuthorId();

    int getPostedTimestamp();

    int getState();

    void setState(int state);
}
