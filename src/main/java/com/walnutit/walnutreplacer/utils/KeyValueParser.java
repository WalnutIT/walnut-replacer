/**
 * Copyright 2020 - 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walnutit.walnutreplacer.utils;

import java.util.ArrayList;
import java.util.List;

import com.walnutit.walnutreplacer.domain.model.ReplacerInputObject;

/**
 * @author Daniel Krentzlin
 *
 */
public class KeyValueParser {

	private static final String NEW_FILE = "file.path";

	private KeyValueParser() {

	}

	public static List<List<ReplacerInputObject>> prepareReplaceObjects(
			String configuration) {

		String trimedConfiguration = configuration.trim();
		String[] lines = trimedConfiguration.split("\\r?\\n");
		List<List<ReplacerInputObject>> sets = new ArrayList<>();
		List<ReplacerInputObject> replacerInputObjects = null;
		String filePath = null;
		for (int idx = 0; idx < lines.length; idx++) {

			if (lines[idx].startsWith(NEW_FILE)) {
				if (replacerInputObjects != null) {
					sets.add(replacerInputObjects);
				}
				replacerInputObjects = new ArrayList<>();
				String[] keyValue = lines[idx].split("=");
				filePath = keyValue[1];
			} else {

				String[] keyValue = lines[idx].split("=");
				String key = keyValue[0];
				String value = keyValue[1];
				ReplacerInputObject rIO = ReplacerInputObject
						.createReplaceInputObject(filePath, key,
								value);
				if (replacerInputObjects != null && rIO != null) {
					replacerInputObjects.add(rIO);
				} else {
					throw new NullPointerException();
				}

				if (idx == lines.length - 1) {
					sets.add(replacerInputObjects);
				}
			}

		}
		return sets;
	}

}
