/*
 * *****************************************************************************
 * Copyright (C) 2022 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

import io.github.dsheirer.rrapi.RadioReferenceService;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.Tag;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit testing for RR service
 */
public class RadioReferenceServiceTest
{
    private static final String SDRTRUNK_APPLICATION_KEY = "88969092";
    private static final Logger mLog = LoggerFactory.getLogger(RadioReferenceServiceTest.class);
    private static RadioReferenceService mRadioReferenceService;
    private static long mCounter = 0;

    public RadioReferenceServiceTest()
    {
    }

    /**
     * Constructs an instance of the radio reference service using user-supplied credentials
     * @return instance or null.
     */
    public RadioReferenceService getService()
    {
        if(mRadioReferenceService == null)
        {
            String name = System.getenv("RADIO_REFERENCE_USER");
            String password = System.getenv("RADIO_REFERENCE_PASSWORD");
            mLog.info("Found username [" + name + "] and password [" + password + "]");

            if(name == null || password == null)
            {
                throw new IllegalArgumentException("Radio Reference service user name and password are required for testing");
            }

            AuthorizationInformation auth = new AuthorizationInformation(SDRTRUNK_APPLICATION_KEY, name, password);

            try
            {
                mRadioReferenceService = new RadioReferenceService(auth);
            }
            catch(Exception e)
            {
                mLog.error("Error creating radio reference service", e);
            }
        }

        return mRadioReferenceService;
    }

    @Test
    @RepeatedTest(1000)
    void tagsMap()
    {
        Random random = new Random();
        long iteration = mCounter++;
        RadioReferenceService service = getService();

        if(iteration % 100 == 1)
        {
            mLog.info("Running test - iteration: " + iteration);
        }

        try
        {
            service.clearTagMap();
            Map<Integer, Tag> tagsMap = service.getTagsMap();
        }
        catch(Exception e)
        {
            mLog.error("Error retrieving tags map", e);
        }

        try
        {
            long interval = (long)(random.nextFloat() * 500);
            Thread.sleep(interval);
        }
        catch(InterruptedException ie)
        {
            mLog.info("Got interrupted!");
        }
    }
}
