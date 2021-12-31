package com.casblogaddon.controllers;

import com.casblogaddon.model.components.BlogPostContainerComponentModel;
import com.casblogaddon.model.components.BlogPostSearchContainerComponentModel;


public interface CasblogaddonControllerConstants
{
	interface Actions
	{
		interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";
			String BlogPostContainerComponent = _Prefix + BlogPostContainerComponentModel._TYPECODE + _Suffix;
			String BlogPostSearchContainerComponent = _Prefix + BlogPostSearchContainerComponentModel._TYPECODE + _Suffix;
		}
	}
}
