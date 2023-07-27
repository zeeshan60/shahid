package com.example.shahid

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GraphQLSupportIntegrationTest(@Autowired val graphQLTest: GraphQLTest) {
    @Test
    fun testHello() {
        val controller = GraphQLSupport()
        assertEquals("Hello World!", controller.hello())
    }

    @Test
    fun `given graphql pole query fetches poles successfully`() {

        println("given graphql pole query fetches poles successfully")
        val poles = graphQLTest.tester.mutate().webTestClient {
        }.build().document(
            "query Hello {\n" +
                    "  hello\n" +
                    "}"
        ).execute().path("").entity(JsonNode::class.java).also {
            println(it.get().toPrettyString())
        }
//        val resourceAsStream =
//            javaClass.classLoader.getResource("poles/polesResponse.json")
//
        Assertions.assertThat(poles).isNotNull
        val actual = poles.get().toPrettyString()
        val expected = ObjectMapper().readValue(
            "{\n" +
                    "  \"hello\" : \"Hello World!\"\n" +
                    "}", JsonNode::class.java
        ).toPrettyString()

        JSONAssert.assertEquals(
            expected,
            actual,
            false
        )
    }
}
