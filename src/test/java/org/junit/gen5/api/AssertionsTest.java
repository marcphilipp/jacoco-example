/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.api;

import static org.junit.gen5.api.Assertions.fail;

import java.util.function.Supplier;

import org.junit.Test;

public class AssertionsTest {

	@Test
	public void failWithString() {
		try {
			fail("test");
			expectRuntimeException();
		}
		catch (RuntimeException ex) {
			assertMessageEquals(ex, "test");
		}
	}

	@Test
	public void failWithMessageSupplier() {
		try {
			fail(() -> "test");
			expectRuntimeException();
		}
		catch (RuntimeException ex) {
			assertMessageEquals(ex, "test");
		}
	}

	@Test
	public void failWithNullString() {
		try {
			fail((String) null);
			expectRuntimeException();
		}
		catch (RuntimeException ex) {
			assertMessageIsNull(ex);
		}
	}

	@Test
	public void failWithNullMessageSupplier() {
		try {
			fail((Supplier<String>) null);
			expectRuntimeException();
		}
		catch (RuntimeException ex) {
			assertMessageIsNull(ex);
		}
	}

	private static void expectRuntimeException() {
		throw new AssertionError("Should have thrown an " + RuntimeException.class.getName());
	}

	private static void assertMessageIsNull(RuntimeException ex) {
		if (ex.getMessage() != null) {
			throw new AssertionError("Message in AssertionError should be null: " + ex.getMessage());
		}
	}

	private static void assertMessageEquals(RuntimeException ex, String msg) {
		if (!msg.equals(ex.getMessage())) {
			throw new AssertionError(
				"Message in AssertionError should be [" + msg + "], but was [" + ex.getMessage() + "].");
		}
	}

}
