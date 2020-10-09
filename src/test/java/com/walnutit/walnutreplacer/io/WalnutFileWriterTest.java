/**
 * Copyright 2020 - 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this testFile except in compliance with the License.
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Daniel Krentzlin
 *
 */
class WalnutFileWriterTest {
	private static final String FILE_PATH = "testFile";
	private static final String DIR_PATH = "testPath";
	private File testFile = null;
	private File testDir = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testFile = new File(FILE_PATH);
		testFile.createNewFile();
		testDir = new File(DIR_PATH);
		testDir.mkdir();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		testFile.delete();
		testDir.delete();
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.io.WalnutFileWriter#writeFile(java.lang.String, java.io.File)}.
	 */
	@Test
	final void testWriteFile() {
		// given
		String expectedContent = "FileWriter(File file) – Constructs a FileWriter object given a File object.\r\n"
				+ "FileWriter (File file, boolean append) – constructs a FileWriter object given a File object.\r\n"
				+ "FileWriter (FileDescriptor fd) – constructs a FileWriter object associated with a file descriptor.\r\n"
				+ "FileWriter (String fileName) – constructs a FileWriter object given a file name.\r\n"
				+ "FileWriter (String fileName, Boolean append) – Constructs a FileWriter object given a file name with a Boolean indicating whether or not to append the data written.";

		// when
		boolean result = WalnutFileWriter.writeFile(expectedContent,
				testFile);
		File actualFile = WalnutFileReader.readFile(FILE_PATH);
		String actualContent = ContentReader.readContent(actualFile);
		// then
		assertTrue(result);
		assertEquals(expectedContent, actualContent);
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.io.WalnutFileWriter#writeFile(java.lang.String, java.io.File)}.
	 */
	@Test
	final void testWriteFileCannotOpenFile() {
		// given
		String expectedContent = "FileWriter(File file) – Constructs a FileWriter object given a File object.\r\n"
				+ "FileWriter (File file, boolean append) – constructs a FileWriter object given a File object.\r\n"
				+ "FileWriter (FileDescriptor fd) – constructs a FileWriter object associated with a file descriptor.\r\n"
				+ "FileWriter (String fileName) – constructs a FileWriter object given a file name.\r\n"
				+ "FileWriter (String fileName, Boolean append) – Constructs a FileWriter object given a file name with a Boolean indicating whether or not to append the data written.";

		// when - none

		// then
		assertFalse(
				WalnutFileWriter.writeFile(expectedContent, testDir));
	}
	

}
