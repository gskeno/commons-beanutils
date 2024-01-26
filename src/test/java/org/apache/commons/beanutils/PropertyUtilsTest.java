/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.commons.beanutils;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class PropertyUtilsTest {

    @Test
    public void testGetSetSimpleProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person("A","Z");
        Object firstName = PropertyUtils.getSimpleProperty(person, "firstName");
        Assert.assertTrue(firstName.equals("A"));

        PropertyUtils.setSimpleProperty(person, "firstName", "1");
        firstName = PropertyUtils.getSimpleProperty(person, "firstName");
        Assert.assertTrue(firstName.equals("1"));
    }

    @Test
    public void testGetIndexedProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Paper paper = new Paper();
        paper.addSinger("1");
        paper.addSinger("2");
        paper.setTutors(new String[]{"A","B","C"});
        Object signer = PropertyUtils.getIndexedProperty(paper, "signers", 0);
        System.out.println(signer);
        Object tutor = PropertyUtils.getIndexedProperty(paper, "tutors", 1);
        System.out.println(tutor);
    }
}
