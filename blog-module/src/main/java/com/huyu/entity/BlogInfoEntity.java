package com.huyu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogInfoEntity {

    private Integer infoId;
    private String infoName;
    private String infoContent;
    private String infoAuthorId;
    private String infoReleaseTime;
    private String infoEditTime;

}
