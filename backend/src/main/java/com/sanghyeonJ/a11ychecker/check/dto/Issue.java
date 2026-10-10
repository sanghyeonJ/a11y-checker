package com.sanghyeonJ.a11ychecker.check.dto;

/**
 * 검사 규칙이 발견한 문제 요소 하나
 *
 * @param severity 심각도 (FAIL / WARNING)
 * @param line     원본 HTML(sourceLines) 기준 줄 번호 (1부터 시작),
 *                 가리킬 줄이 없으면 null (예: title 요소 없음, 파서가 자동 생성한 요소)
 * @param message  사용자에게 보여줄 설명
 * @param snippet  문제 요소의 HTML 일부 (목록에서 미리보기용)
 */
public record Issue(
        Severity severity,
        Integer line,
        String message,
        String snippet
) {
}