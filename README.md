# FlexBean
**Flexible Java Bean**

Unlike other dynamically typed languages, Java is a statically typed language.
Although this is beneficial in a way, somethimes you need flexible containers to carry information in it.
Because it is hard (sometimes impossible) to create a Java bean for all information schema.

For example, you are presenting user an SQL input and sending that SQL directly to database.
That means resultset may have any number of columns with any type. This makes impossible to use regular Java beans to carry informatin. This is where FlexBean comes in. FlexBean provides a flexible class which lets you create any property with any type, any time. Not only that, you can also change type and value of properties in runtime.

Examples:

```java
FlexBean personBean = new FlexBean();

// set some properties, property types automatically inferred.
personBean.set("name", "Ahmet Kaya");
personBean.set("birth", LocalDate.of(1957, 10, 28));
personBean.set("death", LocalDate.of(2000, 11, 16));


Period agePeriod = Period.between(
        personBean.valueOf("birth"),
        personBean.valueOf("death")
);

int age = agePeriod.getYears();

personBean.set("age", age);


// get read what we put in personBean
for (String propertyName: personBean.getPropertyNames()){
    System.out.println(String.format("Property:%s - type:%s - value:%s",
            propertyName,
            personBean.typeOf(propertyName),
            personBean.valueOf(propertyName)
            ));
}
```

Outputs:

    Property:name - type:class java.lang.String - value:Ahmet Kaya
    Property:birth - type:class java.time.LocalDate - value:1957-10-28
    Property:death - type:class java.time.LocalDate - value:2000-11-16
    Property:age - type:class java.lang.Integer - value:43

