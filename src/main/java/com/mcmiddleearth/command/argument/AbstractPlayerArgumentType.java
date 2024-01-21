/*
 * Copyright (C) 2020 MCME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mcmiddleearth.command.argument;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Eriol_Eandur
 */

public abstract class AbstractPlayerArgumentType extends AbstractStringSuggestionListArgumentType {

    public AbstractPlayerArgumentType() {
        setTooltip("any player");
    }

    @Override
    public Collection<String> getExamples() {
        return Collections.singletonList("Eriol_Eandur");
    }

    protected abstract Collection<String> getPlayerSuggestions();

    @Override
    protected Collection<String> getSuggestions() {
        return getPlayerSuggestions();
    }
}
