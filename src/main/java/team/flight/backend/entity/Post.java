package team.flight.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "session_id", nullable = false)
    private UUID sessionId;

    @Column(name = "request", columnDefinition = "TEXT")
    private String request;

    @Column(name = "result", columnDefinition = "TEXT")
    private String result;

    @Builder
    public Post(UUID sessionId, String request, String result) {
        this.sessionId = sessionId;
        this.request = request;
        this.result = result;
    }

    public void updateResult(String result) {
        this.result = result;
    }

    public void updateRequest(String request) {
        this.request = request;
    }
}
