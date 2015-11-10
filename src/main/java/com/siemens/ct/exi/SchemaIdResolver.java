/*
 * Copyright (c) 2007-2015 Siemens AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package com.siemens.ct.exi;

import com.siemens.ct.exi.exceptions.EXIException;
import com.siemens.ct.exi.grammars.Grammars;

/**
 * The schemaId option may be used to identify the schema information used for
 * processing the EXI body.
 * 
 * <p>
 * When the "schemaId" element in the EXI options document contains the xsi:nil
 * attribute with its value set to true, no schema information is used for
 * processing the EXI body (i.e. a schema-less EXI stream). When the value of
 * the "schemaId" element is empty, no user defined schema information is used
 * for processing the EXI body; however, the built-in XML schema types are
 * available for use in the EXI body. When the schemaId option is absent (i.e.,
 * undefined), no statement is made about the schema information used to encode
 * the EXI body and this information MUST be communicated out of band. The EXI
 * specification does not dictate the syntax or semantics of other values
 * specified in this field. An example schemaId scheme is the use of URI that is
 * apt for globally identifying schema resources on the Web. The parties
 * involved in the exchange are free to agree on the scheme of schemaId field
 * that is appropriate for their use to uniquely identify the schema information
 * </p>
 * 
 * @author Daniel.Peintner.EXT@siemens.com
 * @author Joerg.Heuer@siemens.com
 * 
 * @version 0.9.5-SNAPSHOT
 */

public interface SchemaIdResolver {

	/**
	 * Resolves schemaId to an actual grammar representation.
	 * 
	 * @param schemaId
	 * @return Grammars
	 * @throws EXIException
	 */
	public Grammars resolveSchemaId(String schemaId) throws EXIException;

}
