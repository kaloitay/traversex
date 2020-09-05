package com.benmark.elegancy.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Youtube
{
    private String kind;
    private String etag;
    private String nextPageToken;
    private String regionCode;
    private PageInfo pageInfo;
    private Object id;
    private ArrayList < Object > items = new ArrayList < Object > ();


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class PageInfo {
        private float totalResults;
        private float resultsPerPage;
    }

}


