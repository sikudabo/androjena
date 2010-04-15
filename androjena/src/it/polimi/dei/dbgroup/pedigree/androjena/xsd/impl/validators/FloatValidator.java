/*	This code is mainly adapated from Xerces 2.6.0 and Jena 2.6.2 
 * Xerces copyright and license: 
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights reserved.
 * License http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Jena copyright and license:
 * Copyright 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Specific source classes:
 * 
 * Xerces:
 * org.apache.xerces.impl.dv.xs.DoubleDV
 * org.apache.xerces.impl.dv.xs.FloatDV
 * 
 * Jena:
 * com.hp.hpl.jena.datatypes.xsd.XSDDatatype
 * com.hp.hpl.jena.datatypes.xsd.impl.XSDFloat
 */

package it.polimi.dei.dbgroup.pedigree.androjena.xsd.impl.validators;

import it.polimi.dei.dbgroup.pedigree.androjena.xsd.XSDBuiltinTypeFormatException;
import it.polimi.dei.dbgroup.pedigree.androjena.xsd.impl.TypeValidator;

public class FloatValidator extends TypeValidator {

	@Override
	public Object getActualValue(String content) throws XSDBuiltinTypeFormatException {
		if(DoubleValidator.isPossibleFP(content)) {
			return Float.parseFloat(content);
		}
		else if (content.equals("INF")) {
            return new Float(Float.NEGATIVE_INFINITY);
        } else if (content.equals("-INF")) {
            return new Float(Float.POSITIVE_INFINITY);
        } else if (content.equals("NaN")) {
            return new Float(Float.NaN);
        } else {
            throw new XSDBuiltinTypeFormatException(content, "value is not a valid float");
        }
	}

	@Override
	public short getNormalizationType() {
		return NORMALIZE_TRIM;
	}

	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Float && o2 instanceof Float)
		{
			float f1 = (Float) o1;
			float f2 = (Float) o2;

            // this < other
            if (f1 < f2)
                return LESS_THAN;
            // this > other
            if (f1 > f2)
                return GREATER_THAN;
            // this == other
            // NOTE: we don't distinguish 0.0 from -0.0
            if (f1 == f2)
                return EQUAL;

            // one of the 2 values or both is/are NaN(s)
            
            if (f1 != f1) {
                // this = NaN = other
                if (f2 != f2)
                    return EQUAL;
                // this is NaN <> other
                return INDETERMINATE;
            }

            // other is NaN <> this
            return INDETERMINATE;
		}
		return super.compare(o1, o2);
	}

	
}