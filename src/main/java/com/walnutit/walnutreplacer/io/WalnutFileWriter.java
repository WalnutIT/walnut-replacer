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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Krentzlin
 *
 */
public class WalnutFileWriter {

	private static final Logger LOG = LoggerFactory
			.getLogger(WalnutFileWriter.class);

	private WalnutFileWriter() {

	}

	public static boolean writeFile(String newContent, File file) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException e) {
			String msg = "Cannot open file: "
					+ file.getAbsolutePath();
			LOG.error(msg, e);
			return false;
		}

		try {
			fileWriter.write(newContent);
			return true;
		} catch (IOException e) {
			String msg = "Cannot write to file: "
					+ file.getAbsolutePath();
			LOG.error(msg, e);
			return false;
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				LOG.error("Cannot close fileWriter", e);
			}
		}

	}

}
