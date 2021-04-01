package com.murad.roya_news.helpers;


public class NetworkState {
    enum  State{
        RUNNING,
        SUCCESS,
        FAILED
    }
    State state;
    public NetworkState(State state){
        this.state = state;
    }

    public static NetworkState LOADED = new NetworkState(State.SUCCESS);
    public static NetworkState LOADING = new NetworkState(State.RUNNING);
    public static NetworkState ERROR = new NetworkState(State.FAILED);

}
