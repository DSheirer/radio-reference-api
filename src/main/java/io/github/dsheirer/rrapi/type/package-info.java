/*
 * *****************************************************************************
 * Copyright (C) 2019 Dennis Sheirer
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

/**
 * Data types of the web service: countries, states, counties, agencies and their frequencies, trunked radio systems
 * with their sites and talkgroups, FCC license data, and the lookup tables (modes, tags, system types, flavors and
 * voices).
 *
 * <p>Summary types such as {@link io.github.dsheirer.rrapi.type.County} identify an entity; the matching detail types
 * such as {@link io.github.dsheirer.rrapi.type.CountyInfo} come from a separate request.  Frequencies are in MHz and
 * coordinates in decimal degrees.  The types also include helpers that the service doesn't provide:
 * {@link io.github.dsheirer.rrapi.type.Coverage} and {@link io.github.dsheirer.rrapi.type.FrequencyConversion}.</p>
 */
package io.github.dsheirer.rrapi.type;