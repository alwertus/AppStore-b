package com.alwertus.appstore.view.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRq {
    String login;
    String password;
}
