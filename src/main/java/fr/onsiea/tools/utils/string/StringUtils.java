/**
 * This file is part of Onsiea Utils project. (https://github.com/OnsieaStudio/OnsieaUtils)<br>
 * <br>
 * <p>
 * Onsiea Utils is [licensed] (https://github.com/OnsieaStudio/OnsieaUtils/blob/main/LICENSE) under the terms of the
 * "GNU GENERAL PUBLIC LICENSE v3 29 June 2007" (GPL-3).
 * https://github.com/OnsieaStudio/OnsieaUtils/wiki/License#license-and-copyright<br>
 * <br>
 * <p>
 * Onsiea Utils is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 2.1 of the License, or any later
 * version.<br>
 * <br>
 * <p>
 * Onsiea Utils is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU GENERAL PUBLIC LICENSE for more
 * details.<br>
 * <br>
 * <p>
 * You should have received a copy of the GNU GENERAL PUBLIC LICENSE along with Onsiea Utils. If not, see
 * <https://www.gnu.org/licenses/>.<br>
 * <br>
 *
 * <br>
 * Copyright 2021-2023 : Neither the name "Onsiea Studio", "Onsiea Utils", or any derivative name or the names of its
 * authors / contributors may be used to endorse or promote products derived from this software and even less to name
 * another project or other work without clear and precise permissions written in advance.<br>
 * <br>
 *
 * @Author : Seynax (https://github.com/seynax)<br>
 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
 */
package fr.onsiea.tools.utils.string;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtils
{
	public final static int appendAllNonNull(StringBuilder stringBuilderIn, String... contentsIn)
	{
		int changes = 0;

		for (var content : contentsIn)
		{
			if (appendNonNull(stringBuilderIn, content))
			{
				changes++;
			}
		}

		return changes;
	}

	public final static boolean appendNonNull(StringBuilder stringBuilderIn, String contentIn)
	{
		if (contentIn != null)
		{
			stringBuilderIn.append(contentIn);

			return true;
		}

		return false;
	}

	public final static int appendAllNonBlank(StringBuilder stringBuilderIn, String... contentsIn)
	{
		int changes = 0;

		for (var content : contentsIn)
		{
			if (appendNonBlank(stringBuilderIn, content))
			{
				changes++;
			}
		}

		return changes;
	}

	public final static boolean appendNonBlank(StringBuilder stringBuilderIn, String contentIn)
	{
		if (!isBlank(contentIn))
		{
			stringBuilderIn.append(contentIn);

			return true;
		}

		return false;
	}

	public final static boolean isBlank(String contentIn)
	{
		return contentIn == null || contentIn.isEmpty() || contentIn.isBlank() || contentIn.matches("($|[ \t\r\n]|^)+");
	}

	public final static String firstUpper(String contentIn)
	{
		return contentIn.substring(0, 1).toUpperCase() + contentIn.substring(1);
	}

	public final static String nonNull(String contentIn)
	{
		return contentIn != null ? contentIn : "";
	}

	public final static String prefixedNonNull(String contentIn, String prefixIn)
	{
		return contentIn != null ? prefixIn + contentIn : "";
	}

	public final static String suffixedNonNull(String contentIn, String suffixIn)
	{
		return contentIn != null ? contentIn + suffixIn : "";
	}

	public final static String enclaveNonNull(String contentIn, String prefixIn, String suffixIn)
	{
		return contentIn != null ? prefixIn + contentIn + suffixIn : "";
	}

	/**
	 * Interpret bytesIn and convert to string from UTF_8 Charset
	 *
	 * @return converted string from bytesIn
	 * @author Seynax
	 */
	public static StringBuilder toStringBuilder(final byte[] bytesIn)
	{
		return new StringBuilder(StringUtils.toString(bytesIn));
	}

	/**
	 * Interpret bytesIn and convert to string from UTF_8 Charset
	 *
	 * @return converted string from bytesIn
	 * @author Seynax
	 */
	public static String toString(final byte[] bytesIn)
	{
		return new String(bytesIn, StandardCharsets.UTF_8);
	}

	/**
	 * Interpret bytesIn and convert to string from charsetIn Charset
	 *
	 * @return converted string from bytesIn
	 * @author Seynax
	 */
	public static String toString(final byte[] bytesIn, final Charset charsetIn)
	{
		return new String(bytesIn, charsetIn);
	}

	/**
	 * @return raw string from bytesIn
	 * @author Seynax
	 */
	public static String toRawString(final byte[] bytesIn)
	{
		return StringUtils.toRawStringBuilder(bytesIn).toString();
	}

	/**
	 * @return raw string builder from bytesIn
	 * @author Seynax
	 */
	public static StringBuilder toRawStringBuilder(final byte[] bytesIn)
	{
		final var stringBuilder = new StringBuilder();

		for (final var b : bytesIn)
		{
			stringBuilder.append(b);
		}

		return stringBuilder;
	}

	public final static String removeUnusedNewLine(String contentIn)
	{
		return contentIn.replaceAll("^[\r\n]+", "").replaceAll("[\r\n]+$", "");
	}

	public final static String removeUnusedBlanks(String contentIn)
	{
		return contentIn.replaceAll("^[ \t\r\n]+", "").replaceAll("[ \t\r\n]+$", "");
	}
}