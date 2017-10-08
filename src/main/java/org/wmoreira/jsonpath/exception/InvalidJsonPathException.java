package org.wmoreira.jsonpath.exception;

import com.jayway.jsonpath.InvalidPathException;

public class InvalidJsonPathException extends RuntimeException {
	private static final long serialVersionUID = -7127901811292767313L;

	public InvalidJsonPathException(final InvalidPathException ipe) {
		super(ipe);
	}

}
