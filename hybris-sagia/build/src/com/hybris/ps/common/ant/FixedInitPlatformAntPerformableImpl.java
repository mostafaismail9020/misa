/**
 *
 */
package com.hybris.ps.common.ant;

import org.apache.log4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import de.hybris.ant.taskdefs.InitPlatformAntPerformableImpl;


/**
 * Fix for PLA-9860, junit init now has the same behaviour than regular init.
 * @author adrien.missemer
 */
public class FixedInitPlatformAntPerformableImpl extends InitPlatformAntPerformableImpl
{
	private static final Logger LOG = Logger.getLogger(FixedInitPlatformAntPerformableImpl.class.getName());

	public FixedInitPlatformAntPerformableImpl(final String tenantID)
	{
		super(tenantID);
		ReflectionTestUtils.setField(this, "isJunitTenant", Boolean.FALSE);
	}
}
