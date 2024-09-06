package team.flight.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, 4004, "해당 객체가 존재하지 않습니다."),
    PDF_CONVERT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5005, "파일을 PDF로 변환하는 과정에서 에러가 발생했습니다.")
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
