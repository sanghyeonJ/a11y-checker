package com.sanghyeonJ.a11ychecker.check.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 단일 페이지 검사 결과 (검사 API의 최종 응답)
 *
 * @param url         검사한 페이지 URL
 * @param checkedAt   검사 시각
 * @param passed      FAIL 항목이 0개면 true
 * @param summary     상태별 항목 개수
 * @param items       KWCAG 2.2 항목별 결과 (33개)
 * @param sourceLines Jsoup으로 정리한 HTML을 줄 단위로 나눈 목록 (Issue.line과 연결)
 */
public record CheckResponse(
        String url,
        LocalDateTime checkedAt,
        boolean passed,
        Summary summary,
        List<ItemResult> items,
        List<String> sourceLines
) {

    /**
     * 항목 결과와 HTML 줄 목록을 받아 응답 생성
     * — 검사 시각, 요약, 통과 여부는 자동으로 채움
     */
    public static CheckResponse of (String url, List<ItemResult> items, List<String> sourceLines) {
        Summary summary = Summary.from(items);

        return new CheckResponse(
                url,
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                summary.fail() == 0,
                summary,
                List.copyOf(items),
                List.copyOf(sourceLines)
        );
    }

}
