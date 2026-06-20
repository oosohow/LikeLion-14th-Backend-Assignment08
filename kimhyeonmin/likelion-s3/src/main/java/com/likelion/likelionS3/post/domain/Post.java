package com.likelion.likelionS3.post.domain;

import com.likelion.likelionS3.member.domain.Member;
import com.likelion.likelionS3.post.api.dto.request.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;

    private String contents;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Post(String title, String contents, Member member, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.member = member;
        this.imageUrl = imageUrl;
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.title();
        this.contents = postUpdateRequestDto.contents();
    }

    //이미지 주소 변경
    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
