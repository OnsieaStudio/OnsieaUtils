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
	public final static void main(final String[] argsIn)
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

		final var	classCommentBlock		= """
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
		final var	classCommentDescription	= """
				/**
				* <class_name>
				* @author : Seynax (https://github.com/seynax)<br>
				* @organization : Onsiea Studio (https://github.com/OnsieaStudio)<br>
				*<br>
				* <channels_number> <type> Color utils class with <components>
				*/""";
		final var	operationMethodPattern	= """
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
			final var	baseClassContent	= classCommentBlock + "package fr.onsiea.utils.color;\r\n\r\n";
			final var	currentClassComment	= classCommentDescription.replace("<type>", type);

			for (final var components : allComponents)
			{
				final var	className		= "Color" + components.length() + type.charAt(0);
				var			classContent	= baseClassContent;
				for (final var toType : types)
				{
					for (final var toComponents : allComponents)
					{
						final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

						classContent += "import fr.onsiea.utils.color.prototype.I" + toClassName + ";\r\n";
					}
				}
				/**
				 * for (final var toType : types) { for (final var toComponents : allComponents) { final var toClassName = "Color" + toComponents.length() + toType.charAt(0);
				 *
				 * if (toClassName.contentEquals(className)) { continue; }
				 *
				 * classContent += "import fr.onsiea.utils.color." + toClassName + ";\r\n"; } }
				 **/
				classContent += "\r\n" + currentClassComment.replace("<class_name>", className).replace("<channels_number>", "" + components.components.length).replace("<components>", components.toString()) + "\r\npublic final class "
						+ className + " implements I" + className + "\r\n{\r\n";

				// Variables
				for (final var component : components.get())
				{
					classContent += "\t\t" + type + " " + component + ";\r\n";
				}

				// Constructors
				classContent += "\r\n\t//\tConstructors\r\n\r\n";

				classContent += "\tpublic " + className + "()\r\n\t{\r\n\r\n\t}\r\n\r\n";

				for (final var toComponents : allComponents)
				{
					for (final var toType : types)
					{
						final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

						classContent += "\tpublic " + className + "(final I" + toClassName + " colorIn)\r\n\t{\r\n";

						classContent += ColorClassCreator.operation("\t\t", components, "=", toComponents, null, type, toType, true);

						classContent += "\r\n\t}\r\n\r\n";

						if (toComponents.length() > components.length())
						{
							continue;
						}

						classContent += "\tpublic " + className + "(" + toComponents.parameters("final ", "In").replace("<type>", toType) + ")\r\n\t{\r\n";

						classContent += ColorClassCreator.operationParameters("\t\t", components, "=", toComponents, null, type, toType, true);

						classContent += "\r\n\t}\r\n\r\n";
					}
				}

				for (final var operation : operations)
				{
					classContent += "\r\n\t/**\r\n\t *\t" + operation.name + "\r\n\t */\r\n\r\n";
					for (final var parametersComponents : allComponents)
					{
						for (final var parametersType : types)
						{
							classContent += ColorClassCreator.operationMethodWithColorClassInstance("\t", className, operationMethodPattern, type, parametersType, operation, parametersComponents, components,
									"Color" + parametersComponents.length() + parametersType.charAt(0), components.length()) + "\r\n";

							if (components.length() < parametersComponents.length())
							{
								continue;
							}

							classContent += ColorClassCreator.operationMethod("\t", className, operationMethodPattern, type, parametersType, operation, parametersComponents, components, components.length()) + "\r\n";
						}
					}
				}

				// Converters and copy
				classContent += "\r\n\t//\tConverters and copy\r\n\r\n";

				classContent	+= "\tpublic final " + className + " copy()\r\n\t{\r\n\t\treturn new " + className + "(this);\r\n\t}\r\n\r\n";
				classContent	+= "\tpublic final " + className + " copy(final " + className + " colorIn)\r\n\t{\r\n";
				classContent	+= ColorClassCreator.invOperation("\t\t", components, "=", components, null, type, type, true) + "\r\n\r\n";
				classContent	+= "\t\treturn colorIn;\r\n\t}\r\n\r\n";
				classContent	+= "\tpublic final " + className + ".Constant constantCopy()\r\n\t{\r\n\t\treturn new " + className + ".Constant(this);\r\n\t}\r\n\r\n";

				for (final var toType : types)
				{
					for (final var toComponents : allComponents)
					{
						final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

						if (toClassName.contentEquals(className))
						{
							continue;
						}

						classContent	+= "\tpublic final " + toClassName + " copy(final " + toClassName + " colorIn)\r\n\t{\r\n";
						classContent	+= ColorClassCreator.invOperation("\t\t", components, "=", toComponents, null, type, toType, true) + "\r\n\r\n";
						classContent	+= "\t\treturn colorIn;\r\n\t}\r\n\r\n";
						classContent	+= "\tpublic final " + toClassName + " copy" + toClassName.substring(toClassName.length() - 2) + "()\r\n\t{\r\n\t\treturn new " + toClassName + "(this);\r\n\t}\r\n\r\n";
						classContent	+= "\tpublic final " + toClassName + ".Constant constantCopy" + toClassName.substring(toClassName.length() - 2) + "()\r\n\t{\r\n\t\treturn new " + toClassName + ".Constant(this);\r\n\t}\r\n\r\n";
					}
				}

				// Getter and setters
				classContent += "\r\n\t//\tSetters | Getters\r\n\r\n";
				for (final var component : components.get())
				{
					classContent	+= "\tpublic final " + className + " " + component + "(final " + type + " " + component + "In)\r\n\t{\r\n\t\t" + component + " = " + component + "In;\r\n\r\n\t\treturn this;\r\n\t}\r\n\r\n";
					classContent	+= "\tpublic final " + type + " " + component + "()\r\n\t{\r\n\t\treturn " + component + ";\r\n\t}\r\n\r\n";
				}
				classContent = classContent.replaceAll("\\s+$", "") + "\r\n";

				// Constant class
				{
					classContent += "\r\n\tpublic final static class Constant implements I" + className + "\r\n\t{\r\n";

					// Variables
					for (final var component : components.get())
					{
						classContent += "\t\tprivate final " + type + " " + component + ";\r\n";
					}
					classContent += "\r\n";

					// Constructors
					classContent	+= "\r\n\t//\tConstructors\r\n\r\n";
					classContent	+= "\tpublic Constant()\r\n\t{";
					for (var i = 0; i < components.length(); i++)
					{
						classContent += "\r\n\t\t\tthis." + components.get()[i] + " = 0;";
					}
					classContent += "\r\n\t}\r\n\r\n";
					for (final var toComponents : allComponents)
					{
						for (final var toType : types)
						{
							final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

							classContent += "\t\tpublic Constant(final I" + toClassName + " colorIn)\r\n\t\t{\r\n";

							classContent += ColorClassCreator.operation("\t\t\t", components, "=", toComponents, "", type, toType, true);

							for (var i = toComponents.length(); i < components.length(); i++)
							{
								classContent += "\r\n\t\t\tthis." + components.get()[i] + " = 0;";
							}

							classContent += "\r\n\t\t}\r\n\r\n";

							if (toComponents.length() > components.length())
							{
								continue;
							}

							classContent += "\t\tpublic Constant(" + toComponents.parameters("final ", "In").replace("<type>", toType) + ")\r\n\t{\r\n";

							classContent += ColorClassCreator.operationParameters("\t\t\t", components, "=", toComponents, null, type, toType, true);

							for (var i = toComponents.length(); i < components.length(); i++)
							{
								classContent += "\r\n\t\t\tthis." + components.get()[i] + " = 0;";
							}

							classContent += "\r\n\t\t}\r\n\r\n";
						}
					}

					// Converters and copy
					classContent += "\r\n\t//\tConverters and copy\r\n\r\n";

					classContent	+= "\tpublic final " + className + " copy()\r\n\t{\r\n\t\treturn new " + className + "(this);\r\n\t}\r\n\r\n";
					classContent	+= "\tpublic final " + className + " copy(final " + className + " colorIn)\r\n\t{\r\n";
					classContent	+= ColorClassCreator.invOperation("\t\t", components, "=", components, null, type, type, true) + "\r\n\r\n";
					classContent	+= "\t\treturn colorIn;\r\n\t}\r\n\r\n";
					classContent	+= "\tpublic final " + className + ".Constant constantCopy()\r\n\t{\r\n\t\treturn new " + className + ".Constant(this);\r\n\t}\r\n\r\n";

					for (final var toType : types)
					{
						for (final var toComponents : allComponents)
						{
							final var toClassName = "Color" + toComponents.length() + toType.charAt(0);

							if (toClassName.contentEquals(className))
							{
								continue;
							}

							classContent	+= "\tpublic final " + toClassName + " copy(final " + toClassName + " colorIn)\r\n\t{\r\n";
							classContent	+= ColorClassCreator.invOperation("\t\t", components, "=", toComponents, null, type, toType, true) + "\r\n\r\n";
							classContent	+= "\t\treturn colorIn;\r\n\t}\r\n\r\n";
							classContent	+= "\tpublic final " + toClassName + " copy" + toClassName.substring(toClassName.length() - 2) + "()\r\n\t{\r\n\t\treturn new " + toClassName + "(this);\r\n\t}\r\n\r\n";
							classContent	+= "\tpublic final " + toClassName + ".Constant constantCopy" + toClassName.substring(toClassName.length() - 2) + "()\r\n\t{\r\n\t\treturn new " + toClassName + ".Constant(this);\r\n\t}\r\n\r\n";
						}
					}

					// Getter
					classContent += "\r\n\t//\tGetters\r\n\r\n";
					for (final var component : components.get())
					{
						classContent += "\t\tpublic final " + type + " " + component + "()\r\n\t\t{\r\n\t\t\treturn " + component + ";\r\n\t\t}\r\n\r\n";
					}

					classContent	= classContent.replaceAll("\\s+$", "") + "\r\n";
					classContent	+= "\t}\r\n";

					classContent += "}";

					try
					{
						final var file = new File("P:\\java\\eclipse\\Aeison\\OnsieaUtils\\src\\fr\\onsiea\\utils\\color\\" + className + ".java");
						if (!file.getParentFile().exists())
						{
							file.getParentFile().mkdirs();
						}
						if (!file.exists())
						{
							file.createNewFile();
						}
						if (file.isDirectory())
						{
							System.err.println("IS DIRECTORY !");
							System.exit(-1);
						}
						final var bufferedWriter = new BufferedWriter(new FileWriter(file));
						bufferedWriter.write(classContent);
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

	/**
	 * private final static String operationParameters(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String fromTypeIn, final String toTypeIn, final
	 * String parameterSuffixIn) { return ColorClassCreator.operationParameters(startIn, componentsIn, operatorIn, toComponentsIn, parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private final static String operationParameters(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn,
			final String toTypeIn, final boolean typeFixerIn)
	{
		final var	output	= new StringBuilder();
		var			i		= 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to = null;
					if (parameterSuffixIn != null && !parameterSuffixIn.matches("\\s+") && parameterSuffixIn.length() > 0)
					{
						to = toComponent + parameterSuffixIn.toUpperCase().charAt(0) + parameterSuffixIn.substring(1);
					}
					else
					{
						to = toComponent;
					}

					if (operatorIn.contentEquals("=") && !fromTypeIn.contentEquals(toTypeIn) && typeFixerIn)
					{
						output.append(startIn).append("this.").append(component).append(" " + operatorIn + " (" + fromTypeIn + ") ").append(to + "In;");
					}
					else
					{
						output.append(startIn).append("this.").append(component).append(" " + operatorIn + " ").append(to + "In;");
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
	 * private final static String invOperation(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final
	 * String toTypeIn) { return ColorClassCreator.invOperation(startIn, componentsIn, operatorIn, toComponentsIn, parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private final static String invOperation(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final String toTypeIn,
			final boolean typeFixerIn)
	{
		final var	output	= new StringBuilder();
		var			i		= 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to = null;
					if (parameterSuffixIn != null && !parameterSuffixIn.matches("\\s+") && parameterSuffixIn.length() > 0)
					{
						to = "color" + parameterSuffixIn.toUpperCase().charAt(0) + parameterSuffixIn.substring(1);
					}
					else
					{
						to = "colorIn";
					}
					if (operatorIn.contentEquals("=") && !fromTypeIn.contentEquals(toTypeIn) && typeFixerIn)
					{
						output.append(startIn).append("colorIn.").append(component).append(" " + operatorIn + " (" + toTypeIn + ") ").append(to + ".").append(toComponent + "()").append(";");
					}
					else
					{
						output.append(startIn).append("colorIn.").append(component).append(" " + operatorIn + " this.").append(toComponent).append(";");
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
	 * private final static String operation(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final
	 * String toTypeIn) { return ColorClassCreator.operation(startIn, componentsIn, operatorIn, toComponentsIn, parameterSuffixIn, fromTypeIn, toTypeIn, false); }
	 **/

	private final static String operation(final String startIn, final Components componentsIn, final String operatorIn, final Components toComponentsIn, final String parameterSuffixIn, final String fromTypeIn, final String toTypeIn,
			final boolean typeFixerIn)
	{
		final var	output	= new StringBuilder();
		var			i		= 0;
		for (final var component : componentsIn.get())
		{
			for (final var toComponent : toComponentsIn.get())
			{
				if (component.contentEquals(toComponent))
				{
					String to = null;
					if (parameterSuffixIn != null && !parameterSuffixIn.matches("\\s+") && parameterSuffixIn.length() > 0)
					{
						to = "color" + parameterSuffixIn.toUpperCase().charAt(0) + parameterSuffixIn.substring(1) + "In";
					}
					else
					{
						to = "colorIn";
					}

					if (operatorIn.contentEquals("=") && !fromTypeIn.contentEquals(toTypeIn) && typeFixerIn)
					{
						output.append(startIn).append("this.").append(component).append(" " + operatorIn + " (" + fromTypeIn + ") ").append(to + ".").append(toComponent + "()").append(";");
					}
					else
					{
						output.append(startIn).append("this.").append(component).append(" " + operatorIn + " " + to + ".").append(toComponent + "()").append(";");
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

	private final static String operationMethodWithColorClassInstance(final String startIn, final String classNameIn, final String operationMethodPatternIn, final String typeIn, final String parameterTypeIn, final Operation operationIn,
			final Components componentsIn, final Components toComponentsIn, final String parameterClassInstanceNameIn, final int parameterClassInstanceComponentsNumberIn)
	{
		final var	commentDescription		= new StringBuilder();
		final var	parametersCommentLine	= new StringBuilder();
		final var	methodOperations		= ColorClassCreator.operation(startIn + "\t", componentsIn, operationIn.operator, toComponentsIn, operationIn.descriptor, typeIn, parameterTypeIn, true);

		final var baseParameter = "color" + operationIn.descriptor.toUpperCase().charAt(0) + operationIn.descriptor.substring(1) + "In";
		parametersCommentLine.append("@param " + baseParameter);
		var	i			= 0;
		var	components	= "";
		for (final var component : componentsIn.get())
		{
			components += component;
			i++;
			if (i >= parameterClassInstanceComponentsNumberIn || i >= componentsIn.length())
			{
				break;
			}
		}

		if (operationIn.commentDescriptorInverted)
		{
			commentDescription.append(components).append(" ").append(operationIn.contractor).append(" ").append(baseParameter + "." + components);
		}
		else
		{
			commentDescription.append(baseParameter + "." + components + " ").append(operationIn.contractor).append(" ").append(components);
		}

		// @formatter:off
		return operationMethodPatternIn.replace("<parameters>", "final I" + parameterClassInstanceNameIn + " " + baseParameter)
				.replace("<verb>", operationIn.verb + " (" + typeIn +" version) : ")
				.replace("<comment_description>", commentDescription.toString())
				.replace("<parameters_comment_line>", parametersCommentLine.toString())
				.replace("<class>", classNameIn)
				.replace("<operations>", methodOperations.toString())
				.replace("<method_name>", operationIn.methodName)
				.replace("<return>", "this");
		// @formatter:on
	}

	private final static String operationMethod(final String startIn, final String classNameIn, final String operationMethodPatternIn, final String typeIn, final String parameterTypeIn, final Operation operationIn,
			final Components componentsIn, final Components toComponentsIn, final int componentsNumberIn)
	{
		final var	commentDescription		= new StringBuilder();
		final var	parametersCommentLine	= new StringBuilder();
		final var	methodOperations		= ColorClassCreator.operationParameters(startIn + "\t", componentsIn, operationIn.operator, toComponentsIn, operationIn.descriptor, typeIn, parameterTypeIn, true);

		final var	descriptor	= operationIn.descriptor.toUpperCase().charAt(0) + operationIn.descriptor.substring(1);
		var			i			= 0;
		var			components	= "";
		for (final var component : componentsIn.get())
		{
			components += component;
			final var parameter = component + descriptor + "In";
			parametersCommentLine.append("@param ").append(parameter);
			i++;
			if (i >= componentsNumberIn || i >= componentsIn.length())
			{
				break;
			}
			if (i < componentsIn.length())
			{
				parametersCommentLine.append("\r\n" + startIn + " * ");
			}
		}

		if (operationIn.commentDescriptorInverted)
		{
			commentDescription.append(components).append(" ").append(operationIn.contractor).append(" ").append(components + operationIn.descriptor + "In");
		}
		else
		{
			commentDescription.append(components + operationIn.descriptor + "In ").append(operationIn.contractor).append(" ").append(components);
		}

		// @formatter:off
		return operationMethodPatternIn.replace("<parameters>", componentsIn.parameters("final ", descriptor + "In").replace("<type>", parameterTypeIn))
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
		public final static Components of(final String... componentsIn)
		{
			return new Components(componentsIn);
		}

		String[] components;
		// String parameters;
		String list;

		public Components(final String... componentsIn)
		{
			this.components = componentsIn;
			// this.parameters = "";
			this.list = "";
			var i = 0;
			for (final var component : this.components)
			{
				// this.parameters += "<type> " + component + "In";
				this.list += component;
				i++;
				if (i < this.components.length)
				{
					// this.parameters += ", ";
					this.list += ", ";
				}
			}
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
		 * public String list() { return this.list; }
		 **/

		/**
		 * @author Seynax
		 * @param prefixIn
		 * @param suffixIn
		 * @return string parameters
		 */
		public String parameters(final String prefixIn, final String suffixIn)
		{
			final var	outputParameters	= new StringBuilder();
			var			i					= 0;
			for (final var component : this.components)
			{
				outputParameters.append(prefixIn + "<type> ").append(component).append(suffixIn);
				i++;
				if (i < this.components.length)
				{
					outputParameters.append(", ");
				}
			}
			return outputParameters.toString();
		}

		/**
		 * @author Seynax
		 * @param suffixIn
		 * @return
		 */
		/**
		 * public String parameters(final String suffixIn) { final var outputParameters = new StringBuilder(); var i = 0; for (final var component : this.components) { outputParameters.append("<type>
		 * ").append(component).append(suffixIn); i++; if (i < this.components.length) { outputParameters.append(", "); } } return outputParameters.toString(); }
		 **/

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
		/**
		 * public static Operation of(final String nameIn, final String verbIn, final String descriptorIn, final String operatorIn, final String contractorIn, final boolean commentDescriptorInvertedIn) { return new
		 * Operation(nameIn, verbIn, descriptorIn, operatorIn, contractorIn, commentDescriptorInvertedIn); }
		 **/

		/**
		 * @author Seynax
		 * @param nameIn
		 * @param verbIn
		 * @param methodNameIn
		 * @param descriptorIn
		 * @param operatorIn
		 * @param contractorIn
		 * @param commentDescriptorInvertedIn
		 * @return new Operation instance
		 */
		public static Operation of(final String nameIn, final String verbIn, final String methodNameIn, final String descriptorIn, final String operatorIn, final String contractorIn, final boolean commentDescriptorInvertedIn)
		{
			return new Operation(nameIn, verbIn, methodNameIn, descriptorIn, operatorIn, contractorIn, commentDescriptorInvertedIn);
		}

		final String	name;
		final String	verb;
		final String	methodName;
		final String	descriptor;
		final String	operator;
		final String	contractor;
		final boolean	commentDescriptorInverted;

		/**
		 * public Operation(final String nameIn, final String verbIn, final String descriptorIn, final String operatorIn, final String contractorIn, final boolean commentDescriptorInvertedIn) { this.name = nameIn; this.verb =
		 * verbIn; this.methodName = this.verb.toLowerCase().substring(0, 3); this.descriptor = descriptorIn; this.operator = operatorIn; this.contractor = contractorIn; this.commentDescriptorInverted =
		 * commentDescriptorInvertedIn; }
		 **/

		/**
		 * @param nameIn
		 * @param verbIn
		 * @param methodNameIn
		 * @param descriptorIn
		 * @param operatorIn
		 * @param contractorIn
		 * @param commentDescriptorInvertedIn
		 */
		public Operation(final String nameIn, final String verbIn, final String methodNameIn, final String descriptorIn, final String operatorIn, final String contractorIn, final boolean commentDescriptorInvertedIn)
		{
			this.name						= nameIn;
			this.verb						= verbIn;
			this.methodName					= methodNameIn;
			this.descriptor					= descriptorIn;
			this.operator					= operatorIn;
			this.contractor					= contractorIn;
			this.commentDescriptorInverted	= commentDescriptorInvertedIn;
		}
	}
}