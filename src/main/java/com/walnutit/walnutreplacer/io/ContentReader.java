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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Krentzlin
 *
 */
public class ContentReader {

	private static final Logger LOG = LoggerFactory
			.getLogger(ContentReader.class);
	
	private ContentReader() {
		
	}

	public static String readContent(File file) {
		FileReader fileReader = null;

		StringBuilder sb = new StringBuilder();

		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			String msg = "Could not find config file: "
					+ file.getAbsolutePath();
			LOG.error(msg, e);
		}

		int i;
		if (fileReader != null) {

			try {
				while ((i = fileReader.read()) != -1) {
					sb.append((char) i);
				}
			} catch (IOException e) {
				String msg = "Could not read config file: "
						+ file.getAbsolutePath();
				LOG.error(msg, e);
			} finally {
				try {
					fileReader.close();
				} catch (IOException e) {
					LOG.error("Could not close filereader stream", e);
				}
			}
			return sb.toString();
		}
		throw new NullPointerException();
	}

}
