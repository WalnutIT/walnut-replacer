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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.walnutit.walnutreplacer.domain.model.ReplacerInputObject;

/**
 * @author Daniel Krentzlin
 *
 */
class KeyValueParserTest {

	List<List<ReplacerInputObject>> expected = null;
	
	@BeforeEach
	public void setUp() {

		expected = new ArrayList<>();
		List<ReplacerInputObject> set1 = initSet1();
		List<ReplacerInputObject> set2 = initSet2();

		expected.add(set1);
		expected.add(set2);
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.utils.KeyValueParser#prepareReplaceObjects(java.lang.String)}.
	 */
	@ParameterizedTest
	@DisplayName("")
	@MethodSource("getConfigFile")
	final void testPrepareReplaceObjects(String configuration) {

		// given - config, expected

		// when
		List<List<ReplacerInputObject>> actual = KeyValueParser
				.prepareReplaceObjects(configuration);

		// then
		Iterator<List<ReplacerInputObject>> iteratorActual = actual
				.iterator();
		Iterator<List<ReplacerInputObject>> iteratorExpected = expected
				.iterator();

		while (iteratorActual.hasNext()
				&& iteratorExpected.hasNext()) {
			List<ReplacerInputObject> setActual = iteratorActual
					.next();
			List<ReplacerInputObject> setExpected = iteratorExpected
					.next();

			Iterator<ReplacerInputObject> actualInputObjectIterator = setActual
					.iterator();

			Iterator<ReplacerInputObject> expectedInputObjectIterator = setExpected
					.iterator();

			while (actualInputObjectIterator.hasNext()
					&& expectedInputObjectIterator.hasNext()) {

				ReplacerInputObject v1r = actualInputObjectIterator
						.next();
				ReplacerInputObject v2r = expectedInputObjectIterator
						.next();

				boolean resultKey = v1r.getKey().equals(v2r.getKey());

				boolean resultValue = v1r.getValue()
						.equals(v2r.getValue());

				assertTrue(resultKey && resultValue);
			}

		}
	}

	static Stream<Arguments> getConfigFile() {
		String config = "file.path=testDat1.dat\r\n"
				+ "${test.attribute.1}=1\r\n"
				+ "${test.attribute.2}=2\r\n"
				+ "file.path=testDat2.dat\r\n"
				+ "${test.attribute.3}=3\r\n"
				+ "${test.attribute.4}=4\r\n";

		return Stream.of(Arguments.of(config));

	}

	private List<ReplacerInputObject> initSet1() {
		ReplacerInputObject replacerInputObject1 = ReplacerInputObject
				.createReplaceInputObject("testDat1.dat",
						"${test.attribute.1}", "1");
		ReplacerInputObject replacerInputObject2 = ReplacerInputObject
				.createReplaceInputObject("testDat1.dat",
						"${test.attribute.2}", "2");
		List<ReplacerInputObject> set = new ArrayList<>();
		set.add(replacerInputObject1);
		set.add(replacerInputObject2);

		return set;
	}

	private List<ReplacerInputObject> initSet2() {
		ReplacerInputObject replacerInputObject3 = ReplacerInputObject
				.createReplaceInputObject("testDat2.dat",
						"${test.attribute.3}", "3");
		ReplacerInputObject replacerInputObject4 = ReplacerInputObject
				.createReplaceInputObject("testDat2.dat",
						"${test.attribute.4}", "4");
		List<ReplacerInputObject> set = new ArrayList<>();
		set.add(replacerInputObject3);
		set.add(replacerInputObject4);

		return set;
	}

}
