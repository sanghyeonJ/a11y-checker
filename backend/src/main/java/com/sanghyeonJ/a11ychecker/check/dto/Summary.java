package com.sanghyeonJ.a11ychecker.check.dto;

import java.util.List;

/**
 * 검사 항목 상태별 개수 요약 (네 값을 더하면 전체 항목 수)
 *
 * @param pass    PASS 항목 수
 * @param fail    FAIL 항목 수
 * @param warning WARNING 항목 수
 * @param manual  MANUAL 항목 수
 */
public record Summary(
        int pass,
        int fail,
        int warning,
        int manual
) {

    /**
     * 항목 결과 목록을 받아 상태별 개수를 세서 Summary 생성
     */
    public static Summary from (List<ItemResult> items) {
        return new Summary(
                count(items, ItemStatus.PASS),
                count(items, ItemStatus.FAIL),
                count(items, ItemStatus.WARNING),
                count(items, ItemStatus.MANUAL)
        );
    }

    /**
     * 특정 상태인 항목 개수 세기
     */
    private static int count (List<ItemResult> items, ItemStatus status) {
        return (int) items.stream()
                .filter(item -> item.status() == status)
                .count();
    }

}
