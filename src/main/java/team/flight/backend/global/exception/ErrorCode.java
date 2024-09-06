package team.flight.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, 4004, "해당 객체가 존재하지 않습니다."),
    PDF_CONVERT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5005, "파일을 PDF로 변환하는 과정에서 에러가 발생했습니다."),
    WEBCLIENT_CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5006, "AI 서버 요청 간 클라이언트 에러 발생"),
    WEBCLIENT_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5007, "AI 서버 요청 간 AI 서버 내부 에러 발생")
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
