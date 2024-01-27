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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyUtilsTest {

    @Test
    public void testIntrospector() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        System.out.println(beanInfo);
    }
    @Test
    public void testGetSetSimpleProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // firstName = A, lastName = Z, ?????
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
        Assert.assertEquals("1", signer);
        Object tutor = PropertyUtils.getIndexedProperty(paper, "tutors", 1);
        Assert.assertEquals("B", tutor);
    }

    @Test
    public void testGetMapIndexProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Employee employee = new Employee();
        Map<String,Object> address = new HashMap<>();
        address.put("home","A");
        address.put("company","B");
        employee.setAddress(address);

        // ??????,????get??
        Object home1 = PropertyUtils.getMappedProperty(employee, "address(home)");
        Object home2 = PropertyUtils.getMappedProperty(employee, "address", "home");
        Assert.assertEquals(home1, home2);
        Assert.assertEquals(home1,"A");

        PropertyUtils.setMappedProperty(employee, "address(home)", "??");
        Assert.assertEquals(PropertyUtils.getMappedProperty(employee, "address(home)"), "??");
        PropertyUtils.setMappedProperty(employee, "address", "home", "??");
        Assert.assertEquals(PropertyUtils.getMappedProperty(employee, "address(home)"), "??");
    }

    @Test
    public void testDynaClass() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        BasicDynaClass basicDynaClass = new BasicDynaClass("dyncClass", null, new DynaProperty[]{
                new DynaProperty("booleanProperty", Boolean.TYPE),
                new DynaProperty("listIndexed", List.class),
                new DynaProperty("stringArray", new String[0].getClass()),
                new DynaProperty("mappedIntProperty", Map.class),
                new DynaProperty("stringProperty", String.class),
        });
        DynaBean dynaBean = basicDynaClass.newInstance();
        Map<Integer,Object> map = new HashMap<>();
        map.put(1,"A");
        dynaBean.set("mappedIntProperty", map);
        Object result = dynaBean.get("mappedIntProperty");
        Assert.assertEquals(map, result);

        PropertyUtils.setProperty(dynaBean, "mappedIntProperty", map);
        Object mappedIntProperty = PropertyUtils.getProperty(dynaBean, "mappedIntProperty");
        Assert.assertEquals(mappedIntProperty, map);
    }
}
