package com.sap.ibso.eservices.core.sagia.dto;

import java.io.Serializable;

public class CountryWsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** <i>Generated constant</i> - Attribute key of <code>code</code> attribute defined at extension <code>sagiacore</code>. */
	private String code;
	
	/** <i>Generated constant</i> - Attribute key of <code>name</code> attribute defined at extension <code>sagiacore</code>. */
	private String name;
	
	/** <i>Generated constant</i> - Attribute key of <code>prefix</code> attribute defined at extension <code>sagiacore</code>. */
	private String prefix;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
	
}
