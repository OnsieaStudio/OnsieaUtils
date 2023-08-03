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

import java.io.File;
import java.io.IOException;

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
}