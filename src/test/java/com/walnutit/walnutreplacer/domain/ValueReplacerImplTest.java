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
package com.walnutit.walnutreplacer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.walnutit.walnutreplacer.domain.model.ReplacerInputObject;
import com.walnutit.walnutreplacer.domain.model.ReplacerOutputObject;

/**
 * @author Daniel Krentzlin
 *
 */
class ValueReplacerImplTest {

	List<ReplacerInputObject> inputObjects;
	File expectedFile = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		String filePath = "testFilePath";
		String content = "����   7 /  2com/walnutit/db/integration/persistence/DatabaseIT  java/lang/Object URL Ljava/lang/String; \r\n"
				+ "ConstantValue\b 	 ${test.lbr.db.url} 	USER_NAME\b \f ${test.lbr.db.userName} \bPASSWORD\b  ${test.lbr.db.password} <init> ()V Code\r\n"
				+ "  \f   LineNumberTable LocalVariableTable this 4Lcom/walnutit/db/integration/persistence/DatabaseIT; testCreateDbConnection \r\n"
				+ "Exceptions  java/sql/SQLException RuntimeVisibleAnnotations Lorg/junit/jupiter/api/Test;\r\n"
				+ "   \" ! $com/walnutit/db/persistence/Database\f # $ createDbConnection _(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lliquibase/database/DatabaseConnection;\r\n"
				+ " & ( ' org/junit/Assert\f ) * \r\n"
				+ "assertNotNull (Ljava/lang/Object;)V dbCon 'Lliquibase/database/DatabaseConnection; \r\n"
				+ "SourceFile DatabaseIT.java                \b  \r\n"
				+ "         \r\n"
				+ "                 /     *� �                \f                              S     \b� L+� %�           2  3  2 \r\n"
				+ " 6  7             \r\n"
				+ "  + ,   -    .";
		// create file

		expectedFile = new File(filePath);
		expectedFile.createNewFile();

		FileOutputStream oFile = new FileOutputStream(expectedFile,
				false);
		oFile.write(content.getBytes());
		oFile.flush();
		oFile.close();

		// create list

		inputObjects = new ArrayList<>();

		inputObjects.add(ReplacerInputObject.createReplaceInputObject(
				filePath, "${test.lbr.db.url}", "testUrl"));
		inputObjects.add(ReplacerInputObject.createReplaceInputObject(
				filePath, "${test.lbr.db.userName}", "testUserName"));
		inputObjects.add(ReplacerInputObject.createReplaceInputObject(
				filePath, "${test.lbr.db.password}", "testPassword"));

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		// expectedFile.delete();
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.ValueReplacerImpl#getValueReplacerImpl(java.util.Set)}.
	 */
	@Test
	final void testGetValueReplacerImpl() {

		// given
		ValueReplacerImpl vRI = null;

		// when
		vRI = ValueReplacerImpl.getValueReplacerImpl(inputObjects);

		// then
		assertNotNull(vRI);
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.ValueReplacerImpl#replace()}.
	 */
	@ParameterizedTest
	@DisplayName("")
	@ValueSource(strings = { "testUrl", "testUserName",
			"testPassword" })
	final void testReplaceContent(String expected) {
		// given
		ValueReplacerImpl vRI = null;
		// when
		vRI = ValueReplacerImpl.getValueReplacerImpl(inputObjects);
		ReplacerOutputObject actual = vRI.replace();

		// then
		assertTrue(actual.getContent().contains(expected));
	}

	/**
	 * Test method for
	 * {@link com.walnutit.replacer.domain.ValueReplacerImpl#replace()}.
	 */
	@ParameterizedTest
	@DisplayName("")
	@ValueSource(strings = { "testFilePath" })
	final void testReplaceFile(String expected) {
		// given
		ValueReplacerImpl vRI = null;
		// when
		vRI = ValueReplacerImpl.getValueReplacerImpl(inputObjects);
		ReplacerOutputObject actual = vRI.replace();

		// then
		assertEquals(expected, actual.getFile().getPath());
	}

}
