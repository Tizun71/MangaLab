package com.tizun.mangalab.domainLayer.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable	
public class FollowID implements Serializable{
    @Column(name = "userID")
    private int UserID;

    @Column(name = "mangaID")
    private int MangaID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowID followId = (FollowID) o;
        return UserID == followId.UserID && MangaID == followId.MangaID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserID, MangaID);
    }

}
