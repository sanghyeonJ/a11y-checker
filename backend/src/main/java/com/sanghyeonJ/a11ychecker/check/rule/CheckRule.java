package com.sanghyeonJ.a11ychecker.check.rule;

import com.sanghyeonJ.a11ychecker.check.dto.Issue;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * 자동 검사 규칙 (규칙 1개 = KWCAG 항목 1개)
 * <p>
 * 구현 클래스는 @Component를 붙여 스프링 빈으로 등록한다.
 * <ul>
 *   <li>document를 수정하지 않는다 (모든 규칙이 같은 문서를 공유)</li>
 *   <li>필드에 검사 상태를 저장하지 않는다 (여러 요청이 같은 객체를 동시에 사용)</li>
 * </ul>
 */
public interface CheckRule {

    /**
     * 이 규칙이 검사하는 항목
     */
    KwcagItem item();

    /**
     * @param document 원본 HTML을 위치 추적(setTrackPosition)을 켜고 파싱한 문서
     * @return 발견한 문제 목록, 문제가 없으면 빈 목록 (null 금지)
     */
    List<Issue> check(Document document);

}
