package com.vsct.vboard.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class LabelTest {

    @ParameterizedTest(name = "\"{0}\" should return \"{1}\"")
    @CsvSource({"HellO,hello", "été,ete", "'#hello',hello", "' hello ',hello", "'♩',''"})
    public void should_normalize_label(String label, String labelExpected) {
        final String result = new Label(label).getLabelName();

        Assertions.assertThat(result).isEqualTo(labelExpected);

    }
}
