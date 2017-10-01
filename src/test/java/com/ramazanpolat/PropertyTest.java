package com.ramazanpolat;

import org.junit.Test;

import javax.lang.model.type.NullType;

import static org.junit.Assert.*;

public class PropertyTest {
    @Test
    public void constructorTest(){
        FlexBean flexBean = new FlexBean();

        FlexBean.Property<Integer> stringProperty = flexBean.new Property<Integer>("intProp",5){};

        System.out.println(stringProperty.toString());
    }


    @Test
    public void toStringTest(){
        FlexBean flexBean = new FlexBean();

        FlexBean.Property<String> stringProperty = flexBean.new Property<String>("strProp","ramazan"){};

        System.out.println(stringProperty.toString());
    }

    @Test
    public void nullValueToStringTest(){
        FlexBean flexBean = new FlexBean();

        FlexBean.Property<Integer> stringProperty = flexBean.new Property<Integer>("intProp",null){};

        System.out.println(stringProperty.toString());
        //assertTrue(stringProperty.toString().equals("null"));
    }


    @Test
    public void getTypeTest(){
        assertEquals(String.class, (new FlexBean()).new Property<String>("strProp","strVal"){}.getType());
        assertEquals(Integer.class, (new FlexBean()).new Property<Integer>("intProp",1){}.getType());
        assertEquals(Float.class, (new FlexBean()).new Property<Float>("floatProp",1.0f){}.getType());
        assertEquals(Double.class, (new FlexBean()).new Property<Double>("doubleProp",1.0){}.getType());
        assertEquals(Long.class, (new FlexBean()).new Property<Long>("doubleProp",1L){}.getType());
    }

    @Test
    public void nullValueGetValueTest(){
        FlexBean flexBean = new FlexBean();

        FlexBean.Property<String> stringProperty = flexBean.new Property<String>("strProp",null){};

        assertEquals(null, stringProperty.getValue());
    }

    @Test
    public void nullValueGetTypeTest(){
        FlexBean flexBean = new FlexBean();

        FlexBean.Property<String> stringProperty = flexBean.new Property<String>("strProp","arbitrary string"){};

        System.out.println(stringProperty.getType());



        FlexBean.Property<String> stringNullProperty = flexBean.new Property<String>("strProp",null){};

        System.out.println(stringNullProperty.getType());




        //assertEquals(, stringProperty.getType());
    }



}