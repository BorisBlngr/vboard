package com.vsct.vboard.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LabelsTest {

    @Nested
    @DisplayName("Method toString()")
    class ToString {

        @Test
        public void should_return_joined_elements() {
            String result = new Labels("label1", "label2").toString();

            assertThat(result).isEqualTo("label1, label2");
        }


        @Test
        public void should_ignore_char_if_empty() {
            String result = new Labels("", "").toString();

            assertThat(result).isEqualTo("");
        }


        @Test
        public void should_return_empty_char_if_null() {
            String result = new Labels((String) null).toString();

            assertThat(result).isEqualTo("");
        }


        @Test
        public void should_return_empty_char_if_null_array() {
            String result = new Labels((String[]) null).toString();

            assertThat(result).isEqualTo("");
        }

        @Test
        public void should_ignore_null_element() {
            String result = new Labels("label1", null, "label2").toString();

            assertThat(result).isEqualTo("label1, label2");
        }
    }


}
