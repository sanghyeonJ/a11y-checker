package com.sanghyeonJ.a11ychecker.check.rule;

import com.sanghyeonJ.a11ychecker.check.dto.Issue;
import com.sanghyeonJ.a11ychecker.check.dto.Severity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RuleSupportTest {

    private Document parse(String html) {
        return Jsoup.parse(html, "", Parser.htmlParser().setTrackPosition(true));
    }

    @Test
    void 원본에_있는_요소는_줄번호를_돌려준다() {
        String html = """
                <!doctype html>
                <html lang="ko">
                <body>
                <img src="a.png">
                </body>
                </html>
                """;
        Element img = parse(html).selectFirst("img");

        assertThat(RuleSupport.lineOf(img)).isEqualTo(4);
    }

    @Test
    void 파서가_자동으로_만든_요소는_null을_돌려준다() {
        Element html = parse("<p>hi</p>").selectFirst("html");

        assertThat(RuleSupport.lineOf(html)).isNull();
    }

    @Test
    void snippet은_한_줄로_정리된다() {
        Element ul = parse("<ul>\n  <li>a</li>\n  <li>b</li>\n</ul>").selectFirst("ul");

        String snippet = RuleSupport.snippetOf(ul);

        assertThat(snippet).doesNotContain("\n").startsWith("<ul>");
    }

    @Test
    void 긴_snippet은_잘리고_말줄임표가_붙는다() {
        Element p = parse("<p>" + "가".repeat(300) + "</p>").selectFirst("p");

        String snippet = RuleSupport.snippetOf(p);

        assertThat(snippet)
                .hasSize(RuleSupport.SNIPPET_MAX_LENGTH + 1)
                .endsWith(RuleSupport.ELLIPSIS);
    }

    @Test
    void issue는_줄번호와_snippet을_채워서_만든다() {
        Element img = parse("<img src=\"a.png\">").selectFirst("img");

        Issue issue = RuleSupport.issue(Severity.FAIL, img, "메시지");

        assertThat(issue.severity()).isEqualTo(Severity.FAIL);
        assertThat(issue.line()).isEqualTo(1);
        assertThat(issue.message()).isEqualTo("메시지");
        assertThat(issue.snippet()).isEqualTo(RuleSupport.snippetOf(img));
    }
}