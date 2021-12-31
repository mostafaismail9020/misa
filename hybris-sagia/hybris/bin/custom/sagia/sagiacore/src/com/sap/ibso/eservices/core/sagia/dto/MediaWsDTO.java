package com.sap.ibso.eservices.core.sagia.dto;

import java.io.Serializable;

public class MediaWsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>MediaWsDTO.description</code> property defined at extension <code>misawebservice</code>. */
	
	private String description;

	/** <i>Generated property</i> for <code>MediaWsDTO.url</code> property defined at extension <code>misawebservice</code>. */
		
	private String url;

	/** <i>Generated property</i> for <code>MediaWsDTO.downloadUrl</code> property defined at extension <code>misawebservice</code>. */
		
	private String downloadUrl;

	/** <i>Generated property</i> for <code>MediaWsDTO.fileName</code> property defined at extension <code>misawebservice</code>. */
		
	private String fileName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
	
}
