package com.cgi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.common.reflect.TypeToken;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public abstract class JpaJsonConverter<EntityAttribute> implements AttributeConverter<EntityAttribute, String> {

    // guava
    private final TypeToken<EntityAttribute> type = new TypeToken<EntityAttribute>(getClass()) {};
    private ObjectReader reader;

    /**
     * Jackson's ObjectMapper static
     */
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(
            DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
            DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    @Override
    public String convertToDatabaseColumn(EntityAttribute attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return serialize(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("cannot serialize attribute " + attribute, e);
        }
    }

    protected String serialize(EntityAttribute attribute) throws JsonProcessingException {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    public EntityAttribute convertToEntityAttribute(String dbData) {
        if (dbData == null || StringUtils.isEmpty(dbData)) {
            return valueForEmptyColumn();
        }
        try {
            return deserialize(dbData);
        } catch (IOException e) {
            throw new RuntimeException("cannot deserialize attribute: " + type + " from: " + dbData, e);
        }
    }

    public EntityAttribute valueForEmptyColumn() {
        return null;
    }

    protected EntityAttribute deserialize(String dbData) throws IOException {
        ObjectReader reader = getReader();
        return reader.readValue(dbData);
    }

    private ObjectReader getReader() {
        //constructing reader takes roughly ~20% deserialization time
        if (reader == null) {
            reader = objectMapper.readerFor(objectMapper.constructType(type.getType()));
        }
        return reader;
    }
}