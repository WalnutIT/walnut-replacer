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

import com.walnutit.walnutreplacer.exceptions.EmptyStackException;
import com.walnutit.walnutreplacer.exceptions.ToManyArgumentsException;

/**
 * @author Daniel Krentzlin
 *
 */
public class ParameterHandler {
	
	private ParameterHandler() {

	}

	public static String getParameter(String[] args) {

		if (args.length == 1) {
			return args[0];
		} else if (args.length == 0) {
			throw new EmptyStackException();
		} else {
			throw new ToManyArgumentsException();
		}
	}
	
	

}
