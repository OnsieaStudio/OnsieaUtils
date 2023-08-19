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
package fr.onsiea.utils.text.colorizer;

import fr.onsiea.utils.style.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colorizer
{
	private final static String CONTENT_BASE_BALISE_TAG = "<content>";
	private final static ITag CONTENT_BASE_TAG = new Tag(Colorizer.CONTENT_BASE_BALISE_TAG, ".*", Pattern.DOTALL);

	public static void main(final String[] argsIn)
	{
		System.out.println(Colorizer.colorizeJava("""
				/**
				 * Copyright 2021-2023 Onsiea Studio All rights reserved.<br>
				 * <br>
				 *
				 * This file is part of Onsiea Engine project.
				 * (https://github.com/OnsieaStudio/OnsieaEngine)<br>
				 * <br>
				 *
				 * Onsiea Engine is [licensed]
				 * (https://github.com/OnsieaStudio/OnsieaEngine/blob/main/LICENSE) under the terms of
				 * the "GNU General Public Lesser License v2.1" (LGPL-2.1).
				 * https://github.com/OnsieaStudio/OnsieaEngine/wiki/License#license-and-copyright<br>
				 * <br>
				 *
				 * Onsiea Engine is free software: you can redistribute it and/or modify
				 * it under the terms of the GNU Lesser General Public License as published by
				 * the Free Software Foundation, either version 2.1 of the License, or
				 * (at your option) any later version.<br>
				 * <br>
				 *
				 * Onsiea Engine is distributed in the hope that it will be useful,
				 * but WITHOUT ANY WARRANTY; without even the implied warranty of
				 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
				 * GNU Lesser General Public License for more details.<br>
				 * <br>
				 *
				 * You should have received a copy of the GNU Lesser General Public License
				 * along with Onsiea Engine. If not, see <https://www.gnu.org/licenses/>.<br>
				 * <br>
				 *
				 * Neither the name "Onsiea Studio", "Onsiea Engine", or any derivative name or the
				 * names of its authors / contributors may be used to endorse or promote
				 * products derived from this software and even less to name another project or
				 * other work without clear and precise permissions written in advance.<br>
				 * <br>
				 *
				 * @Author : Seynax (https://github.com/seynax)<br>
				 * @Organization : Onsiea Studio (https://github.com/OnsieaStudio)
				 */
				package fr.onsiea.utils.text.colorizer;

				import java.util.LinkedHashMap;
				import java.util.List;
				import java.util.Map;
				import java.util.regex.Pattern;

				import fr.onsiea.utils.style.Style;

				public class Colorizer
				{
					public final static void main(final String[] argsIn)
					{
						final Map<String, String[]> simpleTextsStylesIn = new LinkedHashMap<>();
						simpleTextsStylesIn.put(Style.Color.RED, new String[] {"package", "import", "public", "private", "class", "interface", "enum", "final", "static", "void", "var", "byte", "short", "char", "int", "float", "double", "long", "while", "for", "do", "return"})


						final Map<String, String[]> regexesStylesIn = new LinkedHashMap<>();
						System.out.println(Colorizer.colorize(simpleTextsStylesIn, regexesStylesIn, ));
					}

					public final static String colorize(final Map<String, String[]> finalSimpleTextsStylesIn, final Map<String, String[]> regexesStylesIn, final String contentIn)
					{
						var content = contentIn;

						for (final var entry : regexesStylesIn.entrySet())
						{
							final var style = entry.getKey();

							for (final var regex : entry.getValue())
							{
								final var	pattern	= Pattern.compile(regex);
								final var	matcher	= pattern.matcher(content);

								while (matcher.find())
								{
									content = content.replaceAll(matcher.group(), style + matcher.group() + Style.BASE);
								}
							}
						}

						for (final var entry : regexesStylesIn.entrySet())
						{
							final var style = entry.getKey();

							for (final var text : entry.getValue())
							{
								content = content.replace(text, style + text + Style.BASE);
							}
						}
						//   \tTEST\t

						return content;
					}
				}
								"""));
	}

	public static String enclosed(final String beforeIn)
	{
		return beforeIn + Colorizer.CONTENT_BASE_BALISE_TAG + Style.RESET;
	}

	public static String enclosed(final String beforeIn, final String afterIn)
	{
		return beforeIn + Colorizer.CONTENT_BASE_BALISE_TAG + afterIn;
	}

	public static String wholeWord(final String wordIn)
	{
		return "(^|[.[^a-zA-Z0-9_]]|[ \t\r\n])" + Pattern.quote(wordIn) + "([.[^a-zA-Z0-9_]]|[ \t\r\n\\.]$)+";
	}

	public static String wholeRegex(final String regexIn)
	{
		return "(^|[.[^a-zA-Z0-9_]]|[ \t\r\n])" + regexIn + "([.[^a-zA-Z0-9_]]|[ \t\r\n\\.]|$)";
	}

	public static ColorizerPattern[] wholeWords(final String conversionIn, final String... wordsIn)
	{
		final var wholeWordsColorizerPattern = new ColorizerPattern[wordsIn.length];

		for (var i = 0; i < wholeWordsColorizerPattern.length; i++)
		{
			wholeWordsColorizerPattern[i] = new ColorizerPattern.Builder(Colorizer.wholeWord(wordsIn[i]),
					conversionIn).elementsToModifyRegex(wordsIn[i]).detectionPatternFlags(Pattern.DOTALL).build();
		}

		return wholeWordsColorizerPattern;
	}

	@SuppressWarnings("FinalStaticMethod")
	public final static ColorizerPattern[] wholeLineWords(final String conversionIn, final String... wordsIn)
	{
		final var wholeWordsColorizerPattern = new ColorizerPattern[wordsIn.length];

		for (var i = 0; i < wholeWordsColorizerPattern.length; i++)
		{
			wholeWordsColorizerPattern[i] = new ColorizerPattern.Builder(
					"(^|[.[^a-zA-Z0-9_]]|[ \t\r\n])" + Pattern.quote(wordsIn[i]) + "[.[^;]]*;",
					conversionIn).elementsToModifyRegex(wordsIn[i]).elementsToKeepRegex(wordsIn[i] + ".*;")
					.detectionPatternFlags(Pattern.DOTALL).build();
		}

		return wholeWordsColorizerPattern;
	}

	public static ColorizerPattern[] wholeRegexes(final String conversionIn, final String... regexesIn)
	{
		final var wholeWordsColorizerPattern = new ColorizerPattern[regexesIn.length];

		for (var i = 0; i < wholeWordsColorizerPattern.length; i++)
		{
			wholeWordsColorizerPattern[i] = new ColorizerPattern.Builder(Colorizer.wholeRegex(regexesIn[i]),
					conversionIn).elementsToModifyRegex(regexesIn[i]).detectionPatternFlags(Pattern.DOTALL).build();
		}

		return wholeWordsColorizerPattern;
	}

	public static String colorizeJava(final String contentIn)
	{
		// classes :

		final var patterns = new ColorizerPatterns().group(Colorizer.enclosed(Style.Color.BLACK))
				.add("(?s)/\\*(.*?\\*/)", // Comment block
						"//.*" // Comment line
				).end().add("(?s)\"(.*?\\\")", Colorizer.enclosed(Style.Color.GREEN))
				.add(Colorizer.wholeLineWords(Colorizer.enclosed(Style.Color.RED), "package", "import"))
				.add(Colorizer.wholeWords(Colorizer.enclosed(Style.Color.RED), "public", "private", "final", "static",
						"class", "enum", "interface", "var", "new", "void", "byte", "char", "short", "int", "float",
						"double", "long", "do", "while", "for", "try", "catch", "switch", "case", "default", "break",
						"continue", "return"))
				.add(Colorizer.wholeRegexes(Colorizer.enclosed(Style.Color.BLUE), "[A-Z]+[a-zA-Z0-9_]*"))
				.add("[a-zA-Z0-9_]+\\(", "[a-zA-Z0-9_]+", Colorizer.enclosed(Style.Color.GREEN))
				.add("[a-zA-Z_]+[0-9_]*\\)[.[^;]]+", "[a-zA-Z]+[0-9_]*\\)", "[a-zA-Z]+[0-9_]*",
						Colorizer.enclosed(Style.Color.CYAN), Pattern.DOTALL)
				.add("[a-zA-Z_]+[0-9_]*,[.[^;]]+", "[a-zA-Z]+[0-9_]*,", "[a-zA-Z]+[0-9_]",
						Colorizer.enclosed(Style.Color.CYAN), Pattern.DOTALL)
				.add(Colorizer.wholeRegexes(Colorizer.enclosed(Style.Color.YELLOW), "[a-zA-Z_]+[0-9_]*"));

		final var first = new ColorizerToken(contentIn);
		var current = first;

		int i;
		do
		{
			current = first;
			while (current.previous != null)
			{
				current = current.previous;
			}

			i = 0;
			do
			{
				for (final ColorizerPattern pattern : patterns.list)
				{
					if (!current.needColorization)
					{
						continue;
					}

					if (pattern.colorize(current))
					{
						i++;
					}
				}

				current = current.next;
			} while (current != null);
		} while (i > 0);

		current = first;
		final var content = new StringBuilder();
		do
		{
			content.append(current.content);

			current = current.next;
		} while (current != null);

		return content.toString();
	}

	public interface ITag
	{
		String detectionRegex();

		Pattern detectionPattern();

		default Matcher matcher(final String contentIn)
		{
			return this.detectionPattern().matcher(contentIn);
		}

		String baliseConversion();

		default String convert(final String contentBeforeConversionIn, final String contentIn)
		{
			if (!contentIn.contains(this.baliseConversion()))
			{
				return contentIn;
			}

			var content = contentIn;

			final var matcher = this.matcher(contentBeforeConversionIn);

			while (matcher.find())
			{
				content = content.replace(this.baliseConversion(), matcher.group());
			}

			return content;
		}
	}

	@Getter
	public final static class ColorizerToken
	{
		private ColorizerToken previous;
		private ColorizerToken next;

		private boolean needColorization;
		private String content;

		public ColorizerToken(final String contentIn)
		{
			this.content = contentIn;
			this.needColorization = true;
		}

		public ColorizerToken(final String contentIn, final boolean needColorizationIn)
		{
			this.content = contentIn;
			this.needColorization = needColorizationIn;
		}

		public ColorizerToken previous(final String contentIn, final boolean needColorizationIn)
		{
			final var _previous = this.previous;
			this.previous = new ColorizerToken(contentIn, needColorizationIn);
			this.previous.previous = _previous;
			this.previous.next = this;

			return this.previous;
		}

		public ColorizerToken next(final String contentIn, final boolean needColorizationIn)
		{
			final var _next = this.next;
			this.next = new ColorizerToken(contentIn, needColorizationIn);
			this.next.next = _next;
			this.next.previous = this;

			return this.next;
		}
	}

	@Getter
	public final static class Tag implements ITag
	{
		private final String detectionRegex;
		private final @Getter Pattern detectionPattern;
		private final @Getter String baliseConversion;

		public Tag(final String detectionRegexIn, final String baliseConversionIn)
		{
			this.detectionRegex = detectionRegexIn;
			this.detectionPattern = Pattern.compile(this.detectionRegex);
			this.baliseConversion = baliseConversionIn;
		}

		public Tag(final String baliseConversionIn, final String detectionRegexIn, final int detectionPatternFlagsIn)
		{
			this.detectionRegex = detectionRegexIn;
			this.detectionPattern = Pattern.compile(this.detectionRegex, detectionPatternFlagsIn);
			this.baliseConversion = baliseConversionIn;
		}
	}

	public final static class ColorizerPatterns
	{
		private final List<ColorizerPattern> list;

		public ColorizerPatterns(final ColorizerPattern... colorizerPatternsIn)
		{
			this.list = new ArrayList<>();
			Collections.addAll(this.list, colorizerPatternsIn);
		}

		public ColorizerPatterns add(final ColorizerPattern... colorizerPatternsIn)
		{
			Collections.addAll(this.list, colorizerPatternsIn);

			return this;
		}

		public ColorizerPatterns add(final String detectionRegexIn, final String conversionIn, final ITag... tagsIn)
		{
			Collections.addAll(this.list, ColorizerPattern.of(detectionRegexIn, conversionIn, tagsIn));

			return this;
		}

		public ColorizerPatterns add(final String detectionRegexIn, final String toModifyRegexIn,
				final String conversionIn, final ITag... tagsIn)
		{
			Collections.addAll(this.list,
					new ColorizerPattern.Builder(detectionRegexIn, conversionIn, tagsIn).elementsToModifyRegex(
							toModifyRegexIn).build());

			return this;
		}

		public ColorizerPatterns add(final String detectionRegexIn, final String toKeepRegexIn,
				final String toModifyRegexIn, final String conversionIn, final int patternFlagsIn, final ITag... tagsIn)
		{
			Collections.addAll(this.list,
					new ColorizerPattern.Builder(detectionRegexIn, conversionIn, tagsIn).elementsToKeepRegex(
									toKeepRegexIn).elementsToModifyRegex(toModifyRegexIn).detectionPatternFlags(patternFlagsIn)
							.build());

			return this;
		}

		public ColorizerPatterns add(final String detectionRegexIn, final String conversionIn, final List<ITag> tagsIn)
		{
			Collections.addAll(this.list, ColorizerPattern.of(detectionRegexIn, conversionIn, tagsIn));

			return this;
		}

		public ColorizerPatterns add(final String[] detectionRegexesIn, final String conversionIn, final ITag... tagsIn)
		{
			for (final String detectionRegex : detectionRegexesIn)
			{
				this.add(detectionRegex, conversionIn, tagsIn);
			}

			return this;
		}

		public ColorizerPatterns add(final List<String> detectionRegexesIn, final String conversionIn,
				final List<ITag> tagsIn)
		{
			for (final String detectionRegex : detectionRegexesIn)
			{
				this.add(detectionRegex, conversionIn, tagsIn);
			}

			return this;
		}

		public GroupBuilder group(final String conversionIn, final ITag... tagsIn)
		{
			return new GroupBuilder(this, conversionIn, tagsIn);
		}

		@Getter
		@Setter
		@AllArgsConstructor
		public final static class GroupBuilder
		{
			private final ColorizerPatterns parent;
			private String conversion;
			private List<ITag> tags;
			private List<String> detectionsRegexes;

			public GroupBuilder(final ColorizerPatterns parentIn, final String conversionIn, final ITag... tagsIn)
			{
				this.parent = parentIn;
				this.conversion = conversionIn;
				this.tags = new ArrayList<>();
				Collections.addAll(this.tags, tagsIn);
				this.detectionsRegexes = new ArrayList<>();
			}

			public GroupBuilder add(final ITag... tagsIn)
			{
				Collections.addAll(this.tags, tagsIn);

				return this;
			}

			public GroupBuilder add(final String... regexesIn)
			{
				Collections.addAll(this.detectionsRegexes, regexesIn);

				return this;
			}

			public ColorizerPatterns end()
			{
				this.parent.add(this.detectionsRegexes, this.conversion, this.tags);

				return this.parent;
			}
		}
	}

	@Getter
	public final static class ColorizerPattern
	{
		private final String detectionRegex;
		private final Pattern detectionPattern;
		private final String elementsToModifyRegex;
		private final Pattern elementsToModifyPattern;
		private final String elementsToKeepRegex;
		private final Pattern elementsToKeepPattern;
		private final String conversion;
		private final ITag[] tags;
		private final boolean canColorizePrevious;
		private final boolean canColorize;
		private final boolean canColorizeNext;

		public ColorizerPattern(ColorizerPattern.Builder builderIn)
		{
			this(builderIn.detectionRegex, builderIn.elementsToKeepRegex, builderIn.elementsToModifyRegex,
					builderIn.conversion, builderIn.canColorizePrevious, builderIn.canColorize,
					builderIn.canColorizeNext, builderIn.detectionPatternFlags, builderIn.tags);
		}

		public ColorizerPattern(final String detectionRegexIn, final String elementsToKeepRegex,
				final String elementsToModifyRegexIn, final String conversionIn, final boolean canColorizePreviousIn,
				final boolean canColorizeIn, final boolean canColorizeNextIn, final int detectionPatternFlagsIn,
				final Collection<ITag> tagsIn)
		{
			this.detectionRegex = detectionRegexIn;
			this.detectionPattern = Pattern.compile(this.detectionRegex, detectionPatternFlagsIn);
			this.elementsToKeepRegex = elementsToKeepRegex;
			this.elementsToKeepPattern = Pattern.compile(this.elementsToKeepRegex, detectionPatternFlagsIn);
			this.elementsToModifyRegex = elementsToModifyRegexIn;
			this.elementsToModifyPattern = Pattern.compile(this.elementsToModifyRegex);
			this.conversion = conversionIn;
			this.canColorizePrevious = canColorizePreviousIn;
			this.canColorize = canColorizeIn;
			this.canColorizeNext = canColorizeNextIn;

			this.tags = new ITag[tagsIn.size() + 1];
			this.tags[0] = Colorizer.CONTENT_BASE_TAG; // Content base tag added by default

			int i = 0;
			for (var tag : tagsIn)
			{
				this.tags[i] = tag;
				i++;
			}
		}

		public static ColorizerPattern of(final String detectionRegexIn, final String conversionIn,
				final ITag... tagsIn)
		{
			return new ColorizerPattern.Builder(detectionRegexIn, conversionIn, tagsIn).build();
		}

		public static ColorizerPattern of(final String detectionRegexIn, final String conversionIn,
				final Collection<ITag> tagsIn)
		{
			return new ColorizerPattern.Builder(detectionRegexIn, conversionIn, tagsIn).build();
		}

		public String convert(final String contentIn)
		{
			var content = this.conversion;

			for (final ITag tag : this.tags)
			{
				content = tag.convert(contentIn, content);
			}

			return content;
		}

		public ColorizerToken modify(final ColorizerToken colorizerTokenIn, final int startIn, final int endIn)
		{
			var start = startIn;
			var end = endIn;

			String newTokenContent = null;
			var i = 0;
			final var elementsToKeepMatcher = this.elementsToKeepPattern.matcher(
					colorizerTokenIn.content.substring(start, end));
			final var _content = colorizerTokenIn.content;
			if (elementsToKeepMatcher.find())
			{
				// System.out.println("TO MODIFY : " + elementsToModifyMatcher.group() + "[" + elementsToModifyMatcher.start() + "," + elementsToModifyMatcher.end() + "] (" + startIn + "," + endIn + "): " + newTokenContent.length());

				final var group = elementsToKeepMatcher.group();

				start = startIn + elementsToKeepMatcher.start();
				end = start + group.length();
				newTokenContent = _content.substring(start, end);

				System.out.println("GROUP : " + group);

				final var elementsToModifyMatcher = this.elementsToModifyPattern.matcher(newTokenContent);
				if (elementsToModifyMatcher.find())
				{
					newTokenContent = newTokenContent.replace(elementsToModifyMatcher.group(),
							this.convert(elementsToModifyMatcher.group()));

					i++;
				}
				// System.out.println("BEFORE : " + end);

				// System.out.println("AFTER : " + end);
				// System.out.println("TO MODIFY : " + elementsToModifyMatcher.group() + "[" + elementsToModifyMatcher.start() + "," + elementsToModifyMatcher.end() + "] (" + startIn + "," + endIn + "] : " + newTokenContent.length() + "
				// = "
				// + start + ", " + end);

			}

			if (i > 0)
			{
				// System.out.print("\n".repeat(2));

				colorizerTokenIn.content = _content.substring(0, start); // before
				colorizerTokenIn.needColorization = this.canColorizePrevious;

				final var next = colorizerTokenIn.next(newTokenContent,
						this.canColorize); /* between : colorized sentence */

				final var after = _content.substring(end);
				next.next(after, this.canColorizeNext); // after

				/*
				 * System.out.println(end + " vs " + endIn); System.out.println("\tFrom :\r\n\t[\r\n\t" + _content.replaceAll("\n", "\t".repeat(2) + "\n") + "\r\n\t]"); System.out.println("\tBefore :\r\n\t[\r\n\t" +
				 * colorizerTokenIn.content.replaceAll("\n", "\t".repeat(2) + "\n") + "\r\n\t]"); System.out.println("\tBetween :\r\n\t[\r\n\t" + next.content.replaceAll("\n", "\t".repeat(2) + "\n") + "\r\n\t]");
				 * System.out.println("\tAfter :\r\n\t[\r\n\t" + after.replaceAll("\n", "\t".repeat(2) + "\n") + "\r\n\t]");
				 *
				 * System.out.print("\n".repeat(2));
				 */
				return next;
			}

			return null;
		}

		public boolean colorize(final ColorizerToken colorizerTokenIn)
		{
			var detectionMatcher = this.detectionPattern.matcher(colorizerTokenIn.content);

			var i = 0;

			while (detectionMatcher.find())
			{
				final var added = this.modify(colorizerTokenIn, detectionMatcher.start(), detectionMatcher.end());
				if (added != null)
				{
					if (!added.needColorization)
					{
						return false;
					}

					detectionMatcher = this.detectionPattern.matcher(added.content);

					i++;
				}
			}

			return i > 0;
		}

		@Getter
		@Setter
		@AllArgsConstructor
		public final static class Builder
		{
			private final String detectionRegex;
			private final String conversion;
			private final List<ITag> tags;
			private String elementsToModifyRegex;
			private String elementsToKeepRegex;
			private boolean canColorizePrevious;
			private boolean canColorize;
			private boolean canColorizeNext;
			private int detectionPatternFlags;

			public Builder(String detectionRegexIn, String conversionIn)
			{
				this.detectionRegex = detectionRegexIn;
				this.conversion = conversionIn;
				this.tags = new ArrayList<>();

				this.canColorizePrevious = true;
				this.canColorize = false;
				this.canColorizeNext = true;
				this.detectionPatternFlags = 0;
			}

			public Builder(String detectionRegexIn, String conversionIn, final ITag... tagsIn)
			{
				this(detectionRegexIn, conversionIn);
				add(tagsIn);
			}

			public Builder(String detectionRegexIn, String conversionIn, final Collection<ITag> tagsIn)
			{
				this(detectionRegexIn, conversionIn);
				add(tagsIn);
			}

			public Builder add(ITag... tagsIn)
			{
				Collections.addAll(this.tags, tagsIn);

				return this;
			}

			public Builder add(Collection<ITag> tagsIn)
			{
				this.tags.addAll(tagsIn);

				return this;
			}

			public ColorizerPattern build(final ITag... tagsIn)
			{
				if (this.elementsToModifyRegex == null)
				{
					this.elementsToModifyRegex = this.detectionRegex;
				}

				if (this.elementsToKeepRegex == null)
				{
					this.elementsToKeepRegex = this.elementsToModifyRegex;
				}

				Collections.addAll(this.tags, tagsIn);

				return new ColorizerPattern(this);
			}
		}
	}
}