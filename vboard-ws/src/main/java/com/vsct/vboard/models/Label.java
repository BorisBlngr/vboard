/*
 * This file is part of the vboard distribution.
 * (https://github.com/voyages-sncf-technologies/vboard)
 * Copyright (c) 2017 VSCT.
 *
 * vboard is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, version 3.
 *
 * vboard is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.vsct.vboard.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsct.vboard.utils.SerializationError;
import com.vsct.vboard.utils.StaticContextAccessor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.text.Normalizer;

import static java.text.Normalizer.Form.NFD;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @NotBlank
    @Pattern(regexp = "^[^,]+$", message = "Must not contain a comma")
    private final String labelName;

    // Default constructor for Hibernate
    private Label() {
        this(null);
    }

    public Label(String labelName) {
        if (labelName == null) {
            this.labelName = null;
            return;
        }

        final String nonASCII = "[^\\p{ASCII}]";
        final String specialCharacter = "[^a-zA-Z0-9\\s+]";
        this.labelName = Normalizer
                .normalize(labelName, NFD)
                .replaceAll(nonASCII, "")
                .replaceAll(specialCharacter, "")
                .toLowerCase().trim();
    }

    public String getLabelName() {
        return this.labelName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Label label = (Label) o;

        return labelName != null ? labelName.equals(label.labelName) : label.labelName == null;
    }

    @Override
    public int hashCode() {
        return labelName != null ? labelName.hashCode() : 0;
    }

    @Override
    public String toString() {
        try {
            return StaticContextAccessor.getBean(ObjectMapper.class).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new SerializationError(e);
        }
    }
}
