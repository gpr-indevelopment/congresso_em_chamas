package com.gprindevelopment.cec.api.externalapi.jarbas.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JarbasSuspicionsDeserializer extends JsonDeserializer<JarbasSuspicions> {

    @Override
    public JarbasSuspicions deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JarbasSuspicions jarbasSuspicions = new JarbasSuspicions();
        ObjectNode node = p.getCodec().readTree(p);
        node.fields().forEachRemaining(entry -> jarbasSuspicions.getKeyToSuspicion().get(entry.getKey()).setValue(entry.getValue().asBoolean()));
        return jarbasSuspicions;
    }
}
