package com.FilmiPass.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommaSeparatedListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = ((TextNode) p.getCodec().readTree(p)).asText();
        return Arrays.asList(value.split(","));
    }
}
