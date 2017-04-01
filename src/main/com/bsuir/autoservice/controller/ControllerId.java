package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.library.RequestType;

public class ControllerId{
    private final RequestType requestType;
    private final String url;

    public ControllerId(RequestType type, String url){
        this.requestType = type;
        this.url = url;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        ControllerId controllerId = (ControllerId)obj;
        return requestType == controllerId.requestType &&
                (url == controllerId.url || url != null && url.equals(controllerId.url));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + requestType.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
