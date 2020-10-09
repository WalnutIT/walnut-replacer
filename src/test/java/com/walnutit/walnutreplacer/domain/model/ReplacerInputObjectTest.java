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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Daniel Krentzlin
 *
 */
class ReplacerInputObjectTest {

	ReplacerInputObject inputObj = null;
	String actualFilePath = "testFilePath";
	String actualKey = "testKey";
	String acutalValue = "testValue";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		inputObj = ReplacerInputObject.createReplaceInputObject(
				actualFilePath, actualKey, acutalValue);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.model.ReplacerInputObject#createReplaceInputObject(java.lang.String, java.lang.String)}.
	 */
	@Test
	final void testCreateReplaceInputObject() {
		assertNotNull(inputObj);
	}
	
	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.model.ReplacerInputObject#getFilePath()}.
	 */
	@Test
	final void testGetFilePath() {
		assertEquals(actualFilePath, inputObj.getFilePath());
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.model.ReplacerInputObject#getKey()}.
	 */
	@Test
	final void testGetKey() {
		assertEquals(actualKey, inputObj.getKey());
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.model.ReplacerInputObject#getValue()}.
	 */
	@Test
	final void testGetValue() {
		assertEquals(acutalValue, inputObj.getValue());
	}

}
