/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiasecchat.builder;

import com.sap.ibso.eservices.sagiasecchat.data.Owner;
import com.sap.ibso.eservices.sagiasecchat.data.Transcript;

import java.util.Date;

public class TranscriptBuilder
{
    private String description;
    private Date createdAt;
    private String visibility;
    private Owner owner;

    public static TranscriptBuilder createTranscriptBuilder()
    {
        return new TranscriptBuilder();
    }

    public TranscriptBuilder withDescription(String description)
    {
        this.description = description;
        return this;
    }

    public TranscriptBuilder withCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
        return this;
    }

    public TranscriptBuilder withVisibility(String visibility)
    {
        this.visibility = visibility;
        return this;
    }

    public TranscriptBuilder withOwner(Owner owner)
    {
        this.owner = owner;
        return this;
    }

    public Transcript build()
    {
        Transcript transcript = new Transcript();
        transcript.setDescription(description);
        transcript.setOwner(owner);
        transcript.setCreatedAt(createdAt);
        transcript.setVisibility(visibility);
        return transcript;
    }
}
