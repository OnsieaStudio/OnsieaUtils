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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import fr.onsiea.utils.function.IIFunction;
import fr.onsiea.utils.function.IOIFunction;
import fr.onsiea.utils.string.StringUtils;

/**
 *
 */
public class FileUtils
{
	// Create directory and file methods

	/**
	 * If not exists create all directories from foldersPathIn, but if file exist and isn't directory throw IOException
	 *
	 * @author Seynax
	 * @param foldersPathIn
	 * @throws IOException if file exist and isn't directory
	 */
	public final static void createDirectory(final String... foldersPathIn) throws IOException
	{
		for (final var path : foldersPathIn)
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

	/**
	 * If not exists create all directories from foldersIn, but if file exist and isn't directory throw IOException
	 *
	 * @author Seynax
	 * @param foldersIn
	 * @throws IOException if file exist and isn't directory
	 */
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

	/**
	 * If not exists create all directories from foldersPathIn, but if file exist and isn't directory throw IOException
	 *
	 * @author Seynax
	 * @param foldersPathIn
	 * @throws IOException if file exist and isn't directory
	 */
	public final static void createDirectory(final Path... foldersPathIn) throws IOException
	{
		for (final var folderPath : foldersPathIn)
		{
			final var folder = folderPath.toFile();

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

	/**
	 * If not exists create all files from filesPathIn, but if parent of file exists and isn't directory or file exist and is directory throw IOException
	 *
	 * @author Seynax
	 * @param filesPathIn
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 */
	public final static void createFile(final String... filesPathIn) throws IOException
	{
		for (final var path : filesPathIn)
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

	/**
	 * If not exists create all files from filesIn, but if parent of file exists and isn't directory or file exist and is directory throw IOException
	 *
	 * @author Seynax
	 * @param filesIn
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 */
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

	/**
	 * If not exists create all files from filesPathIn, but if parent of file exists and isn't directory or file exist and is directory throw IOException
	 *
	 * @author Seynax
	 * @param filesPathIn
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 */
	public final static void createFile(final Path... filesPathIn) throws IOException
	{
		for (final var filePath : filesPathIn)
		{
			final var	file		= filePath.toFile();
			final var	parentFile	= file.getParentFile();
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
	 * @author Seynax
	 * @param pathIn
	 * @return all bytes of file from pathIn
	 */
	public final static byte[] bytes(final Path pathIn)
	{
		return FileUtils.bytes(pathIn.toFile());
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
	 * @author Seynax
	 * @param pathIn
	 * @return string content of file from pathIn
	 */
	public final static String content(final Path pathIn)
	{
		return FileUtils.content(pathIn.toFile());
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
	 * read all bytes of file from pathIn, return converted string from bytes with charsetIn charset
	 *
	 * @author Seynax
	 * @param pathIn
	 * @param charsetIn
	 * @return string content of file from pathIn
	 */
	public final static String content(final Path pathIn, final Charset charsetIn)
	{
		return FileUtils.content(pathIn.toFile(), charsetIn);
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
	 * Read line by line file from pathIn, add line into string with "\r\n" line separator
	 *
	 * @author Seynax
	 * @param pathIn
	 * @return separated lines string content of file from pathIn
	 */
	public final static String contentFromLines(final Path pathIn)
	{
		return FileUtils.contentFromLines(pathIn.toFile());
	}

	/**
	 * Read line by line file from filePathIn, add line with lineSeparatorIn if not null into string
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
	 * Read line by line fileIn, add line with lineSeparatorIn if not null into string
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
				content += line;

				if (lineSeparatorIn != null)
				{
					content += lineSeparatorIn;
				}
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return content;
	}

	/**
	 * Read line by line file from pathIn, add line with lineSeparatorIn if not null into string
	 *
	 * @author Seynax
	 * @param pathIn
	 * @param lineSeparatorIn
	 * @return separated lines string content of file from pathIn
	 */
	public final static String contentFromLines(final Path pathIn, final String lineSeparatorIn)
	{
		return FileUtils.contentFromLines(pathIn.toFile(), lineSeparatorIn);
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
	 * @author Seynax
	 * @param pathIn
	 * @return all lines in list of file from pathIn
	 */
	public final static Collection<String> lines(final Path pathIn)
	{
		return FileUtils.lines(pathIn.toFile());
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
	 * Execute functionIn.execute(String lineIn) for all lines of file from pathIn
	 *
	 * @author Seynax
	 * @param pathIn
	 * @param functionIn
	 */
	public final static void forEachLines(final Path pathIn, final IIFunction<String> functionIn)
	{
		FileUtils.forEachLines(pathIn.toFile(), functionIn);
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
	 * Execute functionIn.execute(String lineIn) for all lines of file from pathIn and place String return result into list
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

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from pathIn and place String return result into list
	 *
	 * @author Seynax
	 * @param pathIn
	 * @param functionIn
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 */
	public final static List<String> forEachLinesAndAdd(final Path pathIn, final IOIFunction<String, String> functionIn)
	{
		return FileUtils.forEachLinesAndAdd(pathIn.toFile(), functionIn);
	}

	// SHA methods

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return sha of file from filePathIn
	 * @throws IOException
	 */
	public final static byte[] sha(final String filePathIn) throws IOException
	{
		return FileUtils.sha(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return sha of fileIn
	 * @throws IOException
	 */
	public final static byte[] sha(final File fileIn) throws IOException
	{
		if (fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot get sha of directory ! \"" + fileIn.getAbsolutePath() + "\"");
		}

		MessageDigest digest = null;

		try (var fis = new FileInputStream(fileIn))
		{
			digest = MessageDigest.getInstance("SHA-1");

			var			n		= 0;
			final var	buffer	= new byte[8192];
			while (n != -1)
			{
				n = fis.read(buffer);
				if (n > 0)
				{
					digest.update(buffer, 0, n);
				}
			}
			fis.close();
		}
		catch (final IOException | NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		if (digest != null)
		{
			return digest.digest();
		}

		return null;
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return sha of file from filePathIn
	 * @throws IOException
	 */
	public final static byte[] sha(final Path filePathIn) throws IOException
	{
		return FileUtils.sha(filePathIn.toFile());
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return raw string from sha of file from filePathIn
	 * @throws IOException
	 */
	public final static String stringSHA(final String filePathIn) throws IOException
	{
		return FileUtils.stringSHA(new File(filePathIn));
	}

	/**
	 * @author Seynax
	 * @param fileIn
	 * @return raw string from sha of fileIn
	 * @throws IOException
	 */
	public final static String stringSHA(final File fileIn) throws IOException
	{
		final var sha = FileUtils.sha(fileIn);

		if (sha != null)
		{
			return StringUtils.toRawString(sha);
		}

		return null;
	}

	/**
	 * @author Seynax
	 * @param filePathIn
	 * @return raw string from sha of file from filePathIn
	 * @throws IOException
	 */
	public final static String stringSHA(final Path filePathIn) throws IOException
	{
		return FileUtils.stringSHA(filePathIn.toFile());
	}

	// Compare methods

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files absolute paths (from fromFilePathIn and toFilePathIn) are exactly same (if fromFile is toFile)
	 * @throws IOException
	 */
	public final static boolean is(final String fromFilePathIn, final String toFilePathIn) throws IOException
	{
		return FileUtils.is(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * @author Seynax
	 * @param fromFileIn
	 * @param toFileIn
	 * @return true only if files absolute paths (from fromFileIn and toFileIn) are exactly same (if fromFile is toFile)
	 * @throws IOException
	 */
	public final static boolean is(final File fromFileIn, final File toFileIn) throws IOException
	{
		return fromFileIn.getAbsolutePath().contentEquals(toFileIn.getAbsolutePath());
	}

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files absolute paths (from fromFilePathIn and toFilePathIn) are exactly same (if fromFile is toFile)
	 * @throws IOException
	 */
	public final static boolean is(final Path fromFilePathIn, final Path toFilePathIn) throws IOException
	{
		return fromFilePathIn.toAbsolutePath().toString().contentEquals(toFilePathIn.toAbsolutePath().toString());
	}

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files (from fromFilePathIn and toFilePathIn) are same length
	 * @throws IOException
	 */
	public final static boolean sameLength(final String fromFilePathIn, final String toFilePathIn) throws IOException
	{
		return FileUtils.sameLength(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * @author Seynax
	 * @param fromFileIn
	 * @param toFileIn
	 * @return true only if files (from fromFileIn and toFileIn) are same length
	 * @throws IOException
	 */
	public final static boolean sameLength(final File fromFileIn, final File toFileIn) throws IOException
	{
		return fromFileIn.length() == toFileIn.length();
	}

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files (from fromFilePathIn and toFilePathIn) are same length
	 * @throws IOException
	 */
	public final static boolean sameLength(final Path fromFilePathIn, final Path toFilePathIn) throws IOException
	{
		return FileUtils.sameLength(fromFilePathIn.toFile(), toFilePathIn.toFile());
	}

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files/directories (from fromFilePathIn and toFilePathIn) are same contents (type[directory/file], length, sha)
	 * @throws IOException
	 */
	public final static boolean sameContent(final String fromFilePathIn, final String toFilePathIn) throws IOException
	{
		return FileUtils.sameContent(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * @author Seynax
	 * @param fromFileIn
	 * @param toFileIn
	 * @return true only if files/directories (from fromFileIn and toFileIn) are same contents (type[directory/file], length, sha)
	 * @throws IOException
	 */
	public final static boolean sameContent(final File fromFileIn, final File toFileIn) throws IOException
	{
		if (!fromFileIn.exists())
		{
			throw new FileNotFoundException("[ERROR] Cannot compare files because \"" + fromFileIn.getAbsolutePath() + "\" not exists ! (\"" + fromFileIn.getAbsolutePath() + " <=> " + toFileIn.getAbsolutePath() + "\")");
		}
		if (!toFileIn.exists())
		{
			throw new FileNotFoundException("[ERROR] Cannot compare files because \"" + toFileIn.getAbsolutePath() + "\" not exists ! (\"" + fromFileIn.getAbsolutePath() + " <=> " + toFileIn.getAbsolutePath() + "\")");
		}

		if (fromFileIn.getAbsolutePath().contentEquals(toFileIn.getAbsolutePath()))
		{
			return true;
		}

		if (fromFileIn.length() != toFileIn.length())
		{
			return false;
		}

		if (fromFileIn.isDirectory())
		{
			if (toFileIn.isDirectory())
			{
				final var	fromChildFiles	= fromFileIn.listFiles();
				final var	toChildFiles	= toFileIn.listFiles();

				if (fromChildFiles == null)
				{
					if (toChildFiles == null)
					{
						return true;
					}

					return false;
				}

				if (fromChildFiles.length != toChildFiles.length)
				{
					return false;
				}

				for (var i = 0; i < fromChildFiles.length; i++)
				{
					if (!FileUtils.sameContent(fromChildFiles[i], toChildFiles[i]))
					{
						return false;
					}
				}

				return true;
			}

			return false;
		}

		if (toFileIn.isFile())
		{
			final var	fromSHA	= FileUtils.sha(fromFileIn);
			final var	toSHA	= FileUtils.sha(toFileIn);

			for (var i = 0; i < fromSHA.length; i++)
			{
				if (fromSHA[i] != toSHA[i])
				{
					return false;
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * @author Seynax
	 * @param fromFilePathIn
	 * @param toFilePathIn
	 * @return true only if files/directories (from fromFilePathIn and toFilePathIn) are same contents (type[directory/file], length, sha)
	 * @throws IOException
	 */
	public final static boolean sameContent(final Path fromFilePathIn, final Path toFilePathIn) throws IOException
	{
		return FileUtils.sameContent(fromFilePathIn.toFile(), toFilePathIn.toFile());
	}

	// Write methods

	/**
	 * Write all bytes from bytesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void write(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void write(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(fileIn.toPath(), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void write(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot write in \"" + filePathIn + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		Files.write(filePathIn, bytesIn);
	}

	/**
	 * Write all chars from charsIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void write(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), charsIn);
	}

	/**
	 * Write all chars from charsIn into fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void write(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(charsIn);
		}
	}

	/**
	 * Write all chars from charsIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void write(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), charsIn);
	}

	/**
	 * Write String content from contentIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void write(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), contentIn);
	}

	/**
	 * Write String content from contentIn into fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void write(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(contentIn);
		}
	}

	/**
	 * Write String content from contentIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void write(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), contentIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void write(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void write(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void write(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void write(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into fileIn
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void write(final File fileIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line);

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void write(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}
	// Append methods

	/**
	 * Append all bytes from bytesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void append(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Append all bytes from bytesIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void append(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(fileIn.toPath(), bytesIn);
	}

	/**
	 * Append all bytes from bytesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void append(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn) && Files.isDirectory(filePathIn))
		{
			throw new IOException("[ERROR] Cannot write in \"" + filePathIn + "\" because file exists and is directory !");
		}

		Files.write(filePathIn, bytesIn, StandardOpenOption.APPEND);
	}

	/**
	 * Append all chars from charsIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void append(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), charsIn);
	}

	/**
	 * Append all chars from charsIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void append(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(charsIn);
		}
	}

	/**
	 * Append all chars from charsIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void append(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), charsIn);
	}

	/**
	 * Append String content from contentIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void append(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), contentIn);
	}

	/**
	 * Append String content from contentIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void append(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(contentIn);
		}
	}

	/**
	 * Append String content from contentIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void append(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), contentIn);
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void append(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn);
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void append(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void append(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn);
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void append(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void append(final File fileIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line);

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void append(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}

	// Replace methods

	/**
	 * Write all bytes from bytesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void replace(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void replace(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(fileIn.toPath(), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param bytesIn
	 * @throws IOException
	 */
	public final static void replace(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn) && Files.isDirectory(filePathIn))
		{
			throw new IOException("[ERROR] Cannot write in \"" + filePathIn + "\" because file exists and is directory !");
		}

		Files.write(filePathIn, bytesIn);
	}

	/**
	 * Write all chars from charsIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void replace(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), charsIn);
	}

	/**
	 * Write all chars from charsIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void replace(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(charsIn);
		}
	}

	/**
	 * Write all chars from charsIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param charsIn
	 * @throws IOException
	 */
	public final static void replace(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), charsIn);
	}

	/**
	 * Write String content from contentIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void replace(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), contentIn);
	}

	/**
	 * Write String content from contentIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void replace(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(contentIn);
		}
	}

	/**
	 * Write String content from contentIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param contentIn
	 * @throws IOException
	 */
	public final static void replace(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), contentIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void replace(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void replace(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @throws IOException
	 */
	public final static void replace(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void replace(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param fileIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void replace(final File fileIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath() + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line);

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br>
	 * ATTENTION ! Content of file is replaced with this methods.
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @param linesIn
	 * @param lineSeparatorIn
	 * @throws IOException
	 */
	public final static void replace(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}

	// Copy methods

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public final static void copyFile(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.copyFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 * @param sourceFileIn
	 * @param destinationFileIn
	 * @throws IOException
	 */
	public final static void copyFile(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.copyFile(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public final static void copyFile(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException("[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + "\", because source file/directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException(
					"[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + " \"  because destination file/directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		if (Files.isDirectory(sourcePathIn))
		{
			Files.walk(sourcePathIn).forEach(source ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), source.toString().substring(sourcePathIn.toString().length()));

				try
				{
					Files.copy(source, destination);
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			});
		}
		else
		{
			Files.copy(sourcePathIn, destinationPathIn);
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public static void copyDirectory(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.copyDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy all files from sourceIn into destinationIn
	 *
	 * @author Seynax
	 * @param sourceFileIn
	 * @param destinationFileIn
	 * @throws IOException
	 */
	public static void copyDirectory(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.copyDirectory(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public static void copyDirectory(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException("[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + "\", because source directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException(
					"[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + " \"  because destination directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		Files.walk(sourcePathIn).forEach(source ->
		{
			final var destination = Paths.get(destinationPathIn.toString(), source.toString().substring(sourcePathIn.toString().length()));

			try
			{
				Files.copy(source, destination);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public final static void replaceFile(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.replaceFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourceFileIn
	 * @param destinationFileIn
	 * @throws IOException
	 */
	public final static void replaceFile(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.replaceFile(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public final static void replaceFile(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException("[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + "\", because source file/directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			FileUtils.delete(destinationPathIn);
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		if (Files.isDirectory(sourcePathIn))
		{
			Files.walk(sourcePathIn).forEach(source ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), source.toString().substring(sourcePathIn.toString().length()));

				try
				{
					Files.copy(source, destination);
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			});
		}
		else
		{
			Files.copy(sourcePathIn, destinationPathIn);
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public static void replaceDirectory(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.replaceDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy all files from sourceIn into destinationIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourceFileIn
	 * @param destinationFileIn
	 * @throws IOException
	 */
	public static void replaceDirectory(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.replaceDirectory(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br>
	 * ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 * @param sourcePathIn
	 * @param destinationPathIn
	 * @throws IOException
	 */
	public static void replaceDirectory(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException("[ERROR] Cannot copy \"" + sourcePathIn.toString() + "\" into \"" + destinationPathIn.toString() + "\", because source directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			FileUtils.delete(destinationPathIn);
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		Files.walk(sourcePathIn).forEach(source ->
		{
			final var destination = Paths.get(destinationPathIn.toString(), source.toString().substring(sourcePathIn.toString().length()));

			try
			{
				Files.copy(source, destination);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	// Delete methods

	/**
	 * Delete file (file or directory) from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @throws IOException
	 */
	public final static void delete(final String filePathIn) throws IOException
	{
		FileUtils.delete(Paths.get(filePathIn));
	}

	/**
	 * Delete fileIn (file or directory)
	 *
	 * @author Seynax
	 * @param fileIn
	 * @throws IOException
	 */
	public final static void delete(final File fileIn) throws IOException
	{
		FileUtils.delete(fileIn.toPath());
	}

	/**
	 * Delete file (file or directory) from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @throws IOException
	 */
	public final static void delete(final Path filePathIn) throws IOException
	{
		if (!Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot remove \"" + filePathIn.toString() + "\" because not exists !");
		}

		if (Files.isDirectory(filePathIn))
		{
			Files.walk(filePathIn).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		}
		else
		{
			Files.delete(filePathIn);
		}
	}

	/**
	 * Delete directory from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @throws IOException
	 */
	public final static void deleteDirectory(final String filePathIn) throws IOException
	{
		FileUtils.deleteDirectory(Paths.get(filePathIn));
	}

	/**
	 * Delete fileIn directory
	 *
	 * @author Seynax
	 * @param fileIn
	 * @throws IOException
	 */
	public final static void deleteDirectory(final File fileIn) throws IOException
	{
		FileUtils.deleteDirectory(fileIn.toPath());
	}

	/**
	 * Delete directory from filePathIn
	 *
	 * @author Seynax
	 * @param filePathIn
	 * @throws IOException
	 */
	public final static void deleteDirectory(final Path filePathIn) throws IOException
	{
		if (!Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot remove \"" + filePathIn.toString() + "\" because not exists !");
		}

		try
		{
			Files.walk(filePathIn).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Delete all files (file or directory) from filePathIn
	 *
	 * @author Seynax
	 * @param filesPathIn
	 * @throws IOException
	 */
	public final static void deletes(final String... filesPathIn) throws IOException
	{
		for (final var filePath : filesPathIn)
		{
			FileUtils.delete(Paths.get(filePath));
		}
	}

	/**
	 * Delete all files (file or directory) from filesIn
	 *
	 * @author Seynax
	 * @param filesIn
	 * @throws IOException
	 */
	public final static void deletes(final File... filesIn) throws IOException
	{
		for (final var file : filesIn)
		{
			FileUtils.delete(file.toPath());
		}
	}

	/**
	 * Delete all files (file or directory) from filesPathIn
	 *
	 * @author Seynax
	 * @param filesPathIn
	 * @throws IOException
	 */
	public final static void deletes(final Path... filesPathIn) throws IOException
	{
		for (final var path : filesPathIn)
		{
			FileUtils.delete(path);
		}
	}

	/**
	 * Delete all directories from filePathsIn
	 *
	 * @author Seynax
	 * @param filePathsIn
	 * @throws IOException
	 */
	public final static void deleteDirectories(final String... filePathsIn) throws IOException
	{
		for (final var path : filePathsIn)
		{
			FileUtils.deleteDirectory(Paths.get(path));
		}
	}

	/**
	 * Delete all directories from filesIn
	 *
	 * @author Seynax
	 * @param filesIn
	 * @throws IOException
	 */
	public final static void deleteDirectories(final File... filesIn) throws IOException
	{
		for (final var file : filesIn)
		{
			FileUtils.deleteDirectory(file.toPath());
		}
	}

	/**
	 * Delete all directories from filesPathIn
	 *
	 * @author Seynax
	 * @param filesPathIn
	 * @throws IOException
	 */
	public final static void deleteDirectories(final Path... filesPathIn) throws IOException
	{
		for (final var path : filesPathIn)
		{
			FileUtils.deleteDirectory(path);
		}
	}
}