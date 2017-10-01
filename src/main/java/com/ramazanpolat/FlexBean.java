package com.ramazanpolat;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("unchecked")
public class FlexBean {

    private final Map<String, Property> props = new LinkedHashMap<>();

    FlexBean() {

    }

    public boolean hasProperty(String propertyName){
        return getPropertyNames().contains(propertyName);
    }

    public Set<String> getPropertyNames(){
        return props.keySet();
    }

    public Collection<Property> getProperties(){
        return props.values();
    }

    public <T> void set(String propertyName, T value) {
        props.put(propertyName, new Property<T>(propertyName, value){});
    }

    public <T> void set(String propertyName) {
        props.put(propertyName, new Property<T>(propertyName){});
    }

    public Property get(String propertyName){
        return props.get(propertyName);
    }

    public <Any> Any valueOf(String propertyName) {
        return (Any) props.get(propertyName).value;
    }

    public Type typeOf(String propertyName){
        return props.get(propertyName).getType();
    }

    public boolean hasValue(String propertyName){
        return get(propertyName).hasValue();
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(String propertyName, Type as) {
        if (props.get(propertyName).isTypeOf(as)) {
            return (T) props.get(propertyName).value;
        }
        else {
            return null;
        }
    }

    public FlexBean.Property remove(String propertyName) {
        return props.remove(propertyName);
    }

    public abstract class Property<T>{
        private final String name;
        private T value;

        public Property(String name, T value) {
            this.name = name;
            this.value = value;
        }

        Property(String name) {
            this(name, null);
        }

        public String getName() {
            return name;
        }

        public T getValue(){
            return value;
        }

        public void setValue(T newValue){
            this.value = newValue;
        }

        public boolean setValue(T newValue, boolean checkType){
            if (checkType && !isTypeOf(newValue.getClass())){
                return false;
            }
            setValue(newValue);
            return true;
        }

        public Type getType(){
            if (hasValue())
                return  value.getClass();
            Type mySuperclass = this.getClass().getGenericSuperclass();
            return ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        }

        public boolean isTypeOf(Type aType){
            return getType().equals(aType);
        }

        public boolean hasValue(){
            return value != null;
        }

        @Override
        public String toString() {
            return String.format("{\n name: '%s',\n type:'%s',\n value:'%s'\n}", getName(), getType(), getValue());
        }

    }
}
