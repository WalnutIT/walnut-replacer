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
package com.walnutit.walnutreplacer.domain.model;

/**
 * @author Daniel Krentzlin
 *
 */
public class ReplacerInputObject {

	private String filePath;
	private String key;
	private String value;

	public static ReplacerInputObject createReplaceInputObject(
			String filePath, String key, String value) {
		return new ReplacerInputObject(filePath, key, value);
	}

	private ReplacerInputObject(String filePath, String key,
			String value) {
		this.filePath = filePath;
		this.key = key;
		this.value = value;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
