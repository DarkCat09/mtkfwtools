package ru.darkcat09.mtkfwtools;

public abstract class MftEventHandler {
    public abstract void onProgressChanged(int completed, int total);
    public abstract void onSuccess();
    public abstract void onError();
}
