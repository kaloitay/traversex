package com.benmark.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonPatch {

    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> Map<String,T> patch(TraversePattern cp, T object){
        ObjectNode jsonNodes = mapper.convertValue(object,ObjectNode.class);
        ObjectNode traverseJsonResult = patch(jsonNodes,cp);
        return mapper.convertValue(traverseJsonResult, new TypeReference<Map<String, T>>() {});
    }

    private ObjectNode patch(JsonNode jsonNode, TraversePattern pattern){
        ObjectNode objectNodeResult = mapper.createObjectNode();

        if(jsonNode == null){
            return objectNodeResult;
        }

        Stream<JsonNode> traverseStreams = Stream.of();

        for(String fieldName : pattern.traverseFields){
            if(fieldName.toLowerCase().equals("root")){
                traverseStreams = Stream.concat(traverseStreams, StreamSupport.stream(jsonNode.spliterator(),true));
            }else {
                traverseStreams = Stream.concat(traverseStreams, jsonNode.findValues(fieldName).parallelStream());
            }
        }

        traverseStreams
                .flatMap(node -> {
                    return pattern.filterOperations.parallelStream()
                            .flatMap(fieldName -> {
                                return node.findValues(fieldName).parallelStream();
                            });
                })
                .flatMap(arrayNode -> {
                    return StreamSupport.stream(arrayNode.spliterator(),true);
                })
                .forEach(node -> {
                    pattern.addOperations.forEach(addOp ->{
                        addOp.apply(objectNodeResult,node);
                    });
                });

        return objectNodeResult;

    }
}
