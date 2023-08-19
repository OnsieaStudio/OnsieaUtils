/**
 * Copyright 2021-2023 Onsiea Studio All rights reserved.<br>
 * <br>
 *
 * This file is part of Onsiea Engine project. (https://github.com/OnsieaStudio/OnsieaEngine)<br>
 * <br>
 *
 * Onsiea Engine is [licensed] (https://github.com/OnsieaStudio/OnsieaEngine/blob/main/LICENSE) under the terms of the
 * "GNU General Public Lesser License v2.1" (LGPL-2.1).
 * https://github.com/OnsieaStudio/OnsieaEngine/wiki/License#license-and-copyright<br>
 * <br>
 *
 * Onsiea Engine is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 2.1 of the License, or (at your option)
 * any later version.<br>
 * <br>
 *
 * Onsiea Engine is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.<br>
 * <br>
 *
 * You should have received a copy of the GNU Lesser General Public License along with Onsiea Engine. If not, see
 * <https://www.gnu.org/licenses/>.<br>
 * <br>
 *
 * Neither the name "Onsiea Studio", "Onsiea Engine", or any derivative name or the names of its authors / contributors
 * may be used to endorse or promote products derived from this software and even less to name another project or other
 * work without clear and precise permissions written in advance.<br>
 * <br>
 *
 * @Author : Seynax (https://github.com/seynax)<br>
 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
 */
package fr.onsiea.utils.logs;

import fr.onsiea.utils.file.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class LogsUtils
{
	public static String fileDetails(final File fileIn)
	{
		return "\"" + fileIn.getAbsolutePath() + "\" (" + fileIn.length() + ")";
	}

	@SuppressWarnings("FinalStaticMethod")
	public final static String fileDetails(final File fromFileIn, final File toFileIn)
	{
		final var relativeFromFilePath = fromFileIn.toPath().relativize(toFileIn.toPath()).toString()
				.replaceAll("(\\.\\.\\\\)+", ".\\\\");
		final var relativeToFilePath = toFileIn.toPath().relativize(fromFileIn.toPath()).toString()
				.replaceAll("(\\.\\.\\\\)+", ".\\\\");

		return "\"" + relativeFromFilePath + "\" (" + fromFileIn.length() + ")" + "\"" + relativeToFilePath + "\" ("
				+ toFileIn.length() + ")";
	}

	public static String fileDetails(final File fileIn, final String shaIn)
	{
		return "\"" + fileIn.getAbsolutePath() + "\" (" + fileIn.length() + ")[" + shaIn + "]";
	}

	public static String fileDetails(final File fromFileIn, final String fromSHAIn, final File toFileIn,
			final String toSHAIn)
	{
		final var relativeFromFilePath = fromFileIn.toPath().relativize(toFileIn.toPath()).toString()
				.replaceAll("(\\.\\.\\\\)+", ".\\\\");
		final var relativeToFilePath = toFileIn.toPath().relativize(fromFileIn.toPath()).toString()
				.replaceAll("(\\.\\.\\\\)+", ".\\\\");

		return "\"" + relativeFromFilePath + "\" (" + fromFileIn.length() + ")[" + fromSHAIn + "]" + "\""
				+ relativeToFilePath + "\" (" + toFileIn.length() + ")[" + toSHAIn + "]";
	}

	public static void throwErr(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String errorDetailIn)
	{
		try
		{
			LogsUtils.err(logsPathIn, phaseIn, logsIn, "[ERR0R] " + errorDetailIn + " !");
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void throwErr(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String errorDetailIn, final File fromFileIn)
	{
		try
		{
			LogsUtils.err(logsPathIn, phaseIn, logsIn,
					"[ERR0R] " + errorDetailIn + " ! " + LogsUtils.fileDetails(fromFileIn));
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void throwErr(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String errorDetailIn, final File fromFileIn, final String fromSHAIn)
	{
		try
		{
			LogsUtils.err(logsPathIn, phaseIn, logsIn,
					"[ERR0R] " + errorDetailIn + " ! " + LogsUtils.fileDetails(fromFileIn, fromSHAIn));
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void throwErr(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String errorDetailIn, final File fromFileIn, final String fromSHAIn, final File tofileIn,
			final String toSHAIn)
	{
		try
		{
			LogsUtils.err(logsPathIn, phaseIn, logsIn,
					"[ERR0R] " + errorDetailIn + " ! " + LogsUtils.fileDetails(fromFileIn, fromSHAIn, tofileIn,
							toSHAIn));
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void err(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String detailsIn, final Exception exceptionIn) throws IOException
	{
		LogsUtils.logs(logsPathIn, phaseIn, logsIn);

		final var message = "[ERROR] " + phaseIn + "  - FAILED \"" + detailsIn + "\"\r\n";
		System.err.println(message);
		logsIn.append(message);
		logsIn.append(exceptionIn.getMessage()).append("\r\n");
		FileUtils.append(logsPathIn + "\\" + phaseIn + "_failed" + ".logs", logsIn.toString());

		exceptionIn.getStackTrace();

		System.exit(-1);
	}

	public static void err(final String logsPathIn, final String phaseIn, final StringBuilder logsIn,
			final String errorStackTraceIn) throws IOException
	{
		LogsUtils.logs(logsPathIn, phaseIn, logsIn);

		logsIn.append("[ERROR] ").append(phaseIn).append("  - FAILED \"").append(errorStackTraceIn).append("\"\r\n");
		logsIn.append(errorStackTraceIn).append("\r\n");

		FileUtils.append(logsPathIn + "\\" + phaseIn + "_failed" + ".logs", logsIn.toString());

		System.exit(-1);
	}

	public static void logs(final String logsPathIn, final String phaseIn, final StringBuilder logsIn)
			throws IOException
	{
		if (logsIn.toString().isBlank() || logsIn.toString().isEmpty() || logsIn.toString().matches(" +"))
		{
			return;
		}

		System.out.print(logsIn);
		FileUtils.append(logsPathIn + "\\" + phaseIn + ".logs", logsIn.toString());
		logsIn.setLength(0);
	}
}
