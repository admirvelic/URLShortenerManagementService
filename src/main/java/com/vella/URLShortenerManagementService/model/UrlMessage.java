package com.vella.URLShortenerManagementService.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UrlMessage {

    private Long id;

    private String realURL;

    private String shortURL;

    private Action action;

    public UrlMessage(Url url, Action action) {
        this.shortURL = url.getShortURL();
        this.realURL = url.getRealURL();
        this.action = action;
    }
}


