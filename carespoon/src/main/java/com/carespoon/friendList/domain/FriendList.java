package com.carespoon.friendList.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;

    @Column
    private String viewerId;

    @Column
    private String viewerName;

    @Column
    private String seniorId;


    @Column
    private String seniorName;

    @Builder
    public FriendList(String viewerId, String seniorId, String seniorName, String viewerName){
        this.viewerId = viewerId;
        this.seniorId = seniorId;
        this.seniorName = seniorName;
        this.viewerName = viewerName;
    }
}
