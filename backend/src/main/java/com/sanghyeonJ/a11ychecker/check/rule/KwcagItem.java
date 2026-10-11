package com.sanghyeonJ.a11ychecker.check.rule;

/**
 * KWCAG 2.2 검사 항목 33개
 * <p>
 * 선언 순서 = 응답 items 순서 (항목 번호 순)
 */
public enum KwcagItem {

    // 5. 인식의 용이성
    ALT_TEXT("5.1.1", "적절한 대체 텍스트 제공"),
    CAPTIONS("5.2.1", "자막 제공"),
    TABLE_STRUCTURE("5.3.1", "표의 구성"),
    LINEAR_STRUCTURE("5.3.2", "콘텐츠의 선형구조"),
    CLEAR_INSTRUCTIONS("5.3.3", "명확한 지시사항 제공"),
    USE_OF_COLOR("5.4.1", "색에 무관한 콘텐츠 인식"),
    NO_AUTOPLAY("5.4.2", "자동 재생 금지"),
    CONTRAST("5.4.3", "텍스트 콘텐츠의 명도 대비"),
    CONTENT_DISTINCTION("5.4.4", "콘텐츠 간의 구분"),

    // 6. 운용의 용이성
    KEYBOARD("6.1.1", "키보드 사용 보장"),
    FOCUS("6.1.2", "초점 이동과 표시"),
    OPERABLE("6.1.3", "조작 가능"),
    CHARACTER_SHORTCUTS("6.1.4", "문자 단축키"),
    TIMING_ADJUSTABLE("6.2.1", "응답시간 조절"),
    PAUSE_STOP("6.2.2", "정지 기능 제공"),
    FLASHING("6.3.1", "깜빡임과 번쩍임 사용 제한"),
    BYPASS_BLOCKS("6.4.1", "반복 영역 건너뛰기"),
    TITLES("6.4.2", "제목 제공"),
    LINK_TEXT("6.4.3", "적절한 링크 텍스트"),
    FIXED_REFERENCE("6.4.4", "고정된 참조 위치 정보"),
    SINGLE_POINTER("6.5.1", "단일 포인터 입력 지원"),
    POINTER_CANCELLATION("6.5.2", "포인터 입력 취소"),
    LABEL_IN_NAME("6.5.3", "레이블과 네임"),
    MOTION_ACTUATION("6.5.4", "동작기반 작동"),

    // 7. 이해의 용이성
    LANGUAGE("7.1.1", "기본 언어 표시"),
    ON_USER_REQUEST("7.2.1", "사용자 요구에 따른 실행"),
    CONSISTENT_HELP("7.2.2", "찾기 쉬운 도움 정보"),
    ERROR_CORRECTION("7.3.1", "오류 정정"),
    LABELS("7.3.2", "레이블 제공"),
    ACCESSIBLE_AUTH("7.3.3", "접근 가능한 인증"),
    REDUNDANT_ENTRY("7.3.4", "반복 입력 정보"),

    // 8. 견고성
    MARKUP_ERRORS("8.1.1", "마크업 오류 방지"),
    WEB_APP_ACCESSIBILITY("8.2.1", "웹 애플리케이션 접근성 준수");

    private final String code;
    private final String title;

    KwcagItem(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

}
