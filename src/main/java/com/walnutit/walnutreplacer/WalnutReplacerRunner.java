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
package com.walnutit.walnutreplacer;

import java.io.File;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.walnutit.walnutreplacer.domain.ValueReplacerImpl;
import com.walnutit.walnutreplacer.domain.model.ReplacerInputObject;
import com.walnutit.walnutreplacer.domain.model.ReplacerOutputObject;
import com.walnutit.walnutreplacer.io.ContentReader;
import com.walnutit.walnutreplacer.io.WalnutFileReader;
import com.walnutit.walnutreplacer.io.WalnutFileWriter;
import com.walnutit.walnutreplacer.utils.KeyValueParser;
import com.walnutit.walnutreplacer.utils.ParameterHandler;

/**
 * @author Daniel Krentzlin
 * 
 *         A config file needs to have following structure:
 * 
 *         file.update=<path> <key>=<value> ... file.update=<path> <key>=<value>
 *         ...
 * 
 */
public class WalnutReplacerRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		String configFilePath = ParameterHandler.getParameter(args);
		File file = WalnutFileReader.readFile(configFilePath);
		String configuration = ContentReader.readContent(file);
		List<List<ReplacerInputObject>> sets = KeyValueParser
				.prepareReplaceObjects(configuration);

		for (List<ReplacerInputObject> set : sets) {
			ValueReplacerImpl replacer = ValueReplacerImpl
					.getValueReplacerImpl(set);

			ReplacerOutputObject obj = replacer.replace();
			WalnutFileWriter.writeFile(obj.getContent(), obj.getFile());
		}

	}

}
