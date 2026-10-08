package com.sanghyeonJ.a11ychecker.check.dto;

/**
 * 검사 항목(KWCAG 2.2 항목 하나)의 최종 상태
 */
public enum ItemStatus {

    /** 자동 검사 결과 문제 없음 */
    PASS,

    /** FAIL 이슈가 1건 이상 있음 */
    FAIL,

    /** FAIL은 없고 WARNING 이슈만 있음 */
    WARNING,

    /** 자동 검사 불가 → 사람이 직접 확인해야 함 */
    MANUAL
}