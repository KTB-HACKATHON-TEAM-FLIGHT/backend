package team.flight.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.flight.backend.dto.FirstPostRequest;
import team.flight.backend.dto.PostDetailResponse;
import team.flight.backend.dto.PostsResponse;
import team.flight.backend.service.PdfService;
import team.flight.backend.service.PostService;
import team.flight.backend.service.dto.FirstResponse;

@Tag(name = "PPT 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final PdfService pdfService;

    @Operation(summary = "PPT 생성 요청 API")
    @PostMapping
    public ResponseEntity<FirstResponse> sendFirstRequest(@RequestBody FirstPostRequest request) {
        return ResponseEntity.ok()
                .body(postService.sendUserFirstRequest(UUID.fromString(request.sessionId()), request.postId(), request.request()));
    }

    @Operation(summary = "사용자가 작성한 게시물 목록 조회 기능 구현")
    @GetMapping
    public ResponseEntity<PostsResponse> getPosts(@RequestParam("sessionId") String sessionId) {
        return ResponseEntity.ok().body(postService.findAllPostsBy(UUID.fromString(sessionId)));
    }

    @Operation(summary = "특정 게시글 결과 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable("id") Long postId,
                                                            @RequestParam("sessionId") String sessionId) {
        return ResponseEntity.ok().body(postService.findPostsBy(postId, UUID.fromString(sessionId)));
    }

    @Operation(summary = "HTML 코드를 PDF 파일로 변환하는 API")
    @PostMapping("/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody String htmlContent)
            throws IOException, InterruptedException {
        // PDF 파일 생성
        String outputPdfPath = UUID.randomUUID() + ".pdf";
        File pdfFile = pdfService.generatePdfFromHtml(htmlContent, outputPdfPath);

        // PDF 파일을 클라이언트에게 반환
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + outputPdfPath);
        // 서버 로컬 파일 삭제
        pdfFile.delete();

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
