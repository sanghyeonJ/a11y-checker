package com.sanghyeonJ.a11ychecker.check;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsoupPositionTest {

    private Document parse(String html) {
        return Jsoup.parse(html, "", Parser.htmlParser().setTrackPosition(true));
    }

    @Test
    void 원본_HTML_기준_줄번호를_돌려준다() {
        String html = """
                <!doctype html>
                <html>
                <body>
                <p>hello</p>
                <img src="logo.png">
                </body>
                </html>
                """;

        Element img = parse(html).selectFirst("img");

        assertThat(img.sourceRange().start().lineNumber()).isEqualTo(5);
    }

    @Test
    void 파서가_자동으로_만든_요소는_위치가_없다() {
        Document doc = parse("<p>hi</p>");

        assertThat(doc.selectFirst("html").sourceRange().isImplicit()).isTrue();
        assertThat(doc.selectFirst("p").sourceRange().isImplicit()).isFalse();
        assertThat(doc.selectFirst("p").sourceRange().start().lineNumber()).isEqualTo(1);
    }
}