package com.ramazanpolat;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class FlexBeanText {

    private static FlexBean getFlexBean(){
        FlexBean flexBean = new FlexBean();
        flexBean.set("name", "Ramazan"); // String
        flexBean.set("age", 36);    // Integer
        flexBean.set("dob", LocalDate.of(1980, 1, 1)); // LocalDate
        flexBean.set("null", null); // LocalDate

        return flexBean;
    }

    @Test
    public void getPropertyNamesTest() {

        FlexBean flexBean = getFlexBean();

        for (String propName : flexBean.getPropertyNames()) {
            System.out.println("=== Property Name:" + propName + " ===");
            System.out.println("Property Type:" + flexBean.typeOf(propName));
            System.out.println("Property Value:" + flexBean.valueOf(propName));
        }
    }

    @Test
    public void setNullProperty() {

        FlexBean flexBean = getFlexBean();
        flexBean.set("unsettype");

        for (String propName : flexBean.getPropertyNames()) {
            System.out.println("=== Property Name:" + propName + " ===");
            System.out.println("Property Type:" + flexBean.typeOf(propName));
            System.out.println("Property Value:" + flexBean.valueOf(propName));
        }
    }

    @Test
    public void getTest() {
        FlexBean flexBean = getFlexBean();

        for (String propName : flexBean.getPropertyNames()) {
            FlexBean.Property property = flexBean.get(propName);
            System.out.println("=== Prop Name: " + property.getName() + " ===");
            System.out.println("Type: " + property.getType());
            System.out.println("Value:" + property.getValue());
        }
    }

    @Test
    public void ofMethodsTest() {
        FlexBean flexBean = getFlexBean();

        for (String propName : flexBean.getPropertyNames()) {
            System.out.println("=== Prop Name: " + propName + " ===");
            System.out.println("Type: " + flexBean.typeOf(propName));
            System.out.println("Value:" + flexBean.valueOf(propName));
        }
    }

    @Test
    public void safeValueOfMethodsTest() {
        FlexBean flexBean = getFlexBean();

        for (String propName : flexBean.getPropertyNames()) {
            if (flexBean.hasValue(propName)) {
                System.out.println("=== Prop Name: " + propName + " ===");
                System.out.println("Value:" + flexBean.valueOf(propName, String.class));
            }
        }
    }

    @Test
    public void changePropertyRuntimeTest(){

        FlexBean flexBean = getFlexBean();

        for (String propName : flexBean.getPropertyNames()) {
            FlexBean.Property property = flexBean.get(propName);
            System.out.println("=== Prop Name: " + property.getName() + " ===");
            System.out.println("Type: " + property.getType());
            System.out.println("Value:" + property.getValue());
        }

        System.out.println("--------------------------------------------");

        flexBean.set("name", 21); // String
        flexBean.set("age", "otuzalti");    // Integer
        flexBean.set("null", "deneme"); // LocalDate


        for (String propName : flexBean.getPropertyNames()) {
            FlexBean.Property property = flexBean.get(propName);
            System.out.println("=== Prop Name: " + property.getName() + " ===");
            System.out.println("Type: " + property.getType());
            System.out.println("Value:" + property.getValue());
        }
    }

    @Test
    public void getProperties(){
        FlexBean flexBean = getFlexBean();

        for (FlexBean.Property property: flexBean.getProperties()){
            if (property.isTypeOf(String.class)) {
                property.setValue("this is new value");
            }
            System.out.println("=== Property Name:"+property.getName()+ " ===");
            System.out.println("Type: "+property.getType());
            System.out.println("Has Value: "+property.hasValue());
            System.out.println("Value: "+property.getValue());


        }

    }

    @Test
    public void setValueTest(){
        FlexBean flexBean = getFlexBean();


        FlexBean.Property nameProp = flexBean.get("name");

        System.out.println("=== before ===");

        System.out.println("Property Name:"+nameProp.getName());
        System.out.println("Type: "+nameProp.getType());
        System.out.println("Value: "+nameProp.getValue());


        nameProp.setValue(13);
        System.out.println("=== after ===");
        System.out.println("Property Name:"+nameProp.getName());
        System.out.println("Type: "+nameProp.getType());
        System.out.println("Value: "+nameProp.getValue());

    }

    @Test
    public void setValueWithCheckTest(){
        FlexBean flexBean = getFlexBean();


        FlexBean.Property nameProp = flexBean.get("name");

        System.out.println("=== before ===");

        System.out.println("Property Name:"+nameProp.getName());
        System.out.println("Type: "+nameProp.getType());
        System.out.println("Value: "+nameProp.getValue());


        nameProp.setValue(13,true);
        System.out.println("=== after ===");
        System.out.println("Property Name:"+nameProp.getName());
        System.out.println("Type: "+nameProp.getType());
        System.out.println("Value: "+nameProp.getValue());

    }

    @Test
    public void removeTest(){
        FlexBean flexBean = getFlexBean();

        assertEquals(4, flexBean.getPropertyNames().size());
        assertTrue(flexBean.hasProperty("name"));
        assertTrue(flexBean.hasProperty("age"));
        assertTrue(flexBean.hasProperty("dob"));
        assertTrue(flexBean.hasProperty("null"));


        flexBean.remove("name");

        assertEquals(3, flexBean.getPropertyNames().size());
        assertFalse(flexBean.hasProperty("name"));
        assertTrue(flexBean.hasProperty("age"));
        assertTrue(flexBean.hasProperty("dob"));
        assertTrue(flexBean.hasProperty("null"));

    }
}
