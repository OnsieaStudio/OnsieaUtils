package fr.onsiea.utils.stringbuilder;

import fr.onsiea.utils.string.StringUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

public class StringBuilderCache
{
	private final static ConcurrentLinkedQueue<CachedStringBuilder> STRING_BUILDERS = new ConcurrentLinkedQueue<>();

	public final static int appendAllNonNull(CachedStringBuilder stringBuilderIn, String... contentsIn)
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

	public final static boolean appendNonNull(CachedStringBuilder stringBuilderIn, String contentIn)
	{
		if (contentIn != null)
		{
			stringBuilderIn.append(contentIn);

			return true;
		}

		return false;
	}

	public final static int appendAllNonBlank(CachedStringBuilder stringBuilderIn, String... contentsIn)
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

	public final static boolean appendNonBlank(CachedStringBuilder stringBuilderIn, String contentIn)
	{
		if (!StringUtils.isBlank(contentIn))
		{
			stringBuilderIn.append(contentIn);

			return true;
		}

		return false;
	}

	public final static CachedStringBuilder use()
	{
		for (var stringBuilder : STRING_BUILDERS)
		{
			if (stringBuilder.used())
			{
				continue;
			}

			stringBuilder.use();
			return stringBuilder;
		}

		var stringBuilder = new CachedStringBuilder();
		stringBuilder.use();

		STRING_BUILDERS.add(stringBuilder);

		return stringBuilder;
	}
}