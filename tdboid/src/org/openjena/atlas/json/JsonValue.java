/*
 * (c) Copyright 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.atlas.json;

import org.openjena.atlas.io.IndentedLineBuffer ;
import org.openjena.atlas.io.IndentedWriter ;
import org.openjena.atlas.io.Printable ;
import org.openjena.atlas.json.io.JsonWriter ;

public abstract class JsonValue implements Printable
{
    // Called a "Value" in the JSON spec 
    // Called Element in gson.
    
    public boolean isObject()       { return false ; }
    public JsonObject getAsObject() { return null ; }
    
    public boolean isArray()        { return this instanceof JsonArray ; }
    public JsonArray getAsArray()   { return null ; }
    
    public boolean isPrimitive()    { return isString() || isNumber() || isBoolean() || isNull() ; }
    public boolean isNull()         { return false ; }

    public boolean isNumber()       { return false ; }
    public JsonNumber getNumber()   { return null ; }
    
    public boolean isString()       { return false ; }
    public JsonString getString()   { return null ; }
    
    public boolean isBoolean()      { return false ; }
    public JsonBoolean getBoolean() { return null ; }
    
    @Override public abstract int hashCode() ;
    @Override public abstract boolean equals(Object other) ;
    
    
    public abstract void visit(JsonVisitor visitor) ;

    @Override
    public String toString()
    {
        IndentedLineBuffer buff = new IndentedLineBuffer() ;
        output(buff) ;
        return buff.asString() ;
    }
    
    //@Override
    public void output(IndentedWriter out)
    {
        JsonWriter w = new JsonWriter(out) ;
        w.startOutput() ;
        this.visit(w) ;
        w.finishOutput() ;
    }
    
//    public boolean isInteger()      { return false ; }
//    public boolean isDecimal()      { return false ; }
//    public boolean isDouble()       { return false ; }
}

/*
 * (c) Copyright 2008, 2009 Hewlett-Packard Development Company, LP
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
 */