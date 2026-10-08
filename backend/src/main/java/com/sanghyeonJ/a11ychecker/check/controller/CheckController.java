package com.sanghyeonJ.a11ychecker.check.controller;

import com.sanghyeonJ.a11ychecker.check.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "check", description = "웹 접근성 검사 API")
@RestController
@RequestMapping("/api/check")
public class CheckController {

    @Operation(summary = "단일 페이지 검사", description = "URL 하나를 KWCAG 2.2 기준으로 검사합니다.")
    @PostMapping
    public CheckResponse check (@Valid @RequestBody CheckRequest request) {
        return createDummyResponse(request.url());
    }

    /**
     * JSON 모양 확인용 가짜 응답 (임시)
     */
    private CheckResponse createDummyResponse (String url) {
        List<String> sourceLines = List.of(
                "<!doctype html>",
                "<html>",
                "  <head>",
                "    <title>테스트 페이지</title>",
                "  </head>",
                "  <body>",
                "    <img src=\"logo.png\">",
                "    <img src=\"banner.png\" alt=\"이미지\">",
                "  </body>",
                "</html>"
        );

        List<ItemResult> items = List.of(
                // 자동 검사 + FAIL과 WARNING이 섞인 항목 → FAIL
                ItemResult.automatic("5.1.1", "적절한 대체 텍스트 제공", List.of(
                        new Issue(Severity.FAIL, 7, "img 요소에 alt 속성이 없습니다.", "<img src=\"logo.png\">"),
                        new Issue(Severity.WARNING, 8, "대체 텍스트가 의미 없는 단어입니다.", "<img src=\"banner.png\" alt=\"이미지\">")
                )),
                // 자동 검사 + 이슈 없음 → PASS
                ItemResult.automatic("5.3.1", "표의 구성", List.of()),
                // 수동확인 항목 → MANUAL
                ItemResult.manual("5.4.1", "색에 무관한 콘텐츠 인식"),
                // 자동 검사 + FAIL → FAIL
                ItemResult.automatic("7.1.1", "기본 언어 표시", List.of(
                        new Issue(Severity.FAIL, 2, "html 요소에 lang 속성이 없습니다.", "<html>")
                ))
        );

        return CheckResponse.of(url, items, sourceLines);

    }

}
