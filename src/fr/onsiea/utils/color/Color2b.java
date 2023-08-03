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
package fr.onsiea.utils.color;

import fr.onsiea.utils.color.prototype.IColor2b;
import fr.onsiea.utils.color.prototype.IColor2f;
import fr.onsiea.utils.color.prototype.IColor2i;
import fr.onsiea.utils.color.prototype.IColor3b;
import fr.onsiea.utils.color.prototype.IColor3f;
import fr.onsiea.utils.color.prototype.IColor3i;
import fr.onsiea.utils.color.prototype.IColor4b;
import fr.onsiea.utils.color.prototype.IColor4f;
import fr.onsiea.utils.color.prototype.IColor4i;

/**
 * Color2b
 *
 * @author : Seynax (https://github.com/seynax)<br>
 * @organization : Onsiea Studio (https://github.com/OnsieaStudio)<br>
 *               <br>
 *               2 byte Color utils class with r, g
 */
public final class Color2b implements IColor2b
{
	byte	r;
	byte	g;

	// Constructors

	public Color2b()
	{

	}

	public Color2b(final IColor2b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color2b(final byte rIn, final byte gIn)
	{
		this.r	= rIn;
		this.g	= gIn;
	}

	public Color2b(final IColor2i colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	public Color2b(final int rIn, final int gIn)
	{
		this.r	= (byte) rIn;
		this.g	= (byte) gIn;
	}

	public Color2b(final IColor2f colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	public Color2b(final float rIn, final float gIn)
	{
		this.r	= (byte) rIn;
		this.g	= (byte) gIn;
	}

	public Color2b(final IColor3b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color2b(final IColor3i colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	public Color2b(final IColor3f colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	public Color2b(final IColor4b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color2b(final IColor4i colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	public Color2b(final IColor4f colorIn)
	{
		this.r	= (byte) colorIn.r();
		this.g	= (byte) colorIn.g();
	}

	/**
	 * Addition
	 */

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor2b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final byte rAdderIn, final byte gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor2i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final int rAdderIn, final int gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor2f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final float rAdderIn, final float gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor3b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor3i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor3f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor4b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor4i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (byte version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color2b instance
	 */
	public Color2b add(final IColor4f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Substraction
	 */

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor2b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final byte rSubstractorIn, final byte gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor2i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final int rSubstractorIn, final int gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor2f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final float rSubstractorIn, final float gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor3b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor3i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor3f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor4b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor4i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (byte version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color2b instance
	 */
	public Color2b sub(final IColor4f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Multiplication
	 */

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor2b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final byte rMultiplicatorIn, final byte gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor2i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final int rMultiplicatorIn, final int gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor2f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final float rMultiplicatorIn, final float gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor3b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor3i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor3f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor4b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor4i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (byte version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color2b instance
	 */
	public Color2b mul(final IColor4f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Division
	 */

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor2b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final byte rDivisorIn, final byte gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor2i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final int rDivisorIn, final int gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor2f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final float rDivisorIn, final float gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor3b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor3i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor3f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor4b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor4i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (byte version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color2b instance
	 */
	public Color2b div(final IColor4f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Modulation
	 */

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor2b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final byte rModulorIn, final byte gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor2i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final int rModulorIn, final int gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor2f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final float rModulorIn, final float gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor3b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor3i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor3f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor4b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor4i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (byte version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color2b instance
	 */
	public Color2b mod(final IColor4f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Setters
	 */

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor2b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final byte rSetterIn, final byte gSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor2i colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final int rSetterIn, final int gSetterIn)
	{
		this.r	= (byte) rSetterIn;
		this.g	= (byte) gSetterIn;

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor2f colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final float rSetterIn, final float gSetterIn)
	{
		this.r	= (byte) rSetterIn;
		this.g	= (byte) gSetterIn;

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor3b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor3i colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor3f colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor4b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor4i colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	/**
	 * Set (byte version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color2b instance
	 */
	public Color2b set(final IColor4f colorSetterIn)
	{
		this.r	= (byte) colorSetterIn.r();
		this.g	= (byte) colorSetterIn.g();

		return this;
	}

	// Converters and copy

	public Color2b copy()
	{
		return new Color2b(this);
	}

	public Color2b copy(final Color2b colorIn)
	{
		colorIn.r	= this.r;
		colorIn.g	= this.g;

		return colorIn;
	}

	public Color2b.Constant constantCopy()
	{
		return new Color2b.Constant(this);
	}

	public Color3b copy(final Color3b colorIn)
	{
		colorIn.r	= this.r;
		colorIn.g	= this.g;

		return colorIn;
	}

	public Color3b copy3b()
	{
		return new Color3b(this);
	}

	public Color3b.Constant constantCopy3b()
	{
		return new Color3b.Constant(this);
	}

	public Color4b copy(final Color4b colorIn)
	{
		colorIn.r	= this.r;
		colorIn.g	= this.g;

		return colorIn;
	}

	public Color4b copy4b()
	{
		return new Color4b(this);
	}

	public Color4b.Constant constantCopy4b()
	{
		return new Color4b.Constant(this);
	}

	public Color2i copy(final Color2i colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color2i copy2i()
	{
		return new Color2i(this);
	}

	public Color2i.Constant constantCopy2i()
	{
		return new Color2i.Constant(this);
	}

	public Color3i copy(final Color3i colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color3i copy3i()
	{
		return new Color3i(this);
	}

	public Color3i.Constant constantCopy3i()
	{
		return new Color3i.Constant(this);
	}

	public Color4i copy(final Color4i colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color4i copy4i()
	{
		return new Color4i(this);
	}

	public Color4i.Constant constantCopy4i()
	{
		return new Color4i.Constant(this);
	}

	public Color2f copy(final Color2f colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color2f copy2f()
	{
		return new Color2f(this);
	}

	public Color2f.Constant constantCopy2f()
	{
		return new Color2f.Constant(this);
	}

	public Color3f copy(final Color3f colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color3f copy3f()
	{
		return new Color3f(this);
	}

	public Color3f.Constant constantCopy3f()
	{
		return new Color3f.Constant(this);
	}

	public Color4f copy(final Color4f colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color4f copy4f()
	{
		return new Color4f(this);
	}

	public Color4f.Constant constantCopy4f()
	{
		return new Color4f.Constant(this);
	}

	// Setters | Getters

	public Color2b r(final byte rIn)
	{
		this.r = rIn;

		return this;
	}

	@Override
	public byte r()
	{
		return this.r;
	}

	public Color2b g(final byte gIn)
	{
		this.g = gIn;

		return this;
	}

	@Override
	public byte g()
	{
		return this.g;
	}

	public final static class Constant implements IColor2b
	{
		private final byte	r;
		private final byte	g;

		// Constructors

		public Constant()
		{
			this.r	= 0;
			this.g	= 0;
		}

		public Constant(final IColor2b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
		}

		public Constant(final byte rIn, final byte gIn)
		{
			this.r	= rIn;
			this.g	= gIn;
		}

		public Constant(final IColor2i colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		public Constant(final int rIn, final int gIn)
		{
			this.r	= (byte) rIn;
			this.g	= (byte) gIn;
		}

		public Constant(final IColor2f colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		public Constant(final float rIn, final float gIn)
		{
			this.r	= (byte) rIn;
			this.g	= (byte) gIn;
		}

		public Constant(final IColor3b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
		}

		public Constant(final IColor3i colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		public Constant(final IColor3f colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		public Constant(final IColor4b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
		}

		public Constant(final IColor4i colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		public Constant(final IColor4f colorIn)
		{
			this.r	= (byte) colorIn.r();
			this.g	= (byte) colorIn.g();
		}

		// Converters and copy

		public Color2b copy()
		{
			return new Color2b(this);
		}

		public Color2b copy(final Color2b colorIn)
		{
			colorIn.r	= this.r;
			colorIn.g	= this.g;

			return colorIn;
		}

		public Color2b.Constant constantCopy()
		{
			return new Color2b.Constant(this);
		}

		public Color3b copy(final Color3b colorIn)
		{
			colorIn.r	= this.r;
			colorIn.g	= this.g;

			return colorIn;
		}

		public Color3b copy3b()
		{
			return new Color3b(this);
		}

		public Color3b.Constant constantCopy3b()
		{
			return new Color3b.Constant(this);
		}

		public Color4b copy(final Color4b colorIn)
		{
			colorIn.r	= this.r;
			colorIn.g	= this.g;

			return colorIn;
		}

		public Color4b copy4b()
		{
			return new Color4b(this);
		}

		public Color4b.Constant constantCopy4b()
		{
			return new Color4b.Constant(this);
		}

		public Color2i copy(final Color2i colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color2i copy2i()
		{
			return new Color2i(this);
		}

		public Color2i.Constant constantCopy2i()
		{
			return new Color2i.Constant(this);
		}

		public Color3i copy(final Color3i colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color3i copy3i()
		{
			return new Color3i(this);
		}

		public Color3i.Constant constantCopy3i()
		{
			return new Color3i.Constant(this);
		}

		public Color4i copy(final Color4i colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color4i copy4i()
		{
			return new Color4i(this);
		}

		public Color4i.Constant constantCopy4i()
		{
			return new Color4i.Constant(this);
		}

		public Color2f copy(final Color2f colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color2f copy2f()
		{
			return new Color2f(this);
		}

		public Color2f.Constant constantCopy2f()
		{
			return new Color2f.Constant(this);
		}

		public Color3f copy(final Color3f colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color3f copy3f()
		{
			return new Color3f(this);
		}

		public Color3f.Constant constantCopy3f()
		{
			return new Color3f.Constant(this);
		}

		public Color4f copy(final Color4f colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color4f copy4f()
		{
			return new Color4f(this);
		}

		public Color4f.Constant constantCopy4f()
		{
			return new Color4f.Constant(this);
		}

		// Getters

		@Override
		public byte r()
		{
			return this.r;
		}

		@Override
		public byte g()
		{
			return this.g;
		}
	}
}