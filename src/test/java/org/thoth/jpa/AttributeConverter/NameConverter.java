package org.thoth.jpa.AttributeConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Converter
public class NameConverter implements AttributeConverter<String,String> {

    @Override
    public String convertToDatabaseColumn(String x) {
        System.out.printf("###convertToDatabaseColumn#######################################################%n");
        return x;
    }

    @Override
    public String convertToEntityAttribute(String y) {
        System.out.printf("&&&convertToEntityAttribute&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%n");
        System.out.printf("&&&y=%s%n", y);
        String wow = String.format("WoW: %s", (y == null) ? "" : y);
        System.out.printf("&&&wow=%s%n", wow);
        return wow;
    }

}
