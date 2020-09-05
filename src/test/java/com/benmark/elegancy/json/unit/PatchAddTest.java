package com.benmark.elegancy.json.unit;

import com.benmark.elegancy.json.dto.Youtube;
import com.benmark.json.TraversePattern;
import com.benmark.json.JsonPatch;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FileUtils;

import java.util.Map;

public class PatchAddTest {

    private JsonPatch jsonPatch = new JsonPatch();

    @Test
    public void patchAdd(){
        Youtube youtubeDTO = getYoutubeDTO();

        TraversePattern pattern = new TraversePattern.TraversePatternBuilder()
                .traverseField("items")
                .filerOperation("id")
                .addOperation((resultObjectNode, node) -> {
                    String kind = node.asText();
                    return resultObjectNode.set(kind,node);
                })
                .build();

        Map<String, Youtube> patch = jsonPatch.patch(pattern, youtubeDTO);
        System.out.println(patch);
        Assert.assertEquals(1,1);

    }

    private Youtube getYoutubeDTO(){
        return FileUtils.loadFile("data/youtube.json",Youtube.class);
    }
//    private
}
