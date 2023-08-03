/**
 * This file is part of Onsiea Utils project.
 * (https://github.com/OnsieaStudio/OnsieaUtils)<br>
 * <br>
 *
 * Onsiea Utils is [licensed]
 * (https://github.com/OnsieaStudio/OnsieaUtils/blob/main/LICENSE) under the terms of
 * the "GNU GENERAL PUBLIC LICENSE v3 29 June 2007" (GPL-3).
 * https://github.com/OnsieaStudio/OnsieaUtils/wiki/License#license-and-copyright<br>
 * <br>
 *
 * Onsiea Utils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or any later version.<br>
 * <br>
 *
 * Onsiea Utils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU GENERAL PUBLIC LICENSE for more details.<br>
 * <br>
 *
 * You should have received a copy of the GNU GENERAL PUBLIC LICENSE
 * along with Onsiea Utils. If not, see <https://www.gnu.org/licenses/>.<br>
 * <br>
 *
 * <br>
 * Copyright 2021-2023 : Neither the name "Onsiea Studio", "Onsiea Utils", or any derivative name or the
 * names of its authors / contributors may be used to endorse or promote
 * products derived from this software and even less to name another project or
 * other work without clear and precise permissions written in advance.<br>
 * <br>
 *
 * @Author : Seynax (https://github.com/seynax)<br>
 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
 */
package fr.onsiea.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.onsiea.utils.function.IIFunction;
import fr.onsiea.utils.function.IOIFunction;

/**
 *
 */
public class FileUtils
{
	// Create directory and file methods

	public final static void createDirectory(final String... folderPathsIn) throws IOException
	{
		for (final var path : folderPathsIn)
		{
			final var folder = new File(path);

			if (folder.exists())
			{
				if (folder.isFile())
				{
					throw new IOException("[ERROR] Unable to create folder \"" + path + "\", already exists and is file !");
				}
			}
			else if (!folder.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create folder \"" + path + "\" (File.mkdirs method failed) !");
			}
		}
	}

	public final static void createDirectory(final File... foldersIn) throws IOException
	{
		for (final var folder : foldersIn)
		{
			if (folder.exists())
			{
				if (folder.isFile())
				{
					throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath() + "\", already exists and is file !");
				}
			}
			else if (!folder.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath() + "\" (File.mkdirs method failed) !");
			}
		}
	}

	public final static void createFile(final String... filesPathsIn) throws IOException
	{
		for (final var path : filesPathsIn)
		{
			final var file = new File(path);

			final var parentFile = file.getParentFile();
			if (parentFile.exists())
			{
				if (parentFile.isFile())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \"" + parentFile.getAbsolutePath() + "\" because parent file isn't directory !");
				}
			}
			else if (!parentFile.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \"" + parentFile.getAbsolutePath() + "\" because failed to create parent directory (File.mkdirs method failed) !");
			}

			if (file.exists())
			{
				if (file.isDirectory())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath() + "\", already exists and is directory !");
				}
			}
			else if (!file.createNewFile())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath() + "\" (File.createNewFile method failed) !");
			}
		}
	}

	public final static void createFile(final File... filesIn) throws IOException
	{
		for (final var file : filesIn)
		{
			final var parentFile = file.getParentFile();
			if (parentFile.exists())
			{
				if (parentFile.isFile())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \"" + parentFile.getAbsolutePath() + "\" because parent file isn't directory !");
				}
			}
			else if (!parentFile.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \"" + parentFile.getAbsolutePath() + "\" because failed to create parent directory !");
			}

			if (file.exists())
			{
				if (file.isDirectory())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath() + "\", already exists and is directory !");
				}
			}
			else if (!file.createNewFile())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath() + "\" (File.createNewFile method failed) !");
			}
		}
	}

	// Read methods

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return all bytes of file from filePathIn
	 */
	public final static byte[] bytes(final String filePathIn)
	{
		return FileUtils.bytes(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return all bytes of fileIn
	 */
	public final static byte[] bytes(final File fileIn)
	{
		try
		{
			return Files.readAllBytes(fileIn.toPath());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * read all bytes of file from filePathIn, return converted string from bytes with UTF_8 charset
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @return string content of file from filePathIn
	 */
	public final static String content(final String filePathIn)
	{
		return FileUtils.content(new File(filePathIn));
	}

	/**
	 * read all bytes of fileIn, return converted string from bytes with UTF_8 charset
	 *
	 * @author Seynax
	 * @param fileIn
	 * @return string content of fileIn
	 */
	public final static String content(final File fileIn)
	{
		try
		{
			return new String(Files.readAllBytes(fileIn.toPath()), StandardCharsets.UTF_8);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * read all bytes of file from filePathIn, return converted string from bytes with charsetIn charset
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsetIn
	 * @return string content of file from filePathIn
	 */
	public final static String content(final String filePathIn, final Charset charsetIn)
	{
		return FileUtils.content(new File(filePathIn), charsetIn);
	}

	/**
	 * read all bytes of fileIn, return converted string from bytes with charsetIn charset
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param charsetIn
	 * @return string content of fileIn
	 */
	public final static String content(final File fileIn, final Charset charsetIn)
	{
		try
		{
			return new String(Files.readAllBytes(fileIn.toPath()), charsetIn);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Read line by line file from filePathIn, add line into string with "\r\n" line separator
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @return separated lines string content of file from filePathIn
	 */
	public final static String contentFromLines(final String filePathIn)
	{
		return FileUtils.contentFromLines(new File(filePathIn));
	}

	/**
	 * Read line by line fileIn, add line into string with "\r\n" line separator
	 *
	 * @author Seynax
	 * @param fileIn
	 * @return separated lines string content of fileIn
	 */
	public final static String contentFromLines(final File fileIn)
	{
		String content = null;

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				content += line + "\r\n";
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return content;
	}

	/**
	 * Read line by line file from filePathIn, add line into string with lineSeparatorIn line separator
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param lineSeparatorIn
	 * @return separated lines string content of file from filePathIn
	 */
	public final static String contentFromLines(final String filePathIn, final String lineSeparatorIn)
	{
		return FileUtils.contentFromLines(new File(filePathIn), lineSeparatorIn);
	}

	/**
	 * Read line by line fileIn, add line into string with lineSeparatorIn line separator
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param lineSeparatorIn
	 * @return separated lines string content of fileIn
	 */
	public final static String contentFromLines(final File fileIn, final String lineSeparatorIn)
	{
		String content = null;

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				content += line + lineSeparatorIn;
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return content;
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return all lines in list of file from filePathIn
	 */
	public final static Collection<String> lines(final String filePathIn)
	{
		return FileUtils.lines(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return all lines in list of fileIn
	 */
	public final static Collection<String> lines(final File fileIn)
	{
		List<String> lines = null;

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			lines = new ArrayList<>();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return lines;
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param functionIn
	 */
	public final static void forEachLines(final String filePathIn, final IIFunction<String> functionIn)
	{
		FileUtils.forEachLines(new File(filePathIn), functionIn);
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param functionIn
	 */
	public final static void forEachLines(final File fileIn, final IIFunction<String> functionIn)
	{
		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				functionIn.execute(line);
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from filePathIn and place String return result into list
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param functionIn
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 */
	public final static Collection<String> forEachLinesAndAdd(final String filePathIn, final IOIFunction<String, String> functionIn)
	{
		return FileUtils.forEachLinesAndAdd(new File(filePathIn), functionIn);
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of fileIn and place String return result into list
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param functionIn
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 */
	public final static List<String> forEachLinesAndAdd(final File fileIn, final IOIFunction<String, String> functionIn)
	{
		List<String> lines = null;

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			lines = new ArrayList<>();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				lines.add(functionIn.execute(line));
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return lines;
	}
}