package com.sanghyeonJ.a11ychecker.check.dto;

/**
 * 개별 이슈(문제 요소 하나)의 심각도
 */
public enum Severity {

    /** 명확한 위반 (예: img에 alt 속성 없음) */
    FAIL,

    /** 위반 가능성이 있어 사람이 확인해야 함 (예: alt="이미지"처럼 의미 없는 대체 텍스트) */
    WARNING
}
