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
package com.walnutit.walnutreplacer.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Daniel Krentzlin
 *
 */
class ContentReaderTest {

	private static final String TEST_FILE_PATH = "test_configuration.config";
	private static final String TEST_FILE_PATH_NOT_EXISTS = "test_configuration_not_exists.config";
	private static File expectedFile = null;
	private static File notExistingFile = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		String config = "file.path=testDat1.dat\r\n"
				+ "${test.attribute.1}=1\r\n"
				+ "${test.attribute.2}=2\r\n"
				+ "file.path=testDat2.dat\r\n"
				+ "${test.attribute.3}=3\r\n"
				+ "${test.attribute.4}=4\r\n";

		// create a test file
		expectedFile = new File(TEST_FILE_PATH);
		expectedFile.createNewFile(); // if file already exists will do nothing
		FileOutputStream oFile = new FileOutputStream(expectedFile,
				false);
		oFile.write(config.getBytes());
		oFile.flush();
		oFile.close();

		notExistingFile = new File(TEST_FILE_PATH_NOT_EXISTS);
		notExistingFile.createNewFile();
		notExistingFile.delete();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// delete a testFile
		File file = new File(TEST_FILE_PATH);
		file.delete();
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.io.ContentReader#readContent(java.io.File)}.
	 */
	@ParameterizedTest
	@DisplayName("")
	@MethodSource("getConfig")
	final void testReadContent(String expected) {

		// given - expected

		// when

		String actual = ContentReader.readContent(expectedFile);

		// then
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.io.ContentReader#readContent(java.io.File)}.
	 */
	@ParameterizedTest
	@DisplayName("")
	@MethodSource("getConfig")
	final void testReadContentFileNotFound(String expected) {

		// given - expected

		// when

		// then
		Assertions.assertThrows(NullPointerException.class,
				() -> ContentReader.readContent(notExistingFile));
	}

	static Stream<Arguments> getConfig() {
		String config = "file.path=testDat1.dat\r\n"
				+ "${test.attribute.1}=1\r\n"
				+ "${test.attribute.2}=2\r\n"
				+ "file.path=testDat2.dat\r\n"
				+ "${test.attribute.3}=3\r\n"
				+ "${test.attribute.4}=4\r\n";

		return Stream.of(Arguments.of(config));
	}

}
