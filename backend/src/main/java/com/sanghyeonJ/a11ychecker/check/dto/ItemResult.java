package com.sanghyeonJ.a11ychecker.check.dto;

import java.util.List;

/**
 * KWCAG 2.2 검사 항목 하나의 결과
 *
 * @param code   항목 번호 (예: "5.1.1")
 * @param title  항목 이름 (예: "적절한 대체 텍스트 제공")
 * @param auto   자동 검사 대상이면 true, 수동확인 항목이면 false
 * @param status 항목 최종 상태
 * @param issues 발견된 문제 목록 (없으면 빈 배열)
 */
public record ItemResult(
        String code,
        String title,
        boolean auto,
        ItemStatus status,
        List<Issue> issues
) {

    /**
     * 자동 검사 항목의 결과 생성 — 상태는 이슈 목록으로 자동 결정
     */
    public static ItemResult automatic (String code, String title, List<Issue> issues) {
        return new ItemResult(code, title, true, decideStatus(issues), List.copyOf(issues));
    }

    /**
     * 수동확인 항목의 결과 생성 — 항상 MANUAL, 이슈 없음
     */
    public static ItemResult manual (String code, String title) {
        return new ItemResult(code, title, false, ItemStatus.MANUAL, List.of());
    }

    /**
     * 상태 결정 규칙: FAIL이 하나라도 있으면 FAIL → WARNING만 있으면 WARNING → 없으면 PASS
     */
    private static ItemStatus decideStatus(List<Issue> issues) {
        // 이슈 목록 중에 심각도가 FAIL인 게 하나라도 있으면 true
        boolean hasFail = issues.stream()
                .anyMatch(issue -> issue.severity() == Severity.FAIL);

        if (hasFail) {
            return ItemStatus.FAIL;
        }
        if (!issues.isEmpty()) {
            return ItemStatus.WARNING;
        }
        return ItemStatus.PASS;
    }

}
