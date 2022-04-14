package com.alwertus.appstore.view.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ResponseError extends Response {

    private String error;
    private String result;

    public ResponseError(String error) {
        this.error = error;
    }

    @Override
    public String getResult() {
        return "Error";
    }
}
