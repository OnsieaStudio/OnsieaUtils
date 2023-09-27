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
package fr.onsiea.tools.utils.file;

import fr.onsiea.tools.utils.function.IOIFunction;
import fr.onsiea.tools.utils.function.IIFunction;
import fr.onsiea.tools.utils.string.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 */
public class FileUtils
{
	// Links

	public final static void link(String fromIn, String toIn) throws IOException
	{
		var file = new File(fromIn);
		if (!file.exists())
		{
			throw new IOException("[ERROR] FileUtils : cannot create symbolic link, file not exists ! \"" + file.getAbsolutePath() + "\"");
		}

		Files.createLink(Path.of(toIn), file.toPath());
	}

	public final static void symbolicLink(String fromIn, String toIn) throws IOException
	{
		var file = new File(fromIn);
		if (!file.exists())
		{
			throw new IOException("[ERROR] FileUtils : cannot create symbolic link, file not exists ! \"" + file.getAbsolutePath() + "\"");
		}

		Files.createSymbolicLink(Path.of(toIn), file.toPath());
	}

	// mkdirs methods

	public final static void mkdirs(String... foldersIn)
	{
		for (var folder : foldersIn)
		{
			File file = new File(folder);

			if (file.exists())
			{
				if (file.isFile())
				{
					throw new RuntimeException("[ERROR] Cannot make \"" + folder + "\" directory because exists and is file !");
				}

				continue;
			}

			if (!file.mkdirs())
			{
				throw new RuntimeException("[ERROR] Cannot make \"" + folder + "\" directory !");
			}
		}
	}

	// File method

	public static File file(String filePathIn)
	{
		return new File(filePathIn);
	}

	// RemoveEmptyFoldersRecursively methods

	/**
	 * Remove all empties directories recursively from fromFilePathIn and execute functionIn with this
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final String fromFilePathIn, final IIFunction<File> functionIn)
	throws IOException
	{
		FileUtils.removeEmptyDirectoriesRecursively(new File(fromFilePathIn), functionIn);
	}

	/**
	 * Remove all empties directories recursively from fromFileIn and execute functionIn with this
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final File fromFileIn, final IIFunction<File> functionIn)
	throws IOException
	{
		if (fromFileIn.isDirectory())
		{
			var files = fromFileIn.listFiles();
			if (files != null)
			{
				for (final var file : files)
				{
					FileUtils.removeEmptyDirectoriesRecursively(file);
				}
			}

			files = fromFileIn.listFiles();
			if (files == null || files.length == 0)
			{
				functionIn.execute(fromFileIn);
				Files.delete(fromFileIn.toPath());
			}
		}
	}

	/**
	 * Remove all empties directories recursively from fromFilePathIn and execute functionIn with this
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final Path fromFilePathIn, final IIFunction<File> functionIn)
	throws IOException
	{
		FileUtils.removeEmptyDirectoriesRecursively(fromFilePathIn.toFile(), functionIn);
	}

	/**
	 * Remove all empties directories recursively from fromFilePathIn
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final String fromFilePathIn) throws IOException
	{
		FileUtils.removeEmptyDirectoriesRecursively(new File(fromFilePathIn));
	}

	/**
	 * Remove all empties directories recursively from fromFileIn
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final File fromFileIn) throws IOException
	{
		if (fromFileIn.isDirectory())
		{
			var files = fromFileIn.listFiles();
			if (files != null)
			{
				for (final var file : files)
				{
					FileUtils.removeEmptyDirectoriesRecursively(file);
				}
			}
			files = fromFileIn.listFiles();
			if (files == null || files.length == 0)
			{
				Files.delete(fromFileIn.toPath());
			}
		}
	}

	/**
	 * Remove all empties directories recursively from fromFilePathIn
	 *
	 * @author Seynax
	 */
	public static void removeEmptyDirectoriesRecursively(final Path fromFilePathIn) throws IOException
	{
		FileUtils.removeEmptyDirectoriesRecursively(fromFilePathIn.toFile());
	}

	// Double recursively

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFilePathIn for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final String fromFilePathIn)
	{
		FileUtils.recursively(
				_fromFileIn -> FileUtils.recursively(_toFileIn -> functionIn.execute(_fromFileIn, _toFileIn),
						fromFilePathIn), fromFilePathIn);
	}

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFileIn for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final File fromFileIn)
	{
		FileUtils.recursively(_fromFileIn -> FileUtils.recursively(_toFileIn ->
		{
			if (!_fromFileIn.getAbsolutePath().contentEquals(
					_toFileIn.getAbsolutePath()))
			{
				functionIn.execute(_fromFileIn, _toFileIn);
			}
		}, fromFileIn), fromFileIn);
	}

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFilePathIn except for same files
	 * paths, files (not directories)
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final Path fromFilePathIn)
	{
		FileUtils.recursively(_fromFileIn -> FileUtils.recursively(_toFileIn ->
		{
			if (!_fromFileIn.getAbsolutePath().contentEquals(
					_toFileIn.getAbsolutePath()))
			{
				functionIn.execute(_fromFileIn, _toFileIn);
			}
		}, fromFilePathIn), fromFilePathIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFilePathIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final String fromFilePathIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, fromFilePathIn);
			}
		}, fromFilePathIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFileIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final File fromFileIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, fromFileIn);
			}
		}, fromFileIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFilePathIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final Path fromFilePathIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, fromFilePathIn);
			}
		}, fromFilePathIn);
	}

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFilePathIn and toFilePathIn for
	 * same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final String fromFilePathIn,
	                                     final String toFilePathIn)
	{
		FileUtils.recursively(_fromFileIn -> FileUtils.recursively(_toFileIn ->
		{
			if (!_fromFileIn.getAbsolutePath().contentEquals(
					_toFileIn.getAbsolutePath()))
			{
				functionIn.execute(_fromFileIn, _toFileIn);
			}
		}, toFilePathIn), fromFilePathIn);
	}

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFileIn and toFileIn for same
	 * files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final File fromFileIn,
	                                     final File toFileIn)
	{
		FileUtils.recursively(_fromFileIn -> FileUtils.recursively(_toFileIn ->
		{
			if (!_fromFileIn.getAbsolutePath().contentEquals(
					_toFileIn.getAbsolutePath()))
			{
				functionIn.execute(_fromFileIn, _toFileIn);
			}
		}, toFileIn), fromFileIn);
	}

	/**
	 * Execute double file (not directories) loop functionIn method with files from fromFilePathIn and toFilePathIn
	 * except for same files paths, files (not directories)
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IDoubleFileFunction functionIn, final Path fromFilePathIn,
	                                     final Path toFilePathIn)
	{
		FileUtils.recursively(_fromFileIn -> FileUtils.recursively(_toFileIn ->
		{
			if (!_fromFileIn.getAbsolutePath().contentEquals(
					_toFileIn.getAbsolutePath()))
			{
				functionIn.execute(_fromFileIn, _toFileIn);
			}
		}, toFilePathIn), fromFilePathIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFilePathIn and toFilePathIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final String fromFilePathIn,
	                                     final String toFilePathIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, toFilePathIn);
			}
		}, fromFilePathIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFileIn and toFileIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final File fromFileIn,
	                                     final File toFileIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, toFileIn);
			}
		}, fromFileIn);
	}

	/**
	 * Execute double file loop functionIn method with filtered files (not directories) by filterFunctionIn from
	 * fromFilePathIn and toFilePathIn except for same files paths
	 *
	 * @author Seynax
	 */
	public static void doubleRecursively(final IOIFunction<Boolean, File> filterFunctionIn,
	                                     final IDoubleFileFunction functionIn, final Path fromFilePathIn,
	                                     final Path toFilePathIn)
	{
		FileUtils.recursively(_fromFileIn ->
		{
			if (!_fromFileIn.isFile() || filterFunctionIn.execute(_fromFileIn))
			{
				FileUtils.recursively(_toFileIn ->
				{
					if (!_fromFileIn.getAbsolutePath().contentEquals(
							_toFileIn.getAbsolutePath()) && (
							    !_toFileIn.isFile() || filterFunctionIn.execute(
									    _toFileIn)))
					{
						functionIn.execute(_fromFileIn, _toFileIn);
					}
				}, toFilePathIn);
			}
		}, fromFilePathIn);
	}

	// Recursively methods

	/**
	 * Execute functionIn method with all files (not directories) from filesPathIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IIFunction<File> functionIn, final String... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = new File(filePath);

			if (file.isFile())
			{
				functionIn.execute(file);
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Execute functionIn method with all files (not directories) from filesIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IIFunction<File> functionIn, final File... filesIn)
	{
		for (final var file : filesIn)
		{
			if (file.isFile())
			{
				functionIn.execute(file);
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Execute functionIn method with all files (not directories) from filesPathIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IIFunction<File> functionIn, final Path... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = filePath.toFile();

			if (file.isFile())
			{
				functionIn.execute(file);
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Execute functionIn method with all files (not directories) filtered by filterFunctionIn from filesPathIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IOIFunction<Boolean, File> filterFunctionIn, final IIFunction<File> functionIn,
	                               final String... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = new File(filePath);

			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					functionIn.execute(file);
				}
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Execute functionIn method with all files (not directories) filtered by filterFunctionIn from filesPathIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IOIFunction<Boolean, File> filterFunctionIn, final IIFunction<File> functionIn,
	                               final File... filesIn)
	{
		for (final var file : filesIn)
		{
			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					functionIn.execute(file);
				}
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Execute functionIn method with all files (not directories) filtered by filterFunctionIn from filesPathIn
	 *
	 * @author Seynax
	 */
	public static void recursively(final IOIFunction<Boolean, File> filterFunctionIn, final IIFunction<File> functionIn,
	                               final Path... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = filePath.toFile();

			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					functionIn.execute(file);
				}
			}
			else
			{
				final var files = file.listFiles();
				if (files != null)
				{
					for (final var childFile : files)
					{
						FileUtils.recursively(functionIn, childFile);
					}
				}
			}
		}
	}

	// Get

	/**
	 * @return all files (not directories) recursively from filesPathIn
	 * @author Seynax
	 */
	public static List<File> allFiles(final String... filesPathIn)
	{
		final var files = new ArrayList<File>();

		for (final var filePath : filesPathIn)
		{
			FileUtils.allFiles(files, filePath);
		}

		return files;
	}

	/**
	 * @return all files (not directories) recursively from filesIn
	 * @author Seynax
	 */
	public static List<File> allFiles(final File... filesIn)
	{
		final var files = new ArrayList<File>();

		for (final var file : filesIn)
		{
			FileUtils.allFiles(files, file);
		}

		return files;
	}

	/**
	 * @return all files (not directories) recursively from filesPathIn
	 * @author Seynax
	 */
	public static List<File> allFiles(final Path... filesPathIn)
	{
		final var files = new ArrayList<File>();

		for (final var filePath : filesPathIn)
		{
			FileUtils.allFiles(files, filePath);
		}

		return files;
	}

	/**
	 * Add all files (not directories) recursively from filesPathIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void allFiles(final List<File> filesListIn, final String... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = new File(filePath);

			if (file.isFile())
			{
				filesListIn.add(file);
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.allFiles(filesListIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Add all files (not directories) recursively from filesIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void allFiles(final List<File> filesListIn, final File... filesIn)
	{
		for (final var file : filesIn)
		{
			if (file.isFile())
			{
				filesListIn.add(file);
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.allFiles(filesListIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Add all files (not directories) recursively from filesPathIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void allFiles(final List<File> filesListIn, final Path... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = filePath.toFile();
			if (file.isFile())
			{
				filesListIn.add(file);
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.allFiles(filesListIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * @return all files (not directories) from filePathsIn filtered by filterFunctionIn
	 * @author Seynax
	 */
	public static List<File> get(final IOIFunction<Boolean, File> filterFunctionIn, final String... filesPathIn)
	{
		final var files = new ArrayList<File>();

		for (final var filePath : filesPathIn)
		{
			FileUtils.get(filterFunctionIn, files, filePath);
		}

		return files;
	}

	/**
	 * @return all files (not directories) from filesIn filtered by filterFunctionIn
	 * @author Seynax
	 */
	public static List<File> get(final IOIFunction<Boolean, File> filterFunctionIn, final File... filesIn)
	{
		final var files = new ArrayList<File>();

		for (final var file : filesIn)
		{
			FileUtils.get(filterFunctionIn, files, file);
		}

		return files;
	}

	/**
	 * @return all files (not directories) from filePathsIn filtered by filterFunctionIn
	 * @author Seynax
	 */
	public static List<File> get(final IOIFunction<Boolean, File> filterFunctionIn, final Path... filePathsIn)
	{
		final var files = new ArrayList<File>();

		for (final var filePath : filePathsIn)
		{
			FileUtils.get(filterFunctionIn, files, filePath);
		}

		return files;
	}

	/**
	 * Add all filtered files (not directories) by filterFunctionIn from filesPathIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void get(final IOIFunction<Boolean, File> filterFunctionIn, final List<File> filesListIn,
	                       final String... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = new File(filePath);
			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					filesListIn.add(file);
				}
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.get(filterFunctionIn, filesListIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Add all filtered files (not directories) by filterFunctionIn from filesIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void get(final IOIFunction<Boolean, File> filterFunctionIn, final List<File> filesListIn,
	                       final File... filesIn)
	{
		for (final var file : filesIn)
		{
			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					filesListIn.add(file);
				}
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.get(filterFunctionIn, filesListIn, childFile);
					}
				}
			}
		}
	}

	/**
	 * Add all filtered files (not directories) by filterFunctionIn from filesPathIn into filesListIn
	 *
	 * @author Seynax
	 */
	public static void get(final IOIFunction<Boolean, File> filterFunctionIn, final List<File> filesListIn,
	                       final Path... filesPathIn)
	{
		for (final var filePath : filesPathIn)
		{
			final var file = filePath.toFile();
			if (file.isFile())
			{
				if (filterFunctionIn.execute(file))
				{
					filesListIn.add(file);
				}
			}
			else
			{
				final var childFiles = file.listFiles();

				if (childFiles != null)
				{
					for (final var childFile : childFiles)
					{
						FileUtils.get(filterFunctionIn, filesListIn, childFile);
					}
				}
			}
		}
	}

	// Create directory and file methods

	/**
	 * If not exists create all directories from foldersPathIn, but if file exist and isn't directory throw IOException
	 *
	 * @throws IOException if file exist and isn't directory
	 * @author Seynax
	 */
	public static void createDirectory(final String... foldersPathIn) throws IOException
	{
		for (final var path : foldersPathIn)
		{
			final var folder = new File(path);

			if (folder.exists())
			{
				if (folder.isFile())
				{
					throw new IOException(
							"[ERROR] Unable to create folder \"" + path + "\", already exists and is file !");
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
	 * @throws IOException if file exist and isn't directory
	 * @author Seynax
	 */
	public static void createDirectory(final File... foldersIn) throws IOException
	{
		for (final var folder : foldersIn)
		{
			if (folder.exists())
			{
				if (folder.isFile())
				{
					throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath()
					                      + "\", already exists and is file !");
				}
			}
			else if (!folder.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath()
				                      + "\" (File.mkdirs method failed) !");
			}
		}
	}

	/**
	 * If not exists create all directories from foldersPathIn, but if file exist and isn't directory throw IOException
	 *
	 * @throws IOException if file exist and isn't directory
	 * @author Seynax
	 */
	@SuppressWarnings("FinalStaticMethod")
	public final static void createDirectory(final Path... foldersPathIn) throws IOException
	{
		for (final var folderPath : foldersPathIn)
		{
			final var folder = folderPath.toFile();

			if (folder.exists())
			{
				if (folder.isFile())
				{
					throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath()
					                      + "\", already exists and is file !");
				}
			}
			else if (!folder.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create folder \"" + folder.getAbsolutePath()
				                      + "\" (File.mkdirs method failed) !");
			}
		}
	}

	/**
	 * If not exists create all files from filesPathIn, but if parent of file exists and isn't directory or file exist
	 * and is directory throw IOException
	 *
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 * @author Seynax
	 */
	public static void createFile(final String... filesPathIn) throws IOException
	{
		for (final var path : filesPathIn)
		{
			final var file = file(path);

			final var parentFile = file.getParentFile();
			if (parentFile.exists())
			{
				if (parentFile.isFile())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
					                      + parentFile.getAbsolutePath()
					                      + "\" because parent file isn't directory !");
				}
			}
			else if (!parentFile.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
				                      + parentFile.getAbsolutePath()
				                      + "\" because failed to create parent directory (File.mkdirs method failed) !");
			}

			if (file.exists())
			{
				if (file.isDirectory())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
					                      + "\", already exists and is directory !");
				}
			}
			else if (!file.createNewFile())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
				                      + "\" (File.createNewFile method failed) !");
			}
		}
	}

	/**
	 * If not exists create all files from filesIn, but if parent of file exists and isn't directory or file exist and
	 * is directory throw IOException
	 *
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 * @author Seynax
	 */
	public static void createFile(final File... filesIn) throws IOException
	{
		for (final var file : filesIn)
		{
			final var parentFile = file.getParentFile();
			if (parentFile.exists())
			{
				if (parentFile.isFile())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
					                      + parentFile.getAbsolutePath()
					                      + "\" because parent file isn't directory !");
				}
			}
			else if (!parentFile.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
				                      + parentFile.getAbsolutePath()
				                      + "\" because failed to create parent directory !");
			}

			if (file.exists())
			{
				if (file.isDirectory())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
					                      + "\", already exists and is directory !");
				}
			}
			else if (!file.createNewFile())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
				                      + "\" (File.createNewFile method failed) !");
			}
		}
	}

	/**
	 * If not exists create all files from filesPathIn, but if parent of file exists and isn't directory or file exist
	 * and is directory throw IOException
	 *
	 * @throws IOException if parent of file exists and isn't directory or file exist and is directory
	 * @author Seynax
	 */
	public static void createFile(final Path... filesPathIn) throws IOException
	{
		for (final var filePath : filesPathIn)
		{
			final var file       = filePath.toFile();
			final var parentFile = file.getParentFile();
			if (parentFile.exists())
			{
				if (parentFile.isFile())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
					                      + parentFile.getAbsolutePath()
					                      + "\" because parent file isn't directory !");
				}
			}
			else if (!parentFile.mkdirs())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getName() + "\", into \""
				                      + parentFile.getAbsolutePath()
				                      + "\" because failed to create parent directory !");
			}

			if (file.exists())
			{
				if (file.isDirectory())
				{
					throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
					                      + "\", already exists and is directory !");
				}
			}
			else if (!file.createNewFile())
			{
				throw new IOException("[ERROR] Unable to create file \"" + file.getAbsolutePath()
				                      + "\" (File.createNewFile method failed) !");
			}
		}
	}

	// Read methods

	/**
	 * @return all bytes of file from filePathIn
	 * @author Seynax
	 */
	public static byte[] bytes(final String filePathIn)
	{
		return FileUtils.bytes(new File(filePathIn));
	}

	/**
	 * @return all bytes of fileIn
	 * @author Seynax
	 */
	public static byte[] bytes(final File fileIn)
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
	 * @return all bytes of file from pathIn
	 * @author Seynax
	 */
	public static byte[] bytes(final Path pathIn)
	{
		return FileUtils.bytes(pathIn.toFile());
	}

	/**
	 * read all bytes of file from filePathIn, return converted string from bytes with UTF_8 charset
	 *
	 * @return string content of file from filePathIn
	 * @author Seynax
	 */
	public static String content(final String filePathIn)
	{
		return FileUtils.content(new File(filePathIn));
	}

	/**
	 * read all bytes of fileIn, return converted string from bytes with UTF_8 charset
	 *
	 * @return string content of fileIn
	 * @author Seynax
	 */
	public static String content(final File fileIn)
	{
		try
		{
			return Files.readString(fileIn.toPath());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @return string content of file from pathIn
	 * @author Seynax
	 */
	public static String content(final Path pathIn)
	{
		return FileUtils.content(pathIn.toFile());
	}

	/**
	 * read all bytes of file from filePathIn, return converted string from bytes with charsetIn charset
	 *
	 * @return string content of file from filePathIn
	 * @author Seynax
	 */
	public static String content(final String filePathIn, final Charset charsetIn)
	{
		return FileUtils.content(new File(filePathIn), charsetIn);
	}

	/**
	 * read all bytes of fileIn, return converted string from bytes with charsetIn charset
	 *
	 * @return string content of fileIn
	 * @author Seynax
	 */
	@SuppressWarnings("FinalStaticMethod")
	public final static String content(final File fileIn, final Charset charsetIn)
	{
		try
		{
			return Files.readString(fileIn.toPath(), charsetIn);
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
	 * @return string content of file from pathIn
	 * @author Seynax
	 */
	public static String content(final Path pathIn, final Charset charsetIn)
	{
		return FileUtils.content(pathIn.toFile(), charsetIn);
	}

	/**
	 * Read line by line file from filePathIn, add line into string with "\r\n" line separator
	 *
	 * @return separated lines string content of file from filePathIn
	 * @author Seynax
	 */
	public static String contentFromLines(final String filePathIn)
	{
		return FileUtils.contentFromLines(new File(filePathIn));
	}

	/**
	 * Read line by line fileIn, add line into string with "\r\n" line separator
	 *
	 * @return separated lines string content of fileIn
	 * @author Seynax
	 */
	public static String contentFromLines(final File fileIn)
	{
		StringBuilder contentBuilder = new StringBuilder();

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				contentBuilder.append(line).append("\r\n");
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return contentBuilder.toString();
	}

	/**
	 * Read line by line file from pathIn, add line into string with "\r\n" line separator
	 *
	 * @return separated lines string content of file from pathIn
	 * @author Seynax
	 */
	public static String contentFromLines(final Path pathIn)
	{
		return FileUtils.contentFromLines(pathIn.toFile());
	}

	/**
	 * Read line by line file from filePathIn, add line with lineSeparatorIn if not null into string
	 *
	 * @return separated lines string content of file from filePathIn
	 * @author Seynax
	 */
	public static String contentFromLines(final String filePathIn, final String lineSeparatorIn)
	{
		return FileUtils.contentFromLines(new File(filePathIn), lineSeparatorIn);
	}

	/**
	 * Read line by line fileIn, add line with lineSeparatorIn if not null into string
	 *
	 * @return separated lines string content of fileIn
	 * @author Seynax
	 */
	public static String contentFromLines(final File fileIn, final String lineSeparatorIn)
	{
		StringBuilder contentBuilder = new StringBuilder();

		try (var bufferedReader = new BufferedReader(new FileReader(fileIn)))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				contentBuilder.append(line);

				if (lineSeparatorIn != null)
				{
					contentBuilder.append(lineSeparatorIn);
				}
			}
		}
		catch (final IOException exception)
		{
			exception.printStackTrace();
		}

		return contentBuilder.toString();
	}

	/**
	 * Read line by line file from pathIn, add line with lineSeparatorIn if not null into string
	 *
	 * @return separated lines string content of file from pathIn
	 * @author Seynax
	 */
	public static String contentFromLines(final Path pathIn, final String lineSeparatorIn)
	{
		return FileUtils.contentFromLines(pathIn.toFile(), lineSeparatorIn);
	}

	/**
	 * @return all lines in list of file from filePathIn
	 * @author Seynax
	 */
	public static Collection<String> lines(final String filePathIn)
	{
		return FileUtils.lines(new File(filePathIn));
	}

	/**
	 * @return all lines in list of fileIn
	 * @author Seynax
	 */
	public static Collection<String> lines(final File fileIn)
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
	 * @return all lines in list of file from pathIn
	 * @author Seynax
	 */
	public static Collection<String> lines(final Path pathIn)
	{
		return FileUtils.lines(pathIn.toFile());
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void forEachLines(final String filePathIn, final IIFunction<String> functionIn)
	{
		FileUtils.forEachLines(new File(filePathIn), functionIn);
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of fileIn
	 *
	 * @author Seynax
	 */
	public static void forEachLines(final File fileIn, final IIFunction<String> functionIn)
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
	 */
	public static void forEachLines(final Path pathIn, final IIFunction<String> functionIn)
	{
		FileUtils.forEachLines(pathIn.toFile(), functionIn);
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from filePathIn and place String return result
	 * into list
	 *
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 * @author Seynax
	 */
	public static Collection<String> forEachLinesAndAdd(final String filePathIn,
	                                                    final IOIFunction<String, String> functionIn)
	{
		return FileUtils.forEachLinesAndAdd(new File(filePathIn), functionIn);
	}

	/**
	 * Execute functionIn.execute(String lineIn) for all lines of file from pathIn and place String return result into
	 * list
	 *
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 * @author Seynax
	 */
	public static List<String> forEachLinesAndAdd(final File fileIn, final IOIFunction<String, String> functionIn)
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
	 * Execute functionIn.execute(String lineIn) for all lines of file from pathIn and place String return result into
	 * list
	 *
	 * @return list of string results returned by functionIn.execute(String lineIn) method
	 * @author Seynax
	 */
	public static List<String> forEachLinesAndAdd(final Path pathIn, final IOIFunction<String, String> functionIn)
	{
		return FileUtils.forEachLinesAndAdd(pathIn.toFile(), functionIn);
	}

	// SHA methods

	/**
	 * @return sha of file from filePathIn
	 * @author Seynax
	 */
	public static byte[] sha(final String filePathIn) throws IOException
	{
		return FileUtils.sha(new File(filePathIn));
	}

	/**
	 * @return sha of fileIn
	 * @author Seynax
	 */
	public static byte[] sha(final File fileIn) throws IOException
	{
		if (fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot get sha of directory ! \"" + fileIn.getAbsolutePath() + "\"");
		}

		MessageDigest digest = null;

		try (var fis = new FileInputStream(fileIn))
		{
			digest = MessageDigest.getInstance("SHA-1");

			var       n      = 0;
			final var buffer = new byte[8192];
			while (n != -1)
			{
				n = fis.read(buffer);
				if (n > 0)
				{
					digest.update(buffer, 0, n);
				}
			}
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
	 * @return sha of file from filePathIn
	 * @author Seynax
	 */
	public static byte[] sha(final Path filePathIn) throws IOException
	{
		return FileUtils.sha(filePathIn.toFile());
	}

	/**
	 * @return raw string from sha of file from filePathIn
	 * @author Seynax
	 */
	public static String stringSHA(final String filePathIn) throws IOException
	{
		return FileUtils.stringSHA(new File(filePathIn));
	}

	/**
	 * @return raw string from sha of fileIn
	 * @author Seynax
	 */
	public static String stringSHA(final File fileIn) throws IOException
	{
		final var sha = FileUtils.sha(fileIn);

		if (sha != null)
		{
			return StringUtils.toRawString(sha);
		}

		return null;
	}

	/**
	 * @return raw string from sha of file from filePathIn
	 * @author Seynax
	 */
	public static String stringSHA(final Path filePathIn) throws IOException
	{
		return FileUtils.stringSHA(filePathIn.toFile());
	}

	// Compare methods

	/**
	 * @return true only if files absolute paths (from fromFilePathIn and toFilePathIn) are exactly same (if fromFile is
	 * toFile)
	 * @author Seynax
	 */
	public static boolean is(final String fromFilePathIn, final String toFilePathIn)
	{
		return FileUtils.is(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * @return true only if files absolute paths (from fromFileIn and toFileIn) are exactly same (if fromFile is toFile)
	 * @author Seynax
	 */
	public static boolean is(final File fromFileIn, final File toFileIn)
	{
		return fromFileIn.getAbsolutePath().contentEquals(toFileIn.getAbsolutePath());
	}

	/**
	 * @return true only if files absolute paths (from fromFilePathIn and toFilePathIn) are exactly same (if fromFile is
	 * toFile)
	 * @author Seynax
	 */
	public static boolean is(final Path fromFilePathIn, final Path toFilePathIn)
	{
		return fromFilePathIn.toAbsolutePath().toString().contentEquals(toFilePathIn.toAbsolutePath().toString());
	}

	/**
	 * @return true only if files (from fromFilePathIn and toFilePathIn) are same length
	 * @author Seynax
	 */
	public static boolean sameLength(final String fromFilePathIn, final String toFilePathIn)
	{
		return FileUtils.sameLength(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * @return true only if files (from fromFileIn and toFileIn) are same length
	 * @author Seynax
	 */
	public static boolean sameLength(final File fromFileIn, final File toFileIn)
	{
		return fromFileIn.length() == toFileIn.length();
	}

	/**
	 * @return true only if files (from fromFilePathIn and toFilePathIn) are same length
	 * @author Seynax
	 */
	public static boolean sameLength(final Path fromFilePathIn, final Path toFilePathIn)
	{
		return FileUtils.sameLength(fromFilePathIn.toFile(), toFilePathIn.toFile());
	}

	/**
	 * ATTENTION ! If files are exatly sames paths (if fromFileIn strictly equal to toFIleIn) return true
	 *
	 * @return true only if files/directories (from fromFilePathIn and toFilePathIn) are same contents
	 * (type[directory/file], length, sha)
	 * @author Seynax
	 */
	public static boolean sameContent(final String fromFilePathIn, final String toFilePathIn) throws IOException
	{
		return FileUtils.sameContent(new File(fromFilePathIn), new File(toFilePathIn));
	}

	/**
	 * ATTENTION ! If files are exatly sames paths (if fromFileIn strictly equal to toFIleIn) return true
	 *
	 * @return true only if files/directories (from fromFileIn and toFileIn) are same contents (type[directory/file],
	 * length, sha)
	 * @author Seynax
	 */
	public static boolean sameContent(final File fromFileIn, final File toFileIn) throws IOException
	{
		if (!fromFileIn.exists())
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot compare files because \"" + fromFileIn.getAbsolutePath() + "\" not exists ! (\""
					+ fromFileIn.getAbsolutePath() + " <=> " + toFileIn.getAbsolutePath() + "\")");
		}
		if (!toFileIn.exists())
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot compare files because \"" + toFileIn.getAbsolutePath() + "\" not exists ! (\""
					+ fromFileIn.getAbsolutePath() + " <=> " + toFileIn.getAbsolutePath() + "\")");
		}

		if (fromFileIn.getAbsolutePath().contentEquals(toFileIn.getAbsolutePath()))
		{
			return false;
		}

		if (fromFileIn.length() != toFileIn.length())
		{
			return true;
		}

		if (fromFileIn.isDirectory())
		{
			if (toFileIn.isDirectory())
			{
				final var fromChildFiles = fromFileIn.listFiles();
				final var toChildFiles   = toFileIn.listFiles();

				if (fromChildFiles == null)
				{
					return toChildFiles != null;
				}

				if (fromChildFiles.length != toChildFiles.length)
				{
					return true;
				}

				for (var i = 0; i < fromChildFiles.length; i++)
				{
					if (FileUtils.sameContent(fromChildFiles[i], toChildFiles[i]))
					{
						return true;
					}
				}

				return false;
			}

			return true;
		}

		if (toFileIn.isFile())
		{
			final var fromSHA = FileUtils.sha(fromFileIn);
			final var toSHA   = FileUtils.sha(toFileIn);

			for (var i = 0; i < Objects.requireNonNull(fromSHA).length; i++)
			{
				if (fromSHA[i] != Objects.requireNonNull(toSHA)[i])
				{
					return true;
				}
			}

			return false;
		}

		return true;
	}

	/**
	 * ATTENTION ! If files are exatly sames paths (if fromFileIn strictly equal to toFIleIn) return true
	 *
	 * @return true only if files/directories (from fromFilePathIn and toFilePathIn) are same contents
	 * (type[directory/file], length, sha)
	 * @author Seynax
	 */
	public static boolean sameContent(final Path fromFilePathIn, final Path toFilePathIn) throws IOException
	{
		return FileUtils.sameContent(fromFilePathIn.toFile(), toFilePathIn.toFile());
	}

	// Write methods

	/**
	 * Write all bytes from bytesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void write(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void write(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.write(fileIn.toPath(), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	@SuppressWarnings("FinalStaticMethod")
	public final static void write(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot write in \"" + filePathIn
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		Files.write(filePathIn, bytesIn);
	}

	/**
	 * Write all chars from charsIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void write(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), charsIn);
	}

	/**
	 * Write all chars from charsIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void write(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
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
	 */
	public static void write(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), charsIn);
	}

	/**
	 * Write String content from contentIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void write(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), contentIn);
	}

	/**
	 * Write String content from contentIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void write(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
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
	 */
	public static void write(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), contentIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void write(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void write(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
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
	 */
	public static void write(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void write(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.write(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void write(final File fileIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
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

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
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
	 */
	public static void write(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.write(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void writeEdit(final String filePathIn, final List<String> linesIn,
	                             final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.writeEdit(new File(filePathIn), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void writeEdit(final File fileIn, final List<String> linesIn,
	                             final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line)).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from filePathIn
	 *
	 * @author Seynax
	 */
	public static void writeEdit(final Path filePathIn, final List<String> linesIn,
	                             final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.writeEdit(filePathIn.toFile(), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn
	 *
	 * @author Seynax
	 */
	@SuppressWarnings("FinalStaticMethod")
	public final static void writeEdit(final String filePathIn, final List<String> linesIn,
	                                   final String lineSeparatorIn, final IOIFunction<String, String> filterFunctionIn)
	throws IOException
	{
		FileUtils.writeEdit(new File(filePathIn), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into fileIn
	 *
	 * @author Seynax
	 */
	public static void writeEdit(final File fileIn, final List<String> linesIn, final String lineSeparatorIn,
	                             final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because already exists ! Use replace(...) method to replace file content or append(...) to add after existing content.");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line));

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn
	 *
	 * @author Seynax
	 */
	public static void writeEdit(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn,
	                             final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.writeEdit(filePathIn.toFile(), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	// Append methods

	/**
	 * Append all bytes from bytesIn into file from filePathIn<br> ATTENTION ! Content is added after existing content
	 * in file.
	 *
	 * @author Seynax
	 */
	public static void append(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.append(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Append all bytes from bytesIn into fileIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.append(fileIn.toPath(), bytesIn);
	}

	/**
	 * Append all bytes from bytesIn into file from filePathIn<br> ATTENTION ! Content is added after existing content
	 * in file.
	 *
	 * @author Seynax
	 */
	public static void append(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn) && Files.isDirectory(filePathIn))
		{
			throw new IOException(
					"[ERROR] Cannot write in \"" + filePathIn + "\" because file exists and is directory !");
		}

		Files.write(filePathIn, bytesIn, StandardOpenOption.APPEND);
	}

	/**
	 * Append all chars from charsIn into file from filePathIn<br> ATTENTION ! Content is added after existing content
	 * in file.
	 *
	 * @author Seynax
	 */
	public static void append(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.append(new File(filePathIn), charsIn);
	}

	/**
	 * Append all chars from charsIn into fileIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(charsIn);
		}
	}

	/**
	 * Append all chars from charsIn into file from filePathIn<br> ATTENTION ! Content is added after existing content
	 * in file.
	 *
	 * @author Seynax
	 */
	public static void append(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.append(filePathIn.toFile(), charsIn);
	}

	/**
	 * Append String content from contentIn into file from filePathIn<br> ATTENTION ! Content is added after existing
	 * content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.append(new File(filePathIn), contentIn);
	}

	/**
	 * Append String content from contentIn into fileIn<br> ATTENTION ! Content is added after existing content in
	 * file.
	 *
	 * @author Seynax
	 */
	public static void append(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(contentIn);
		}
	}

	/**
	 * Append String content from contentIn into file from filePathIn<br> ATTENTION ! Content is added after existing
	 * content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.append(filePathIn.toFile(), contentIn);
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into file from filePathIn<br> ATTENTION ! Content is added
	 * after existing content in file.
	 *
	 * @author Seynax
	 */
	@SuppressWarnings("FinalStaticMethod")
	public final static void append(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.append(new File(filePathIn), linesIn);
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into fileIn<br> ATTENTION ! Content is added after existing
	 * content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Append all lines separated by "\r\n" from linesIn into file from filePathIn<br> ATTENTION ! Content is added
	 * after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.append(filePathIn.toFile(), linesIn);
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br> ATTENTION ! Content is
	 * added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.append(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into fileIn<br> ATTENTION ! Content is added after
	 * existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final File fileIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
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

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn, true)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Append all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br> ATTENTION ! Content is
	 * added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void append(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.append(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final String filePathIn, final List<String> linesIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.appendEdit(new File(filePathIn), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into fileIn<br> ATTENTION !
	 * Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final File fileIn, final List<String> linesIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line)).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final Path filePathIn, final List<String> linesIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.appendEdit(filePathIn.toFile(), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.appendEdit(new File(filePathIn), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into fileIn<br>
	 * ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final File fileIn, final List<String> linesIn, final String lineSeparatorIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line));

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content is added after existing content in file.
	 *
	 * @author Seynax
	 */
	public static void appendEdit(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn,
	                              final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.appendEdit(filePathIn.toFile(), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	// Replace methods

	/**
	 * Write all bytes from bytesIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with this
	 * method.
	 *
	 * @author Seynax
	 */
	public static void replace(final String filePathIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.replace(Paths.get(filePathIn), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into fileIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final File fileIn, final byte[] bytesIn) throws IOException
	{
		FileUtils.replace(fileIn.toPath(), bytesIn);
	}

	/**
	 * Write all bytes from bytesIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with this
	 * method.
	 *
	 * @author Seynax
	 */
	public static void replace(final Path filePathIn, final byte[] bytesIn) throws IOException
	{
		if (Files.exists(filePathIn) && Files.isDirectory(filePathIn))
		{
			throw new IOException(
					"[ERROR] Cannot write in \"" + filePathIn + "\" because file exists and is directory !");
		}

		Files.write(filePathIn, bytesIn);
	}

	/**
	 * Write all chars from charsIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with this
	 * method.
	 *
	 * @author Seynax
	 */
	public static void replace(final String filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.replace(new File(filePathIn), charsIn);
	}

	/**
	 * Write all chars from charsIn into fileIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final File fileIn, final char[] charsIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(charsIn);
		}
	}

	/**
	 * Write all chars from charsIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with this
	 * method.
	 *
	 * @author Seynax
	 */
	public static void replace(final Path filePathIn, final char[] charsIn) throws IOException
	{
		FileUtils.replace(filePathIn.toFile(), charsIn);
	}

	/**
	 * Write String content from contentIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with
	 * this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final String filePathIn, final String contentIn) throws IOException
	{
		FileUtils.replace(new File(filePathIn), contentIn);
	}

	/**
	 * Write String content from contentIn into fileIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final File fileIn, final String contentIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(contentIn);
		}
	}

	/**
	 * Write String content from contentIn into file from filePathIn<br> ATTENTION ! Content of file is replaced with
	 * this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final Path filePathIn, final String contentIn) throws IOException
	{
		FileUtils.replace(filePathIn.toFile(), contentIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn<br> ATTENTION ! Content of file is
	 * replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final String filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.replace(new File(filePathIn), linesIn);
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into fileIn<br> ATTENTION ! Content of file is replaced with
	 * this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final File fileIn, final List<String> linesIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(line).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by "\r\n" from linesIn into file from filePathIn<br> ATTENTION ! Content of file is
	 * replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final Path filePathIn, final List<String> linesIn) throws IOException
	{
		FileUtils.replace(filePathIn.toFile(), linesIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br> ATTENTION ! Content of
	 * file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.replace(new File(filePathIn), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into fileIn<br> ATTENTION ! Content of file is replaced
	 * with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final File fileIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
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

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines separated by lineSeparatorIn from linesIn into file from filePathIn<br> ATTENTION ! Content of
	 * file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replace(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn)
	throws IOException
	{
		FileUtils.replace(filePathIn.toFile(), linesIn, lineSeparatorIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final String filePathIn, final List<String> linesIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.replaceByEdit(new File(filePathIn), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into fileIn<br> ATTENTION !
	 * Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final File fileIn, final List<String> linesIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line)).append("\r\n");
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by "\r\n" from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final Path filePathIn, final List<String> linesIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.replaceByEdit(filePathIn.toFile(), linesIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final String filePathIn, final List<String> linesIn, final String lineSeparatorIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.replaceByEdit(new File(filePathIn), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into fileIn<br>
	 * ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final File fileIn, final List<String> linesIn, final String lineSeparatorIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		if (fileIn.exists() && fileIn.isDirectory())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\" because file exists and is directory !");
		}

		final var content = new StringBuilder();
		for (final var line : linesIn)
		{
			content.append(filterFunctionIn.execute(line));

			if (lineSeparatorIn != null)
			{
				content.append(lineSeparatorIn);
			}
		}

		final var parentFile = fileIn.getParentFile();
		if (!parentFile.exists() && !parentFile.mkdirs())
		{
			throw new IOException("[ERROR] Cannot write in \"" + fileIn.getAbsolutePath()
			                      + "\", parent directory not exists and creation failure !");
		}

		try (var bufferedWriter = new BufferedWriter(new FileWriter(fileIn)))
		{
			bufferedWriter.write(content.toString());
		}
	}

	/**
	 * Write all lines returned by filterFunctionIn method separated by lineSeparatorIn from linesIn into file from
	 * filePathIn<br> ATTENTION ! Content of file is replaced with this method.
	 *
	 * @author Seynax
	 */
	public static void replaceByEdit(final Path filePathIn, final List<String> linesIn, final String lineSeparatorIn,
	                                 final IOIFunction<String, String> filterFunctionIn) throws IOException
	{
		FileUtils.replaceByEdit(filePathIn.toFile(), linesIn, lineSeparatorIn, filterFunctionIn);
	}

	// Copy methods

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void copyFile(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.copyFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void copyFile(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.copyFile(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void copyFile(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException("[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + " \"  because destination file/directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		if (Files.isDirectory(sourcePathIn))
		{
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						Files.copy(sourceIn, destination);
					}
					catch (final IOException e)
					{
						e.printStackTrace();

						return false;
					}

					return true;
				});
			}
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
	 */
	public static void copyDirectory(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.copyDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy all files from sourceIn into destinationIn
	 *
	 * @author Seynax
	 */
	public static void copyDirectory(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.copyDirectory(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void copyDirectory(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException("[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + " \"  because destination directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					Files.copy(sourceIn, destination);
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.appendIntoFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.appendIntoFile(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
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
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						if (Files.isDirectory(sourceIn))
						{
							Files.createDirectories(destination);
						}
						else
						{
							FileUtils.append(destination, FileUtils.content(sourceIn));
						}
					}
					catch (final IOException e)
					{
						e.printStackTrace();

						return false;
					}

					return true;
				});
			}
		}
		else
		{
			FileUtils.append(destinationPathIn, FileUtils.content(sourcePathIn));
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.appendIntoDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy all files from sourceIn into destinationIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.appendIntoDirectory(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
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

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					if (Files.isDirectory(sourceIn))
					{
						Files.createDirectories(destination);
					}
					else
					{
						FileUtils.append(destination, FileUtils.content(sourceIn));
					}
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.replaceFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.replaceFile(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy file/directory from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if
	 * exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
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
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						Files.copy(sourceIn, destination);
					}
					catch (final IOException e)
					{
						e.printStackTrace();
						return false;
					}
					return true;
				});
			}
		}
		else
		{
			Files.copy(sourcePathIn, destinationPathIn);
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.replaceDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn));
	}

	/**
	 * Copy all files from sourceIn into destinationIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final File sourceFileIn, final File destinationFileIn) throws IOException
	{
		FileUtils.replaceDirectory(sourceFileIn.toPath(), destinationFileIn.toPath());
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
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

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					Files.copy(sourceIn, destination);
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	// Copy methods with lines filter function

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br>
	 *
	 * @author Seynax
	 */
	public static void copyFile(final String sourcePathIn, final String destinationPathIn,
	                            final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.copyFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br>
	 *
	 * @author Seynax
	 */
	public static void copyFile(final File sourceFileIn, final File destinationFileIn,
	                            final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.copyFile(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br>
	 *
	 * @author Seynax
	 */
	public static void copyFile(final Path sourcePathIn, final Path destinationPathIn,
	                            final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException("[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + " \"  because destination file/directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		if (Files.isDirectory(sourcePathIn))
		{
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						if (Files.isDirectory(sourceIn))
						{
							Files.createDirectory(destination);
						}
						else
						{
							final var content = new StringBuilder();
							FileUtils.forEachLines(sourceIn,
									line -> content.append(line).append("\r\n"));
							FileUtils.write(destination, content.toString());
						}
					}
					catch (final IOException e)
					{
						e.printStackTrace();

						return false;
					}

					return true;
				});
			}
		}
		else
		{
			final var content = new StringBuilder();
			FileUtils.forEachLines(sourcePathIn, line -> content.append(line).append("\r\n"));
			FileUtils.write(destinationPathIn, content.toString());
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn
	 *
	 * @author Seynax
	 */
	public static void copyDirectory(final String sourcePathIn, final String destinationPathIn,
	                                 final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.copyDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn
	 *
	 * @author Seynax
	 */
	public static void copyDirectory(final File sourceFileIn, final File destinationFileIn,
	                                 final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.copyDirectory(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn
	 *
	 * @author Seynax
	 */
	public static void copyDirectory(final Path sourcePathIn, final Path destinationPathIn,
	                                 final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
		}

		if (Files.exists(destinationPathIn))
		{
			throw new IOException("[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + " \"  because destination directory already exists ! Use replace(...) method to replace destination file.");
		}

		final var parent = destinationPathIn.getParent();
		if (!Files.exists(parent))
		{
			Files.createDirectories(parent);
		}

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					FileUtils.copyFile(sourceIn, destination, linesFilterFunctionIn);
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	/**
	 * Append content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final String sourcePathIn, final String destinationPathIn,
	                                  final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.appendIntoFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Append content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final File sourceFileIn, final File destinationFileIn,
	                                  final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.appendIntoFile(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Append content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoFile(final Path sourcePathIn, final Path destinationPathIn,
	                                  final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
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
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						if (Files.isDirectory(sourceIn))
						{
							Files.createDirectories(destination);
						}
						else
						{
							final var content = new StringBuilder();
							FileUtils.forEachLines(sourceIn,
									line -> content.append(line).append("\r\n"));
							FileUtils.append(destination, content.toString());
						}
					}
					catch (final IOException e)
					{
						e.printStackTrace();

						return false;
					}

					return true;
				});
			}
		}
		else
		{
			final var content = new StringBuilder();
			FileUtils.forEachLines(sourcePathIn, line -> content.append(line).append("\r\n"));
			FileUtils.append(destinationPathIn, content.toString());
		}
	}

	/**
	 * Append all files contents all files from sourcePathIn into destinationPathIn by filtering each line of each file
	 * by linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final String sourcePathIn, final String destinationPathIn,
	                                       final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.appendIntoDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Append all files contents all files from sourcePathIn into destinationPathIn by filtering each line of each file
	 * by linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final File sourceFileIn, final File destinationFileIn,
	                                       final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.appendIntoDirectory(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Append all files contents from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void appendIntoDirectory(final Path sourcePathIn, final Path destinationPathIn,
	                                       final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
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

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					if (Files.isDirectory(sourceIn))
					{
						Files.createDirectories(destination);
					}
					else
					{
						FileUtils.appendIntoFile(sourceIn, destination, linesFilterFunctionIn);
					}
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final String sourcePathIn, final String destinationPathIn,
	                               final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.replaceFile(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final File sourceFileIn, final File destinationFileIn,
	                               final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.replaceFile(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Copy content from file/directory of sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceFile(final Path sourcePathIn, final Path destinationPathIn,
	                               final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source file/directory not exists !");
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
			try (var stream = Files.walk(sourcePathIn))
			{
				stream.takeWhile(sourceIn ->
				{
					final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
							.substring(
									sourcePathIn.toString()
											.length()));

					try
					{
						final var content = new StringBuilder();
						FileUtils.forEachLines(sourceIn, line -> content.append(line).append("\r\n"));
						FileUtils.replace(destination, content.toString());
					}
					catch (final IOException e)
					{
						e.printStackTrace();

						return false;
					}

					return true;
				});
			}
		}
		else
		{
			final var content = new StringBuilder();
			FileUtils.forEachLines(sourcePathIn, line -> content.append(line).append("\r\n"));
			FileUtils.replace(destinationPathIn, content.toString());
		}
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final String sourcePathIn, final String destinationPathIn,
	                                    final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.replaceDirectory(Paths.get(sourcePathIn), Paths.get(destinationPathIn), linesFilterFunctionIn);
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final File sourceFileIn, final File destinationFileIn,
	                                    final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		FileUtils.replaceDirectory(sourceFileIn.toPath(), destinationFileIn.toPath(), linesFilterFunctionIn);
	}

	/**
	 * Copy all files from sourcePathIn into destinationPathIn by filtering each line of each file by
	 * linesFilterFunctionIn<br> ATTENTION ! Destination file is replaced if exists !
	 *
	 * @author Seynax
	 */
	public static void replaceDirectory(final Path sourcePathIn, final Path destinationPathIn,
	                                    final IOIFunction<String, String> linesFilterFunctionIn) throws IOException
	{
		if (!Files.exists(sourcePathIn))
		{
			throw new FileNotFoundException(
					"[ERROR] Cannot copy \"" + sourcePathIn + "\" into \"" + destinationPathIn.toString()
					+ "\", because source directory not exists !");
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

		try (var stream = Files.walk(sourcePathIn))
		{
			stream.takeWhile(sourceIn ->
			{
				final var destination = Paths.get(destinationPathIn.toString(), sourceIn.toString()
						.substring(
								sourcePathIn.toString()
										.length()));

				try
				{
					FileUtils.replaceFile(sourceIn, destination, linesFilterFunctionIn);
				}
				catch (final IOException e)
				{
					e.printStackTrace();

					return false;
				}

				return true;
			});
		}
	}

	// Move methods

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void move(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.copyFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	/**
	 * Move file(s) from sourceFIleIn into destinationFileIn
	 *
	 * @author Seynax
	 */
	public static void move(final File sourceFIleIn, final File destinationFileIn) throws IOException
	{
		FileUtils.copyFile(sourceFIleIn, destinationFileIn);

		if (FileUtils.sameContent(sourceFIleIn, destinationFileIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourceFIleIn.getAbsolutePath() + "\" into \""
			                      + destinationFileIn.getAbsolutePath()
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourceFIleIn);
	}

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn
	 *
	 * @author Seynax
	 */
	public static void move(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		FileUtils.copyFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced !
	 *
	 * @author Seynax
	 */
	public static void moveAppend(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.appendIntoFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	/**
	 * Move file(s) from sourceFIleIn into destinationFileIn<br> ATTENTION ! Content is added after existing content in
	 * destination file !
	 *
	 * @author Seynax
	 */
	public static void moveAppend(final File sourceFIleIn, final File destinationFileIn) throws IOException
	{
		FileUtils.appendIntoFile(sourceFIleIn, destinationFileIn);

		if (FileUtils.sameContent(sourceFIleIn, destinationFileIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourceFIleIn.getAbsolutePath() + "\" into \""
			                      + destinationFileIn.getAbsolutePath()
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourceFIleIn);
	}

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn<br> ATTENTION ! Content is added after existing content in
	 * destination file !
	 *
	 * @author Seynax
	 */
	public static void moveAppend(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		FileUtils.appendIntoFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn<br> ATTENTION ! Content is added after existing content in
	 * destination file !
	 *
	 * @author Seynax
	 */
	public static void moveReplace(final String sourcePathIn, final String destinationPathIn) throws IOException
	{
		FileUtils.replaceFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	/**
	 * Move file(s) from sourceFIleIn into destinationFileIn<br> ATTENTION ! Destination file is replaced !
	 *
	 * @author Seynax
	 */
	public static void moveReplace(final File sourceFIleIn, final File destinationFileIn) throws IOException
	{
		FileUtils.replaceFile(sourceFIleIn, destinationFileIn);

		if (FileUtils.sameContent(sourceFIleIn, destinationFileIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourceFIleIn.getAbsolutePath() + "\" into \""
			                      + destinationFileIn.getAbsolutePath()
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourceFIleIn);
	}

	/**
	 * Move file(s) from sourcePathIn into destinationPathIn<br> ATTENTION ! Destination file is replaced !
	 *
	 * @author Seynax
	 */
	public static void moveReplace(final Path sourcePathIn, final Path destinationPathIn) throws IOException
	{
		FileUtils.replaceFile(sourcePathIn, destinationPathIn);

		if (FileUtils.sameContent(sourcePathIn, destinationPathIn))
		{
			throw new IOException("[ERROR] Moving of \"" + sourcePathIn + "\" into \"" + destinationPathIn
			                      + "\" the destination does not have the same content as the source !");
		}

		FileUtils.delete(sourcePathIn);
	}

	// Delete methods

	/**
	 * Delete file (file or directory) from filePathIn
	 *
	 * @author Seynax
	 */
	public static void delete(final String filePathIn) throws IOException
	{
		FileUtils.delete(Paths.get(filePathIn));
	}

	/**
	 * Delete fileIn (file or directory)
	 *
	 * @author Seynax
	 */
	public static void delete(final File fileIn) throws IOException
	{
		FileUtils.delete(fileIn.toPath());
	}

	/**
	 * Delete file (file or directory) from filePathIn
	 *
	 * @author Seynax
	 */
	public static void delete(final Path filePathIn) throws IOException
	{
		if (!Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot remove \"" + filePathIn + "\" because not exists !");
		}

		if (Files.isDirectory(filePathIn))
		{
			try (var stream = Files.walk(filePathIn))
			{
				stream.sorted(Comparator.reverseOrder()).map(Path::toFile).takeWhile(File::delete);
			}
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
	 */
	public static void deleteDirectory(final String filePathIn) throws IOException
	{
		FileUtils.deleteDirectory(Paths.get(filePathIn));
	}

	/**
	 * Delete fileIn directory
	 *
	 * @author Seynax
	 */
	public static void deleteDirectory(final File fileIn) throws IOException
	{
		FileUtils.deleteDirectory(fileIn.toPath());
	}

	/**
	 * Delete directory from filePathIn
	 *
	 * @author Seynax
	 */
	public static void deleteDirectory(final Path filePathIn) throws IOException
	{
		if (!Files.exists(filePathIn))
		{
			throw new IOException("[ERROR] Cannot remove \"" + filePathIn + "\" because not exists !");
		}

		try (var stream = Files.walk(filePathIn))
		{
			stream.sorted(Comparator.reverseOrder()).map(Path::toFile).takeWhile(File::delete);
		}
	}

	/**
	 * Delete all files (file or directory) from filePathIn
	 *
	 * @author Seynax
	 */
	public static void deletes(final String... filesPathIn) throws IOException
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
	 */
	public static void deletes(final File... filesIn) throws IOException
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
	 */
	public static void deletes(final Path... filesPathIn) throws IOException
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
	 */
	public static void deleteDirectories(final String... filePathsIn) throws IOException
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
	 */
	public static void deleteDirectories(final File... filesIn) throws IOException
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
	 */
	public static void deleteDirectories(final Path... filesPathIn) throws IOException
	{
		for (final var path : filesPathIn)
		{
			FileUtils.deleteDirectory(path);
		}
	}

	public interface IDoubleFileFunction
	{
		void execute(final File fromFileIn, final File toFileIn);
	}
}