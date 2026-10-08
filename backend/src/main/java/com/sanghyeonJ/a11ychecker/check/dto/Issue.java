package com.sanghyeonJ.a11ychecker.check.dto;

/**
 * 검사 규칙이 발견한 문제 요소 하나
 *
 * @param severity 심각도 (FAIL / WARNING)
 * @param line     sourceLines 기준 줄 번호 (1부터 시작)
 * @param message  사용자에게 보여줄 설명
 * @param snippet  문제 요소의 HTML 일부 (목록에서 미리보기용)
 */
public record Issue(
        Severity severity,
        int line,
        String message,
        String snippet
) {
}
