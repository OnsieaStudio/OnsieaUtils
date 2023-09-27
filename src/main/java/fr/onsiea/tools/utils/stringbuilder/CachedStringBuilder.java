package fr.onsiea.tools.utils.stringbuilder;

import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
public class CachedStringBuilder
{
	private final @Delegate StringBuilder stringBuilder;

	private boolean used;

	public CachedStringBuilder()
	{
		stringBuilder = new StringBuilder();
	}

	public final void use()
	{
		stringBuilder.setLength(0);
		used = true;
	}

	public final void free()
	{
		stringBuilder.setLength(0);
		used = false;
	}

	@Override
	public int hashCode()
	{
		return stringBuilder.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		return stringBuilder.equals(obj);
	}

	@Override
	public String toString()
	{
		return stringBuilder.toString();
	}
}