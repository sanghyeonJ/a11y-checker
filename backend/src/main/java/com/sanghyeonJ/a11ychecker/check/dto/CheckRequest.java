package com.sanghyeonJ.a11ychecker.check.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 단일 페이지 검사 요청
 *
 * @param url 검사할 페이지 URL (http/https만 허용)
 */
public record CheckRequest(
        @Schema(description = "감사할 페이지 URL", example = "https://example.com")
        @NotBlank(message = "URL을 입력해 주세요.")
        @Size(max = 2048, message = "URL은 2048자 이하로 입력해 주세요.")
        @Pattern(regexp = "^(?i)https?://.+", message = "http:// 또는 https://로 시작하는 URL을 입력해 주세요.")
        String url
) {
}
