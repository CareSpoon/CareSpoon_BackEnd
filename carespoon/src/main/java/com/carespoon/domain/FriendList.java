package com.carespoon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;

    @Column
    private UUID viewerId;

    @Column
    private String viewerName;

    @Column
    private UUID seniorId;


    @Column
    private String seniorName;

    @Builder
    public FriendList(UUID viewerId, UUID seniorId, String seniorName, String viewerName){
        this.viewerId = viewerId;
        this.seniorId = seniorId;
        this.seniorName = seniorName;
        this.viewerName = viewerName;
    }
}
