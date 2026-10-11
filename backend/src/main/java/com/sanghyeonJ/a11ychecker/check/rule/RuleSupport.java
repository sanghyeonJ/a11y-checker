package com.sanghyeonJ.a11ychecker.check.rule;

import com.sanghyeonJ.a11ychecker.check.dto.Issue;
import com.sanghyeonJ.a11ychecker.check.dto.Severity;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Range;

/**
 * 검사 규칙들이 공통으로 쓰는 헬퍼
 */
final class RuleSupport {

    static final int SNIPPET_MAX_LENGTH = 150;
    static final String ELLIPSIS = "\u2026"; // … (말줄임표 1글자)

    private RuleSupport() {

    }

    /**
     * 요소가 원본 HTML에서 시작하는 줄 번호 (1부터)
     *
     * @return 원본에 없는 요소(파서가 자동 생성)면 null
     */
    static Integer lineOf(Element element) {
        Range range = element.sourceRange();
        if (!range.isTracked() || range.isImplicit()) {
            return null;
        }
        return range.start().lineNumber();
    }

    /**
     * 목록 미리보기용 HTML 일부 (공백 정리 + 길이 제한)
     */
    static String snippetOf(Element element) {
        String html = element.outerHtml().replaceAll("\\s+", " ").trim();
        if (html.length() <= SNIPPET_MAX_LENGTH) {
            return html;
        }
        return html.substring(0, SNIPPET_MAX_LENGTH) + ELLIPSIS;
    }

    /**
     * 요소 하나에 대한 이슈 생성 (줄 번호·snippet 자동 계산)
     */
    static Issue issue(Severity severity, Element element, String message) {
        return new Issue(severity, lineOf(element), message, snippetOf(element));
    }

}
