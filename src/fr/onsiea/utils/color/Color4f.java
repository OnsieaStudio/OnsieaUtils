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
 * Color4f
 *
 * @author : Seynax (https://github.com/seynax)<br>
 * @organization : Onsiea Studio (https://github.com/OnsieaStudio)<br>
 *               <br>
 *               4 float Color utils class with r, g, b, a
 */
public final class Color4f implements IColor4f
{
	float	r;
	float	g;
	float	b;
	float	a;

	// Constructors

	public Color4f()
	{

	}

	public Color4f(final IColor2b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color4f(final byte rIn, final byte gIn)
	{
		this.r	= rIn;
		this.g	= gIn;
	}

	public Color4f(final IColor2i colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color4f(final int rIn, final int gIn)
	{
		this.r	= rIn;
		this.g	= gIn;
	}

	public Color4f(final IColor2f colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
	}

	public Color4f(final float rIn, final float gIn)
	{
		this.r	= rIn;
		this.g	= gIn;
	}

	public Color4f(final IColor3b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
	}

	public Color4f(final byte rIn, final byte gIn, final byte bIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
	}

	public Color4f(final IColor3i colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
	}

	public Color4f(final int rIn, final int gIn, final int bIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
	}

	public Color4f(final IColor3f colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
	}

	public Color4f(final float rIn, final float gIn, final float bIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
	}

	public Color4f(final IColor4b colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
		this.a	= colorIn.a();
	}

	public Color4f(final byte rIn, final byte gIn, final byte bIn, final byte aIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
		this.a	= aIn;
	}

	public Color4f(final IColor4i colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
		this.a	= colorIn.a();
	}

	public Color4f(final int rIn, final int gIn, final int bIn, final int aIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
		this.a	= aIn;
	}

	public Color4f(final IColor4f colorIn)
	{
		this.r	= colorIn.r();
		this.g	= colorIn.g();
		this.b	= colorIn.b();
		this.a	= colorIn.a();
	}

	public Color4f(final float rIn, final float gIn, final float bIn, final float aIn)
	{
		this.r	= rIn;
		this.g	= gIn;
		this.b	= bIn;
		this.a	= aIn;
	}

	/**
	 * Addition
	 */

	/**
	 * Add (float version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor2b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (float version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final byte rAdderIn, final byte gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor2i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (float version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final int rAdderIn, final int gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor2f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();

		return this;
	}

	/**
	 * Add (float version) : rgadderIn to rg
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final float rAdderIn, final float gAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor3b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();

		return this;
	}

	/**
	 * Add (float version) : rgbadderIn to rgb
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final byte rAdderIn, final byte gAdderIn, final byte bAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor3i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();

		return this;
	}

	/**
	 * Add (float version) : rgbadderIn to rgb
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final int rAdderIn, final int gAdderIn, final int bAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor3f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();

		return this;
	}

	/**
	 * Add (float version) : rgbadderIn to rgb
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final float rAdderIn, final float gAdderIn, final float bAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor4b colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();
		this.a	+= colorAdderIn.a();

		return this;
	}

	/**
	 * Add (float version) : rgbaadderIn to rgba
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @param aAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final byte rAdderIn, final byte gAdderIn, final byte bAdderIn, final byte aAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;
		this.a	+= aAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor4i colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();
		this.a	+= colorAdderIn.a();

		return this;
	}

	/**
	 * Add (float version) : rgbaadderIn to rgba
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @param aAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final int rAdderIn, final int gAdderIn, final int bAdderIn, final int aAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;
		this.a	+= aAdderIn;

		return this;
	}

	/**
	 * Add (float version) : colorAdderIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final IColor4f colorAdderIn)
	{
		this.r	+= colorAdderIn.r();
		this.g	+= colorAdderIn.g();
		this.b	+= colorAdderIn.b();
		this.a	+= colorAdderIn.a();

		return this;
	}

	/**
	 * Add (float version) : rgbaadderIn to rgba
	 *
	 * @author Seynax
	 * @param rAdderIn
	 * @param gAdderIn
	 * @param bAdderIn
	 * @param aAdderIn
	 * @return this Color4f instance
	 */
	public Color4f add(final float rAdderIn, final float gAdderIn, final float bAdderIn, final float aAdderIn)
	{
		this.r	+= rAdderIn;
		this.g	+= gAdderIn;
		this.b	+= bAdderIn;
		this.a	+= aAdderIn;

		return this;
	}

	/**
	 * Substraction
	 */

	/**
	 * Substract (float version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor2b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (float version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final byte rSubstractorIn, final byte gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor2i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (float version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final int rSubstractorIn, final int gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rg to rg
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor2f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();

		return this;
	}

	/**
	 * Substract (float version) : rgsubstractorIn to rg
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final float rSubstractorIn, final float gSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor3b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();

		return this;
	}

	/**
	 * Substract (float version) : rgbsubstractorIn to rgb
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final byte rSubstractorIn, final byte gSubstractorIn, final byte bSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor3i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();

		return this;
	}

	/**
	 * Substract (float version) : rgbsubstractorIn to rgb
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final int rSubstractorIn, final int gSubstractorIn, final int bSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgb to rgb
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor3f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();

		return this;
	}

	/**
	 * Substract (float version) : rgbsubstractorIn to rgb
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final float rSubstractorIn, final float gSubstractorIn, final float bSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor4b colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();
		this.a	-= colorSubstractorIn.a();

		return this;
	}

	/**
	 * Substract (float version) : rgbasubstractorIn to rgba
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @param aSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final byte rSubstractorIn, final byte gSubstractorIn, final byte bSubstractorIn, final byte aSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;
		this.a	-= aSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor4i colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();
		this.a	-= colorSubstractorIn.a();

		return this;
	}

	/**
	 * Substract (float version) : rgbasubstractorIn to rgba
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @param aSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final int rSubstractorIn, final int gSubstractorIn, final int bSubstractorIn, final int aSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;
		this.a	-= aSubstractorIn;

		return this;
	}

	/**
	 * Substract (float version) : colorSubstractorIn.rgba to rgba
	 *
	 * @author Seynax
	 * @param colorSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final IColor4f colorSubstractorIn)
	{
		this.r	-= colorSubstractorIn.r();
		this.g	-= colorSubstractorIn.g();
		this.b	-= colorSubstractorIn.b();
		this.a	-= colorSubstractorIn.a();

		return this;
	}

	/**
	 * Substract (float version) : rgbasubstractorIn to rgba
	 *
	 * @author Seynax
	 * @param rSubstractorIn
	 * @param gSubstractorIn
	 * @param bSubstractorIn
	 * @param aSubstractorIn
	 * @return this Color4f instance
	 */
	public Color4f sub(final float rSubstractorIn, final float gSubstractorIn, final float bSubstractorIn, final float aSubstractorIn)
	{
		this.r	-= rSubstractorIn;
		this.g	-= gSubstractorIn;
		this.b	-= bSubstractorIn;
		this.a	-= aSubstractorIn;

		return this;
	}

	/**
	 * Multiplication
	 */

	/**
	 * Multiply (float version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor2b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (float version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final byte rMultiplicatorIn, final byte gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor2i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (float version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final int rMultiplicatorIn, final int gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rg by colorMultiplicatorIn.rg
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor2f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();

		return this;
	}

	/**
	 * Multiply (float version) : rg by rgmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final float rMultiplicatorIn, final float gMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgb by colorMultiplicatorIn.rgb
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor3b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();

		return this;
	}

	/**
	 * Multiply (float version) : rgb by rgbmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final byte rMultiplicatorIn, final byte gMultiplicatorIn, final byte bMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgb by colorMultiplicatorIn.rgb
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor3i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();

		return this;
	}

	/**
	 * Multiply (float version) : rgb by rgbmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final int rMultiplicatorIn, final int gMultiplicatorIn, final int bMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgb by colorMultiplicatorIn.rgb
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor3f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();

		return this;
	}

	/**
	 * Multiply (float version) : rgb by rgbmultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final float rMultiplicatorIn, final float gMultiplicatorIn, final float bMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgba by colorMultiplicatorIn.rgba
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor4b colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();
		this.a	*= colorMultiplicatorIn.a();

		return this;
	}

	/**
	 * Multiply (float version) : rgba by rgbamultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @param aMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final byte rMultiplicatorIn, final byte gMultiplicatorIn, final byte bMultiplicatorIn, final byte aMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;
		this.a	*= aMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgba by colorMultiplicatorIn.rgba
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor4i colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();
		this.a	*= colorMultiplicatorIn.a();

		return this;
	}

	/**
	 * Multiply (float version) : rgba by rgbamultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @param aMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final int rMultiplicatorIn, final int gMultiplicatorIn, final int bMultiplicatorIn, final int aMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;
		this.a	*= aMultiplicatorIn;

		return this;
	}

	/**
	 * Multiply (float version) : rgba by colorMultiplicatorIn.rgba
	 *
	 * @author Seynax
	 * @param colorMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final IColor4f colorMultiplicatorIn)
	{
		this.r	*= colorMultiplicatorIn.r();
		this.g	*= colorMultiplicatorIn.g();
		this.b	*= colorMultiplicatorIn.b();
		this.a	*= colorMultiplicatorIn.a();

		return this;
	}

	/**
	 * Multiply (float version) : rgba by rgbamultiplicatorIn
	 *
	 * @author Seynax
	 * @param rMultiplicatorIn
	 * @param gMultiplicatorIn
	 * @param bMultiplicatorIn
	 * @param aMultiplicatorIn
	 * @return this Color4f instance
	 */
	public Color4f mul(final float rMultiplicatorIn, final float gMultiplicatorIn, final float bMultiplicatorIn, final float aMultiplicatorIn)
	{
		this.r	*= rMultiplicatorIn;
		this.g	*= gMultiplicatorIn;
		this.b	*= bMultiplicatorIn;
		this.a	*= aMultiplicatorIn;

		return this;
	}

	/**
	 * Division
	 */

	/**
	 * Divide (float version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor2b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (float version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final byte rDivisorIn, final byte gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor2i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (float version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final int rDivisorIn, final int gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rg by colorDivisorIn.rg
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor2f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();

		return this;
	}

	/**
	 * Divide (float version) : rg by rgdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final float rDivisorIn, final float gDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgb by colorDivisorIn.rgb
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor3b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();

		return this;
	}

	/**
	 * Divide (float version) : rgb by rgbdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final byte rDivisorIn, final byte gDivisorIn, final byte bDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgb by colorDivisorIn.rgb
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor3i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();

		return this;
	}

	/**
	 * Divide (float version) : rgb by rgbdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final int rDivisorIn, final int gDivisorIn, final int bDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgb by colorDivisorIn.rgb
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor3f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();

		return this;
	}

	/**
	 * Divide (float version) : rgb by rgbdivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final float rDivisorIn, final float gDivisorIn, final float bDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgba by colorDivisorIn.rgba
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor4b colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();
		this.a	/= colorDivisorIn.a();

		return this;
	}

	/**
	 * Divide (float version) : rgba by rgbadivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @param aDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final byte rDivisorIn, final byte gDivisorIn, final byte bDivisorIn, final byte aDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;
		this.a	/= aDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgba by colorDivisorIn.rgba
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor4i colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();
		this.a	/= colorDivisorIn.a();

		return this;
	}

	/**
	 * Divide (float version) : rgba by rgbadivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @param aDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final int rDivisorIn, final int gDivisorIn, final int bDivisorIn, final int aDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;
		this.a	/= aDivisorIn;

		return this;
	}

	/**
	 * Divide (float version) : rgba by colorDivisorIn.rgba
	 *
	 * @author Seynax
	 * @param colorDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final IColor4f colorDivisorIn)
	{
		this.r	/= colorDivisorIn.r();
		this.g	/= colorDivisorIn.g();
		this.b	/= colorDivisorIn.b();
		this.a	/= colorDivisorIn.a();

		return this;
	}

	/**
	 * Divide (float version) : rgba by rgbadivisorIn
	 *
	 * @author Seynax
	 * @param rDivisorIn
	 * @param gDivisorIn
	 * @param bDivisorIn
	 * @param aDivisorIn
	 * @return this Color4f instance
	 */
	public Color4f div(final float rDivisorIn, final float gDivisorIn, final float bDivisorIn, final float aDivisorIn)
	{
		this.r	/= rDivisorIn;
		this.g	/= gDivisorIn;
		this.b	/= bDivisorIn;
		this.a	/= aDivisorIn;

		return this;
	}

	/**
	 * Modulation
	 */

	/**
	 * Module (float version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor2b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (float version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final byte rModulorIn, final byte gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor2i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (float version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final int rModulorIn, final int gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rg by colorModulorIn.rg
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor2f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();

		return this;
	}

	/**
	 * Module (float version) : rg by rgmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final float rModulorIn, final float gModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgb by colorModulorIn.rgb
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor3b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();

		return this;
	}

	/**
	 * Module (float version) : rgb by rgbmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final byte rModulorIn, final byte gModulorIn, final byte bModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgb by colorModulorIn.rgb
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor3i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();

		return this;
	}

	/**
	 * Module (float version) : rgb by rgbmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final int rModulorIn, final int gModulorIn, final int bModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgb by colorModulorIn.rgb
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor3f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();

		return this;
	}

	/**
	 * Module (float version) : rgb by rgbmodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final float rModulorIn, final float gModulorIn, final float bModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgba by colorModulorIn.rgba
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor4b colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();
		this.a	%= colorModulorIn.a();

		return this;
	}

	/**
	 * Module (float version) : rgba by rgbamodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @param aModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final byte rModulorIn, final byte gModulorIn, final byte bModulorIn, final byte aModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;
		this.a	%= aModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgba by colorModulorIn.rgba
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor4i colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();
		this.a	%= colorModulorIn.a();

		return this;
	}

	/**
	 * Module (float version) : rgba by rgbamodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @param aModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final int rModulorIn, final int gModulorIn, final int bModulorIn, final int aModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;
		this.a	%= aModulorIn;

		return this;
	}

	/**
	 * Module (float version) : rgba by colorModulorIn.rgba
	 *
	 * @author Seynax
	 * @param colorModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final IColor4f colorModulorIn)
	{
		this.r	%= colorModulorIn.r();
		this.g	%= colorModulorIn.g();
		this.b	%= colorModulorIn.b();
		this.a	%= colorModulorIn.a();

		return this;
	}

	/**
	 * Module (float version) : rgba by rgbamodulorIn
	 *
	 * @author Seynax
	 * @param rModulorIn
	 * @param gModulorIn
	 * @param bModulorIn
	 * @param aModulorIn
	 * @return this Color4f instance
	 */
	public Color4f mod(final float rModulorIn, final float gModulorIn, final float bModulorIn, final float aModulorIn)
	{
		this.r	%= rModulorIn;
		this.g	%= gModulorIn;
		this.b	%= bModulorIn;
		this.a	%= aModulorIn;

		return this;
	}

	/**
	 * Setters
	 */

	/**
	 * Set (float version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor2b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (float version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final byte rSetterIn, final byte gSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor2i colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (float version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final int rSetterIn, final int gSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rg to colorSetterIn.rg
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor2f colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();

		return this;
	}

	/**
	 * Set (float version) : rg to rgsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final float rSetterIn, final float gSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgb to colorSetterIn.rgb
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor3b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();

		return this;
	}

	/**
	 * Set (float version) : rgb to rgbsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final byte rSetterIn, final byte gSetterIn, final byte bSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgb to colorSetterIn.rgb
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor3i colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();

		return this;
	}

	/**
	 * Set (float version) : rgb to rgbsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final int rSetterIn, final int gSetterIn, final int bSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgb to colorSetterIn.rgb
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor3f colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();

		return this;
	}

	/**
	 * Set (float version) : rgb to rgbsetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final float rSetterIn, final float gSetterIn, final float bSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgba to colorSetterIn.rgba
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor4b colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();
		this.a	= colorSetterIn.a();

		return this;
	}

	/**
	 * Set (float version) : rgba to rgbasetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @param aSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final byte rSetterIn, final byte gSetterIn, final byte bSetterIn, final byte aSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;
		this.a	= aSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgba to colorSetterIn.rgba
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor4i colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();
		this.a	= colorSetterIn.a();

		return this;
	}

	/**
	 * Set (float version) : rgba to rgbasetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @param aSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final int rSetterIn, final int gSetterIn, final int bSetterIn, final int aSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;
		this.a	= aSetterIn;

		return this;
	}

	/**
	 * Set (float version) : rgba to colorSetterIn.rgba
	 *
	 * @author Seynax
	 * @param colorSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final IColor4f colorSetterIn)
	{
		this.r	= colorSetterIn.r();
		this.g	= colorSetterIn.g();
		this.b	= colorSetterIn.b();
		this.a	= colorSetterIn.a();

		return this;
	}

	/**
	 * Set (float version) : rgba to rgbasetterIn
	 *
	 * @author Seynax
	 * @param rSetterIn
	 * @param gSetterIn
	 * @param bSetterIn
	 * @param aSetterIn
	 * @return this Color4f instance
	 */
	public Color4f set(final float rSetterIn, final float gSetterIn, final float bSetterIn, final float aSetterIn)
	{
		this.r	= rSetterIn;
		this.g	= gSetterIn;
		this.b	= bSetterIn;
		this.a	= aSetterIn;

		return this;
	}

	// Converters and copy

	public Color4f copy()
	{
		return new Color4f(this);
	}

	public Color4f copy(final Color4f colorIn)
	{
		colorIn.r	= this.r;
		colorIn.g	= this.g;
		colorIn.b	= this.b;
		colorIn.a	= this.a;

		return colorIn;
	}

	public Color4f.Constant constantCopy()
	{
		return new Color4f.Constant(this);
	}

	public Color2b copy(final Color2b colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();

		return colorIn;
	}

	public Color2b copy2b()
	{
		return new Color2b(this);
	}

	public Color2b.Constant constantCopy2b()
	{
		return new Color2b.Constant(this);
	}

	public Color3b copy(final Color3b colorIn)
	{
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();
		colorIn.b	= colorIn.b();

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
		colorIn.r	= colorIn.r();
		colorIn.g	= colorIn.g();
		colorIn.b	= colorIn.b();
		colorIn.a	= colorIn.a();

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
		colorIn.b	= colorIn.b();

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
		colorIn.b	= colorIn.b();
		colorIn.a	= colorIn.a();

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
		colorIn.r	= this.r;
		colorIn.g	= this.g;

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
		colorIn.r	= this.r;
		colorIn.g	= this.g;
		colorIn.b	= this.b;

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

	// Setters | Getters

	public Color4f r(final float rIn)
	{
		this.r = rIn;

		return this;
	}

	@Override
	public float r()
	{
		return this.r;
	}

	public Color4f g(final float gIn)
	{
		this.g = gIn;

		return this;
	}

	@Override
	public float g()
	{
		return this.g;
	}

	public Color4f b(final float bIn)
	{
		this.b = bIn;

		return this;
	}

	@Override
	public float b()
	{
		return this.b;
	}

	public Color4f a(final float aIn)
	{
		this.a = aIn;

		return this;
	}

	@Override
	public float a()
	{
		return this.a;
	}

	public final static class Constant implements IColor4f
	{
		private final float	r;
		private final float	g;
		private final float	b;
		private final float	a;

		// Constructors

		public Constant()
		{
			this.r	= 0;
			this.g	= 0;
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final IColor2b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final byte rIn, final byte gIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final IColor2i colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final int rIn, final int gIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final IColor2f colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final float rIn, final float gIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= 0;
			this.a	= 0;
		}

		public Constant(final IColor3b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= 0;
		}

		public Constant(final byte rIn, final byte gIn, final byte bIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= 0;
		}

		public Constant(final IColor3i colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= 0;
		}

		public Constant(final int rIn, final int gIn, final int bIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= 0;
		}

		public Constant(final IColor3f colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= 0;
		}

		public Constant(final float rIn, final float gIn, final float bIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= 0;
		}

		public Constant(final IColor4b colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= colorIn.a();
		}

		public Constant(final byte rIn, final byte gIn, final byte bIn, final byte aIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= aIn;
		}

		public Constant(final IColor4i colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= colorIn.a();
		}

		public Constant(final int rIn, final int gIn, final int bIn, final int aIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= aIn;
		}

		public Constant(final IColor4f colorIn)
		{
			this.r	= colorIn.r();
			this.g	= colorIn.g();
			this.b	= colorIn.b();
			this.a	= colorIn.a();
		}

		public Constant(final float rIn, final float gIn, final float bIn, final float aIn)
		{
			this.r	= rIn;
			this.g	= gIn;
			this.b	= bIn;
			this.a	= aIn;
		}

		// Converters and copy

		public Color4f copy()
		{
			return new Color4f(this);
		}

		public Color4f copy(final Color4f colorIn)
		{
			colorIn.r	= this.r;
			colorIn.g	= this.g;
			colorIn.b	= this.b;
			colorIn.a	= this.a;

			return colorIn;
		}

		public Color4f.Constant constantCopy()
		{
			return new Color4f.Constant(this);
		}

		public Color2b copy(final Color2b colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();

			return colorIn;
		}

		public Color2b copy2b()
		{
			return new Color2b(this);
		}

		public Color2b.Constant constantCopy2b()
		{
			return new Color2b.Constant(this);
		}

		public Color3b copy(final Color3b colorIn)
		{
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();
			colorIn.b	= colorIn.b();

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
			colorIn.r	= colorIn.r();
			colorIn.g	= colorIn.g();
			colorIn.b	= colorIn.b();
			colorIn.a	= colorIn.a();

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
			colorIn.b	= colorIn.b();

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
			colorIn.b	= colorIn.b();
			colorIn.a	= colorIn.a();

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
			colorIn.r	= this.r;
			colorIn.g	= this.g;

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
			colorIn.r	= this.r;
			colorIn.g	= this.g;
			colorIn.b	= this.b;

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

		// Getters

		@Override
		public float r()
		{
			return this.r;
		}

		@Override
		public float g()
		{
			return this.g;
		}

		@Override
		public float b()
		{
			return this.b;
		}

		@Override
		public float a()
		{
			return this.a;
		}
	}
}