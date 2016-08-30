package com.openhabbo.api.modules.events;

public class EventArgs {
    private boolean isCancelled;

    public EventArgs() {
        this.isCancelled = false;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }
}
