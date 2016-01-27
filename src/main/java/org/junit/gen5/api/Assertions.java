
package org.junit.gen5.api;

import java.util.function.Supplier;

public final class Assertions {

	private Assertions() {
		/* no-op */
	}

	public static void fail(String message) {
		throw new RuntimeException(message);
	}

	public static void fail(Supplier<String> messageSupplier) {
		fail(nullSafeGet(messageSupplier));
	}

	private static String nullSafeGet(Supplier<String> messageSupplier) {
		return (messageSupplier != null ? messageSupplier.get() : null);
	}

}
