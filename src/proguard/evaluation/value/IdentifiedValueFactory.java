/*
 * ProGuard -- shrinking, optimization, obfuscation, and preverification
 *             of Java bytecode.
 *
 * Copyright (c) 2002-2018 GuardSquare NV
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package proguard.evaluation.value;

import proguard.classfile.ClassConstants;
import proguard.classfile.Clazz;

/**
 * This class provides methods to create and reuse Value objects that are
 * identified by unique integer IDs.
 *
 * @author Eric Lafortune
 */
public class IdentifiedValueFactory
extends      ParticularValueFactory
{
    protected int integerID;
    protected int longID;
    protected int floatID;
    protected int doubleID;
    protected int referenceID;


   // Implementations for BasicValueFactory.

    public IntegerValue createIntegerValue()
    {
        return new IdentifiedIntegerValue(this, integerID++);
    }


    public LongValue createLongValue()
    {
        return new IdentifiedLongValue(this, longID++);
    }


    public FloatValue createFloatValue()
    {
        return new IdentifiedFloatValue(this, floatID++);
    }


    public DoubleValue createDoubleValue()
    {
        return new IdentifiedDoubleValue(this, doubleID++);
    }


    public ReferenceValue createReferenceValue(String  type,
                                               Clazz   referencedClass,
                                               boolean mayBeExtension,
                                               boolean mayBeNull)
    {
        return type == null ?
            TypedReferenceValueFactory.REFERENCE_VALUE_NULL :
            new IdentifiedReferenceValue(type,
                                         referencedClass,
                                         mayBeExtension,
                                         mayBeNull,
                                         this,
                                         referenceID++);
    }


    public ReferenceValue createArrayReferenceValue(String       type,
                                                    Clazz        referencedClass,
                                                    IntegerValue arrayLength)
    {
        return type == null ?
            TypedReferenceValueFactory.REFERENCE_VALUE_NULL :
            new IdentifiedArrayReferenceValue(ClassConstants.TYPE_ARRAY + type,
                                              referencedClass,
                                              false,
                                              arrayLength,
                                              this,
                                              referenceID++);
    }
}