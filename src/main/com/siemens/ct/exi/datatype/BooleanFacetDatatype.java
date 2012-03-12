/*
 * Copyright (C) 2007-2011 Siemens AG
 *
 * This program and its interfaces are free software;
 * you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.siemens.ct.exi.datatype;

import java.io.IOException;

import javax.xml.namespace.QName;

import com.siemens.ct.exi.Constants;
import com.siemens.ct.exi.context.DecoderContext;
import com.siemens.ct.exi.context.EncoderContext;
import com.siemens.ct.exi.context.QNameContext;
import com.siemens.ct.exi.datatype.charset.XSDBooleanCharacterSet;
import com.siemens.ct.exi.io.channel.DecoderChannel;
import com.siemens.ct.exi.io.channel.EncoderChannel;
import com.siemens.ct.exi.types.BuiltInType;
import com.siemens.ct.exi.values.BooleanValue;
import com.siemens.ct.exi.values.Value;

/**
 * 
 * @author Daniel.Peintner.EXT@siemens.com
 * @author Joerg.Heuer@siemens.com
 * 
 * @version 0.8
 */

public class BooleanFacetDatatype extends AbstractDatatype {

	private static final long serialVersionUID = 3601744720431415L;

	private int lastValidBooleanID;
	private boolean lastValidBoolean;

	public BooleanFacetDatatype(QName schemaType) {
		super(BuiltInType.BOOLEAN_FACET, schemaType);
		this.rcs = new XSDBooleanCharacterSet();
	}

	protected boolean isValidString(String value) {
		value = value.trim();
		boolean retValue = true;

		if (value.equals(Constants.XSD_BOOLEAN_FALSE)) {
			lastValidBooleanID = 0;
			lastValidBoolean = false;
		} else if (value.equals(Constants.XSD_BOOLEAN_0)) {
			lastValidBooleanID = 1;
			lastValidBoolean = false;
		} else if (value.equals(Constants.XSD_BOOLEAN_TRUE)) {
			lastValidBooleanID = 2;
			lastValidBoolean = true;
		} else if (value.equals(Constants.XSD_BOOLEAN_1)) {
			lastValidBooleanID = 3;
			lastValidBoolean = true;
		} else {
			retValue = false;
		}

		return retValue;
	}

	public boolean isValid(Value value) {
		if (value instanceof BooleanValue) {
			lastValidBoolean = ((BooleanValue) value).toBoolean();
			// TODO not fully correct
			lastValidBooleanID = lastValidBoolean ? 2 : 0;
			return true;
		} else {
			return isValidString(value.toString());
		}
	}

	public void writeValue(EncoderContext encoderContext,
			QNameContext qnContext, EncoderChannel valueChannel)
			throws IOException {
		valueChannel.encodeNBitUnsignedInteger(lastValidBooleanID, 2);
	}

	public Value readValue(DecoderContext decoderContext,
			QNameContext qnContext, DecoderChannel valueChannel)
			throws IOException {
		int booleanID = valueChannel.decodeNBitUnsignedInteger(2);
		return new BooleanValue(booleanID);
	}
}