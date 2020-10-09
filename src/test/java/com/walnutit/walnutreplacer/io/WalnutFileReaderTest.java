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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Daniel Krentzlin
 *
 */
class WalnutFileReaderTest {

	private static final String TEST_FILE_PATH = "test_configuration.config";
	
	private static File expectedFile = null;

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
	 * {@link com.walnutit.replacer.io.WalnutFileReader#readFile(java.lang.String)}.
	 */
	@Test
	final void testReadFile() {
		// given - expectedFile

		// when

		File actualFile = WalnutFileReader.readFile(TEST_FILE_PATH);

		// then
		boolean result = actualFile.compareTo(expectedFile) == 0;
		assertTrue(result);
	}

}
