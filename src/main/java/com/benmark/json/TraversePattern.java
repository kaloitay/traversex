package com.benmark.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Stack;
import java.util.function.BiFunction;

public class TraversePattern {

    public Stack<String> traverseFields;
    public Stack<String> filterOperations;
    public Stack<BiFunction<ObjectNode,JsonNode, JsonNode>> addOperations;

    public TraversePattern(){}

    public TraversePattern(Stack<String> traverseFields, Stack<String> filterOperations, Stack<BiFunction<ObjectNode,JsonNode, JsonNode>> addOperations){
        this.traverseFields = traverseFields;
        this.filterOperations = filterOperations;
        this.addOperations = addOperations;
    }

    public static class TraversePatternBuilder {
        private Stack<String> traverseFields;
        private Stack<String> filterOperations;
        private Stack<BiFunction<ObjectNode,JsonNode, JsonNode>> addOperations;

        public TraversePatternBuilder(){
            this.traverseFields = new Stack<>();
            this.filterOperations = new Stack<>();
            this.addOperations = new Stack<>();
        }

        public TraversePatternBuilder traverseField(String fieldName){
            traverseFields.add(fieldName);
            return this;
        }

        public TraversePatternBuilder filerOperation(String fieldName){
            filterOperations.add(fieldName);
            return this;
        }

        public TraversePatternBuilder addOperation(BiFunction<ObjectNode,JsonNode, JsonNode> func){
            addOperations.add(func);
            return this;
        }

        public TraversePattern build(){
            return new TraversePattern(this.traverseFields,this.filterOperations,this.addOperations);
        }

    }

}
