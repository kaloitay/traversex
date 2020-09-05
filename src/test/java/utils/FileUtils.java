package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class FileUtils {

    public <T> T loadFileFromResources(String filePath, Class<T> cls){
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)){
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, cls);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T loadFile(String filePath, Class<T> cls){
        return new FileUtils().loadFileFromResources(filePath,cls);
    }
}
