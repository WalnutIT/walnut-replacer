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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.walnutit.walnutreplacer.exceptions.EmptyStackException;
import com.walnutit.walnutreplacer.exceptions.ToManyArgumentsException;



/**
 * @author Daniel Krentzlin
 *
 */
class ParameterHandlerTest {

	/**
	 * Test method for {@link com.walnutit.replacer.utils.ParameterHandler#getCommand(java.lang.String[])}.
	 */
	
	@DisplayName("Test of command reader")
	@ParameterizedTest(name = "{displayName}")
	@MethodSource("getTestCommands")
	void testGetParameter(String[] oneArg, String[] emptyList,
			String[] toManyArgs) {

		// TODO should be separated in three single tests

		// given oneArg, emptyList, toManyArgs
		String expectedString = "config.dat";

		// when
		String actualString = ParameterHandler.getParameter(oneArg);

		// then
		Assertions.assertThrows(EmptyStackException.class,
				() -> ParameterHandler.getParameter(emptyList));

		assertEquals(expectedString, actualString);

		Assertions.assertThrows(ToManyArgumentsException.class,
				() -> ParameterHandler.getParameter(toManyArgs));

	}
	
	static Stream<Arguments> getTestCommands() {

		String[] positivTest = new String[] { "config.dat" };
		String[] noArgsTest = new String[] {};
		String[] toManyArgsTest = new String[] { "config1.dat",
				"config2.dat" };

		return Stream.of(Arguments.of(positivTest, noArgsTest,
				toManyArgsTest));
	}

}
