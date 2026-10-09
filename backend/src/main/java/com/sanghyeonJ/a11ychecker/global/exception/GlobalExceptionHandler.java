package com.sanghyeonJ.a11ychecker.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 모든 컨트롤러에서 발생한 예외를 한 곳에서 처리
 */
@RestControllerAdvice  // "모든 컨트롤러를 지켜보다가 예외가 나면 여기서 처리하겠다"는 표시
public class GlobalExceptionHandler {

    /**
     * @Valid 검증 실패 (예: URL 빈 값, http/https가 아님)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)  // @Valid 검사에 걸렸을 때 Spring이 던지는 예외
    public ProblemDetail handleValidation (MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()  // 걸린 규칙 목록
                .findFirst()  // 그중 첫 번째
                .map(FieldError::getDefaultMessage)  // 그 규칙의 message 값
                .orElse("잘못된 요청입니다.");  // 혹시 없으면 기본 문구

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * 요청 본문을 읽을 수 없음 (예: JSON 문법 오류, 본문 없음)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)  // { "url": 처럼 JSON이 깨졌거나 본문이 아예 없을 때
    public ProblemDetail handleNotReadable (HttpMessageNotReadableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "요청 형식이 올바르지 않습니다.");
    }

}
