/**
 * This file is part of Onsiea Utils project. (https://github.com/OnsieaStudio/OnsieaUtils)<br>
 * <br>
 *
 * Onsiea Utils is [licensed] (https://github.com/OnsieaStudio/OnsieaUtils/blob/main/LICENSE) under the terms of the
 * "GNU GENERAL PUBLIC LICENSE v3 29 June 2007" (GPL-3).
 * https://github.com/OnsieaStudio/OnsieaUtils/wiki/License#license-and-copyright<br>
 * <br>
 *
 * Onsiea Utils is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 2.1 of the License, or any later
 * version.<br>
 * <br>
 *
 * Onsiea Utils is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU GENERAL PUBLIC LICENSE for more
 * details.<br>
 * <br>
 *
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
package fr.onsiea.utils.color.dev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class ColorClassCreator
{
	public static void main(final String[] argsIn)
	{
		// @formatter:off
		final String[]		types		= { "byte", "int", "float" };
		final Components[]	allComponents	= { Components.of("r", "g"), Components.of("r", "g", "b"), Components.of("r", "g", "b", "a") };
		final Operation[]	operations	= {
			Operation.of("Addition", "Add", "add", "adder", "+=", "to", false),
			Operation.of("Substraction", "Substract", "sub", "substractor", "-=", "to",false),
			Operation.of("Multiplication", "Multiply", "mul", "multiplicator", "*=", "by", true),
			Operation.of("Division", "Divide", "div", "divisor", "/=", "by", true),
			Operation.of("Modulation", "Module", "mod", "modulor", "%=", "by", true),
			Operation.of("Setters", "Set", "set", "setter", "=", "to", true)
		};
		// @formatter:on

		final var classCommentBlock = """
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
				""";
		final var classCommentDescription = """
				/**
				* <class_name>
				* @author : Seynax (https://github.com/seynax)<br>
				* @organization : Onsiea Studio (https://github.com/OnsieaStudio)<br>
				*<br>
				* <channels_number> <type> Color utils class with <components>
				*/""";
		final var operationMethodPattern = """
				\t/**
				\t * <verb> <comment_description>
				\t *
				\t * @author Seynax
				\t * <parameters_comment_line>
				\t * @return this <class> instance
				\t */
				\tpublic final <class> <method_name>(<parameters>)
				\t{
				<operations>

				\t	return <return>;
				\t}
				""";

		for (final var type : types)
		{
			final var baseClassContent = classCommentBlock + "package fr.onsiea.utils.color;\r\n\r\n";
			final var currentClassComment = classCommentDescription.replace("<type>", type);

			for (final var components : allComponents)
			{
				final var className = "Color" + components.length() + type.charAt(0);
				StringBuilder classContent = new StringBuilder(baseClassContent);
				for (final var toType : types)
				{
					for (final var toComponents : allComponents)
					{
						final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

						classContent.append("import fr.onsiea.utils.color.prototype.I").append(toClassName)
								.append(";\r\n");
					}
				}
				classContent.append("\r\n").append(currentClassComment.replace("<class_name>", className)
								.replace("<channels_number>", "" + components.components.length)
								.replace("<components>", components.toString())).append("\r\npublic final class ")
						.append(className).append(" implements I").append(className).append("\r\n{\r\n");

				// Variables
				for (final var component : components.get())
				{
					classContent.append("\t\t").append(type).append(" ").append(component).append(";\r\n");
				}

				// Constructors
				classContent.append("\r\n\t//\tConstructors\r\n\r\n");

				classContent.append("\tpublic ").append(className).append("()\r\n\t{\r\n\r\n\t}\r\n\r\n");

				for (final var toComponents : allComponents)
				{
					for (final var toType : types)
					{
						final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

						classContent.append("\tpublic ").append(className).append("(final I").append(toClassName)
								.append(" colorIn)\r\n\t{\r\n");

						classContent.append(
								ColorClassCreator.operation("\t\t", components, "=", toComponents, null, type, toType));

						classContent.append("\r\n\t}\r\n\r\n");

						if (toComponents.length() > components.length())
						{
							continue;
						}

						classContent.append("\tpublic ").append(className).append("(")
								.append(toComponents.parameters("final ", "In").replace("<type>", toType))
								.append(")\r\n\t{\r\n");

						classContent.append(
								ColorClassCreator.operationParameters("\t\t", components, "=", toComponents, null, type,
										toType));

						classContent.append("\r\n\t}\r\n\r\n");
					}
				}

				for (final var operation : operations)
				{
					classContent.append("\r\n\t/**\r\n\t *\t").append(operation.name).append("\r\n\t */\r\n\r\n");
					for (final var parametersComponents : allComponents)
					{
						for (final var parametersType : types)
						{
							classContent.append(ColorClassCreator.operationMethodWithColorClassInstance(className, type,
									parametersType, operation, parametersComponents, components,
									"Color" + parametersComponents.length() + parametersType.charAt(0),
									components.length())).append("\r\n");

							if (components.length() < parametersComponents.length())
							{
								continue;
							}

							classContent.append(
									ColorClassCreator.operationMethod(className, type, parametersType, operation,
											parametersComponents, components, components.length())).append("\r\n");
						}
					}
				}

				// Converters and copy
				convertersAndCopyMethods(classContent, className, components, type, types, allComponents);

				// Getter and setters
				classContent.append("\r\n\t//\tSetters | Getters\r\n\r\n");
				for (final var component : components.get())
				{
					classContent.append("\tpublic final ").append(className).append(" ").append(component)
							.append("(final ").append(type).append(" ").append(component).append("In)\r\n\t{\r\n\t\t")
							.append(component).append(" = ").append(component)
							.append("In;\r\n\r\n\t\treturn this;\r\n\t}\r\n\r\n");
					classContent.append("\tpublic final ").append(type).append(" ").append(component)
							.append("()\r\n\t{\r\n\t\treturn ").append(component).append(";\r\n\t}\r\n\r\n");
				}
				classContent = new StringBuilder(classContent.toString().replaceAll("\\s+$", "") + "\r\n");

				// Constant class
				{
					classContent.append("\r\n\tpublic final static class Constant implements I").append(className)
							.append("\r\n\t{\r\n");

					// Variables
					for (final var component : components.get())
					{
						classContent.append("\t\tprivate final ").append(type).append(" ").append(component)
								.append(";\r\n");
					}
					classContent.append("\r\n");

					// Constructors
					classContent.append("\r\n\t//\tConstructors\r\n\r\n");
					classContent.append("\tpublic Constant()\r\n\t{");
					for (var i = 0; i < components.length(); i++)
					{
						classContent.append("\r\n\t\t\tthis.").append(components.get()[i]).append(" = 0;");
					}
					classContent.append("\r\n\t}\r\n\r\n");
					for (final var toComponents : allComponents)
					{
						for (final var toType : types)
						{
							final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

							classContent.append("\t\tpublic Constant(final I").append(toClassName)
									.append(" colorIn)\r\n\t\t{\r\n");

							classContent.append(
									ColorClassCreator.operation("\t\t\t", components, "=", toComponents, "", type,
											toType));

							for (var i = toComponents.length(); i < components.length(); i++)
							{
								classContent.append("\r\n\t\t\tthis.").append(components.get()[i]).append(" = 0;");
							}

							classContent.append("\r\n\t\t}\r\n\r\n");

							if (toComponents.length() > components.length())
							{
								continue;
							}

							classContent.append("\t\tpublic Constant(")
									.append(toComponents.parameters("final ", "In").replace("<type>", toType))
									.append(")\r\n\t{\r\n");

							classContent.append(
									ColorClassCreator.operationParameters("\t\t\t", components, "=", toComponents, null,
											type, toType));

							for (var i = toComponents.length(); i < components.length(); i++)
							{
								classContent.append("\r\n\t\t\tthis.").append(components.get()[i]).append(" = 0;");
							}

							classContent.append("\r\n\t\t}\r\n\r\n");
						}
					}

					// Converters and copy
					convertersAndCopyMethods(classContent, className, components, type, types, allComponents);

					// Getter
					classContent.append("\r\n\t//\tGetters\r\n\r\n");
					for (final var component : components.get())
					{
						classContent.append("\t\tpublic final ").append(type).append(" ").append(component)
								.append("()\r\n\t\t{\r\n\t\t\treturn ").append(component).append(";\r\n\t\t}\r\n\r\n");
					}

					classContent = new StringBuilder(classContent.toString().replaceAll("\\s+$", "") + "\r\n");
					classContent.append("\t}\r\n");

					classContent.append("}");

					try
					{
						final var file = new File(
								"P:\\java\\eclipse\\Aeison\\OnsieaUtils\\src\\fr\\onsiea\\utils\\color\\" + className
										+ ".java");
						if (!file.getParentFile().exists())
						{
							if (!file.getParentFile().mkdirs())
							{
								return;
							}
						}
						if (!file.exists())
						{
							if (!file.createNewFile())
							{
								return;
							}
						}
						else if (file.isDirectory())
						{
							System.err.println("IS DIRECTORY !");
							System.exit(-1);
						}
						final var bufferedWriter = new BufferedWriter(new FileWriter(file));
						bufferedWriter.write(classContent.toString());
						bufferedWriter.close();
					}
					catch (final IOException exceptionIn)
					{
						exceptionIn.printStackTrace();
					}
				}
			}
		}
	}

	private static void convertersAndCopyMethods(StringBuilder classContentIn, final String classNameIn,
			final Components componentsIn, final String typeIn, String[] typesIn, Components[] allComponentsIn)
	{
		classContentIn.append("\r\n\t//\tConverters and copy\r\n\r\n\tpublic final ").append(classNameIn)
				.append(" copy()\r\n\t{\r\n\t\treturn new ").append(classNameIn)
				.append("(this);\r\n\t}\r\n\r\n\tpublic final ").append(classNameIn).append(" copy(final ")
				.append(classNameIn).append(" colorIn)\r\n\t{\r\n")
				.append(ColorClassCreator.invOperation(componentsIn, componentsIn, typeIn, typeIn))
				.append("\r\n\r\n\t\treturn colorIn;\r\n\t}\r\n\r\n\tpublic final ").append(classNameIn)
				.append(".Constant constantCopy()\r\n\t{\r\n\t\treturn new ").append(classNameIn)
				.append(".Constant(this);\r\n\t}\r\n\r\n");

		for (final var toType : typesIn)
		{
			for (final var toComponents : allComponentsIn)
			{
				final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

				if (toClassName.contentEquals(classNameIn))
				{
					continue;
				}
				var baseClassName = toClassName.substring(toClassName.length() - 2);

				classContentIn.append("\tpublic final ").append(toClassName).append(" copy(final ").append(toClassName)
						.append(" colorIn)\r\n\t{\r\n");
				classContentIn.append(ColorClassCreator.invOperation(componentsIn, toComponents, typeIn, toType))
						.append("\r\n\r\n");
				classContentIn.append("\t\treturn colorIn;\r\n\t}\r\n\r\n");
				classContentIn.append("\tpublic final ").append(toClassName).append(" copy").append(baseClassName)
						.append("()\r\n\t{\r\n\t\treturn new ").append(toClassName).append("(this);\r\n\t}\r\n\r\n");
				classContentIn.append("\tpublic final ").append(toClassName).append(".Constant constantCopy")
						.append(baseClassName).append("()\r\n\t{\r\n\t\treturn new ").append(toClassName)
						.append(".Constant(this);\r\n\t}\r\n\r\n");
			}
		}
	}

	/**
	 * private final static String operationParameters(final String startIn, final Components componentsIn, final String
	 * operatorIn, final Components toComponentsIn, final String fromTypeIn, final String toTypeIn, final String
	 * parameterSuffixIn) { return ColorClassCreator.operationParameters(startIn, componentsIn, operatorIn,
	 * toComponentsIn, parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private static String operationParameters(final String startIn, final Components componentsIn,
			final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn,
			final String fromTypeIn, final String toTypeIn)
	{
		final var output = new StringBuilder();
		var i = 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to;
					if (parameterSuffixIn != null && !parameterSuffixIn.matches("\\s+") && !parameterSuffixIn.isEmpty())
					{
						to = toComponent + parameterSuffixIn.toUpperCase().charAt(0) + parameterSuffixIn.substring(1);
					}
					else
					{
						to = toComponent;
					}

					if (operatorIn.contentEquals("=") && !fromTypeIn.contentEquals(toTypeIn))
					{
						output.append(startIn).append("this.").append(component).append(" ").append(operatorIn)
								.append(" (").append(fromTypeIn).append(") ").append(to).append("In;");
					}
					else
					{
						output.append(startIn).append("this.").append(component).append(" ").append(operatorIn)
								.append(" ").append(to).append("In;");
					}

					break;
				}
			}

			i++;
			if (i >= componentsIn.length() || i >= toComponentsIn.length())
			{
				break;
			}
			output.append("\r\n");
		}

		return output.toString();
	}

	/**
	 * private final static String invOperation(final String startIn, final Components componentsIn, final String
	 * operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final
	 * String toTypeIn) { return ColorClassCreator.invOperation(startIn, componentsIn, operatorIn, toComponentsIn,
	 * parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private static String invOperation(final Components componentsIn, final Components toComponentsIn,
			final String fromTypeIn, final String toTypeIn)
	{
		final var output = new StringBuilder();
		var i = 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to = "colorIn";
					if (!fromTypeIn.contentEquals(toTypeIn))
					{
						output.append("\t\t").append("colorIn.").append(component).append(" ").append("=").append(" (")
								.append(toTypeIn).append(") ").append(to).append(".").append(toComponent).append("()")
								.append(";");
					}
					else
					{
						output.append("\t\t").append("colorIn.").append(component).append(" ").append("=")
								.append(" this.").append(toComponent).append(";");
					}

					break;
				}
			}

			i++;
			if (i >= componentsIn.length() || i >= toComponentsIn.length())
			{
				break;
			}
			output.append("\r\n");
		}

		return output.toString();
	}

	/**
	 * private final static String operation(final String startIn, final Components componentsIn, final String
	 * operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final
	 * String toTypeIn) { return ColorClassCreator.operation(startIn, componentsIn, operatorIn, toComponentsIn,
	 * parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private static String operation(final String startIn, final Components componentsIn, final String operatorIn,
			final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn,
			final String toTypeIn)
	{
		final var output = new StringBuilder();
		var i = 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to;
					if (parameterSuffixIn != null && !parameterSuffixIn.matches("\\s+") && !parameterSuffixIn.isEmpty())
					{
						to = "color" + parameterSuffixIn.toUpperCase().charAt(0) + parameterSuffixIn.substring(1)
								+ "In";
					}
					else
					{
						to = "colorIn";
					}

					if (operatorIn.contentEquals("=") && !fromTypeIn.contentEquals(toTypeIn))
					{
						output.append(startIn).append("this.").append(component).append(" ").append(operatorIn)
								.append(" (").append(fromTypeIn).append(") ").append(to).append(".").append(toComponent)
								.append("()").append(";");
					}
					else
					{
						output.append(startIn).append("this.").append(component).append(" ").append(operatorIn)
								.append(" ").append(to).append(".").append(toComponent).append("()").append(";");
					}

					break;
				}
			}

			i++;
			if (i >= componentsIn.length() || i >= toComponentsIn.length())
			{
				break;
			}
			output.append("\r\n");
		}

		return output.toString();
	}

	private static String operationMethodWithColorClassInstance(final String classNameIn, final String typeIn,
			final String parameterTypeIn, final Operation operationIn, final Components componentsIn,
			final Components toComponentsIn, final String parameterClassInstanceNameIn,
			final int parameterClassInstanceComponentsNumberIn)
	{
		final var commentDescription = new StringBuilder();
		final var parametersCommentLine = new StringBuilder();
		final var methodOperations = ColorClassCreator.operation("\t" + "\t", componentsIn, operationIn.operator,
				toComponentsIn, operationIn.descriptor, typeIn, parameterTypeIn);

		final var baseParameter =
				"color" + operationIn.descriptor.toUpperCase().charAt(0) + operationIn.descriptor.substring(1) + "In";
		parametersCommentLine.append("@param ").append(baseParameter);
		var i = 0;
		StringBuilder components = new StringBuilder();
		for (final var component : componentsIn.get())
		{
			components.append(component);
			i++;
			if (i >= parameterClassInstanceComponentsNumberIn || i >= componentsIn.length())
			{
				break;
			}
		}

		if (operationIn.commentDescriptorInverted)
		{
			commentDescription.append(components).append(" ").append(operationIn.contractor).append(" ")
					.append(baseParameter).append(".").append(components);
		}
		else
		{
			commentDescription.append(baseParameter).append(".").append(components).append(" ")
					.append(operationIn.contractor).append(" ").append(components);
		}

		// @formatter:off
		return "\t/**\n\t * <verb> <comment_description>\n\t *\n\t * @author Seynax\n\t * <parameters_comment_line>\n\t * @return this <class> instance\n\t */\n\tpublic final <class> <method_name>(<parameters>)\n\t{\n<operations>\n\n\t\treturn <return>;\n\t}\n".replace("<parameters>", "final I" + parameterClassInstanceNameIn + " " + baseParameter)
				.replace("<verb>", operationIn.verb + " (" + typeIn +" version) : ")
				.replace("<comment_description>", commentDescription.toString())
				.replace("<parameters_comment_line>", parametersCommentLine.toString())
				.replace("<class>", classNameIn)
				.replace("<operations>", methodOperations)
				.replace("<method_name>", operationIn.methodName)
				.replace("<return>", "this");
		// @formatter:on
	}

	private static String operationMethod(final String classNameIn, final String typeIn, final String parameterTypeIn,
			final Operation operationIn, final Components componentsIn, final Components toComponentsIn,
			final int componentsNumberIn)
	{
		final var commentDescription = new StringBuilder();
		final var parametersCommentLine = new StringBuilder();
		final var methodOperations = ColorClassCreator.operationParameters("\t" + "\t", componentsIn,
				operationIn.operator, toComponentsIn, operationIn.descriptor, typeIn, parameterTypeIn);

		final var descriptor = operationIn.descriptor.toUpperCase().charAt(0) + operationIn.descriptor.substring(1);
		var i = 0;
		StringBuilder components = new StringBuilder();
		for (final var component : componentsIn.get())
		{
			components.append(component);
			final var parameter = component + descriptor + "In";
			parametersCommentLine.append("@param ").append(parameter);
			i++;
			if (i >= componentsNumberIn || i >= componentsIn.length())
			{
				break;
			}
			parametersCommentLine.append("\r\n" + "\t" + " * ");
		}

		if (operationIn.commentDescriptorInverted)
		{
			commentDescription.append(components).append(" ").append(operationIn.contractor).append(" ")
					.append(components).append(operationIn.descriptor).append("In");
		}
		else
		{
			commentDescription.append(components).append(operationIn.descriptor).append("In ")
					.append(operationIn.contractor).append(" ").append(components);
		}

		// @formatter:off
		return "\t/**\n\t * <verb> <comment_description>\n\t *\n\t * @author Seynax\n\t * <parameters_comment_line>\n\t * @return this <class> instance\n\t */\n\tpublic final <class> <method_name>(<parameters>)\n\t{\n<operations>\n\n\t\treturn <return>;\n\t}\n".replace("<parameters>", componentsIn.parameters("final ", descriptor + "In").replace("<type>", parameterTypeIn))
				.replace("<verb>", operationIn.verb + " (" + typeIn +" version) : ")
				.replace("<comment_description>", commentDescription.toString())
				.replace("<parameters_comment_line>", parametersCommentLine.toString())
				.replace("<class>", classNameIn)
				.replace("<operations>", methodOperations)
				.replace("<method_name>", operationIn.methodName)
				.replace("<return>", "this");
		// @formatter:on
	}

	private final static class Components
	{
		final String[] components;
		final String list;

		public Components(final String... componentsIn)
		{
			this.components = componentsIn;

			var i = 0;
			StringBuilder listBuilder = new StringBuilder();
			for (final var component : this.components)
			{
				// this.parameters += "<type> " + component + "In";
				listBuilder.append(component);
				i++;
				if (i < this.components.length)
				{
					// this.parameters += ", ";
					listBuilder.append(", ");
				}
			}

			this.list = listBuilder.toString();
		}

		public static Components of(final String... componentsIn)
		{
			return new Components(componentsIn);
		}

		public String[] get()
		{
			return this.components;
		}

		public int length()
		{
			return this.components.length;
		}

		/**
		 * @return string parameters
		 * @author Seynax
		 */
		public String parameters(final String prefixIn, final String suffixIn)
		{
			final var outputParameters = new StringBuilder();
			var i = 0;
			for (final var component : this.components)
			{
				outputParameters.append(prefixIn).append("<type> ").append(component).append(suffixIn);
				i++;
				if (i < this.components.length)
				{
					outputParameters.append(", ");
				}
			}
			return outputParameters.toString();
		}

		/**
		 * public String parameters() { return this.parameters; }
		 **/

		@Override
		public String toString()
		{
			return this.list;
		}
	}

	private final static class Operation
	{

		final String name;
		final String verb;
		final String methodName;
		final String descriptor;
		final String operator;
		final String contractor;
		final boolean commentDescriptorInverted;

		/**
		 *
		 */
		public Operation(final String nameIn, final String verbIn, final String methodNameIn, final String descriptorIn,
				final String operatorIn, final String contractorIn, final boolean commentDescriptorInvertedIn)
		{
			this.name = nameIn;
			this.verb = verbIn;
			this.methodName = methodNameIn;
			this.descriptor = descriptorIn;
			this.operator = operatorIn;
			this.contractor = contractorIn;
			this.commentDescriptorInverted = commentDescriptorInvertedIn;
		}

		/**
		 * @return new Operation instance
		 * @author Seynax
		 */
		public static Operation of(final String nameIn, final String verbIn, final String methodNameIn,
				final String descriptorIn, final String operatorIn, final String contractorIn,
				final boolean commentDescriptorInvertedIn)
		{
			return new Operation(nameIn, verbIn, methodNameIn, descriptorIn, operatorIn, contractorIn,
					commentDescriptorInvertedIn);
		}
	}
}