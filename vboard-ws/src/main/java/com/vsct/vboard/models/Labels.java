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

import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Labels {
    private final String delimiter = ", ";
    private List<Label> labels = new ArrayList<>();

    public Labels(String... labels) {
        Optional.ofNullable(labels)
                .ifPresent(value -> this.labels = Arrays.stream(value)
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank)
                        .map(Label::new)
                        .collect(toList()));
    }

    List<String> toStringList() {
        return labels.stream()
                .map(Label::getLabelName)
                .collect(toList());
    }

    @Override
    public String toString() {
        return this.labels.stream()
                .map(Label::getLabelName)
                .collect(joining(delimiter));
    }
}
