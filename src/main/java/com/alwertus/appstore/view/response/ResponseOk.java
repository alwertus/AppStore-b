package com.alwertus.appstore.view.response;


import lombok.Setter;

public class ResponseOk extends Response {

    @Setter
    private String result;

    @Override
    public String getResult() {
        return "Ok";
    }
}
