package org.sebas.taskmanager.todo;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.json.JsonAssert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskJsonTest {

    @Test
    void shouldCompareJSon() throws JSONException {
        var data = getData();
        var expected = """
                 {
                   "task" : [
                        {
                            "id": 1,
                            "description" : "TEST 1",
                            "isCompleted" : true
                        },
                        {
                            "id": 2,
                            "description" : "TEST 2",
                            "isCompleted" : false
                        }
                   ]
                }
                """;


        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    void shouldCompareJsonPath(){
        var json = """
                 {
                   "task" : [
                        {
                            "id": 1,
                            "description" : "TEST 1",
                            "isCompleted" : true
                        },
                        {
                            "id": 2,
                            "description" : "TEST 2",
                            "isCompleted" : false
                        }
                   ]
                }
                """;

        Integer length = JsonPath.read(json, "$.task.length()");
        var name = JsonPath.read(json, "$.task[1].description");
        assertThat(name).isEqualTo("TEST 2");
        assertThat(length).isEqualTo(2);
    }

    private String getData(){
        return """
                {
                   "task" : [
                        {
                            "id": 1,
                            "description" : "TEST 1",
                            "isCompleted" : true
                        },
                        {
                            "id": 2,
                            "description" : "TEST 2",
                            "isCompleted" : false
                        }
                   ]
                }
                
                """;
    }
}
