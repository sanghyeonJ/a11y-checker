package com.sanghyeonJ.a11ychecker.check.rule;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KwcagItemTest {

    private final List<String> codes = Arrays.stream(KwcagItem.values())
            .map(KwcagItem::code)
            .toList();

    @Test
    void 항목은_33개다() {
        assertThat(codes).hasSize(33);
    }

    @Test
    void 항목_번호는_중복되지_않는다() {
        assertThat(codes).doesNotHaveDuplicates();
    }

    @Test
    void 항목은_번호_순서로_선언되어_있다() {
        assertThat(codes).isSorted();
    }

}
