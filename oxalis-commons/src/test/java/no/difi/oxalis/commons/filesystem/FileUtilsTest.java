/*
 * Copyright 2010-2017 Norwegian Agency for Public Management and eGovernment (Difi)
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 */

package no.difi.oxalis.commons.filesystem;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author erlend
 */
public class FileUtilsTest {

    @Test
    public void simpleConstructor() {
        new FileUtils();
    }

    @Test
    public void simpleFilterString() {
        String result = FileUtils.filterString("<1811836472.7.1486495191281.JavaMail.ebe@L-EBE-X260>");
        Assert.assertEquals(result, "_1811836472.7.1486495191281.JavaMail.ebe_L-EBE-X260_");
    }
}
