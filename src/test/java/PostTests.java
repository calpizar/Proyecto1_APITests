import helpers.AuthHelper;
import helpers.DataHelper;
import model.Post;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


public class PostTests extends BaseTest {

    String resourcePath1 = "/v1/posts";
    String resourcePath2 = "/v1/post/";
    String postId1 = "7963";
    String postId2 = "7962";
    String deletedPost = "7996";
    String invalidPostId = "1234";

    @Test
    public void Test_Create_Post(){

        Post newPost = new Post(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .body(newPost)
                .when()
                .post(resourcePath2)
                .then()
                .body("message", equalTo("Post created"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Create_Post_Invalid_URI(){

        Post newPost = new Post(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .body(newPost)
                .when()
                .post("V1/post/")
                .then()
                .statusCode(404);
    }

    @Test
    public void Test_Get_All_Posts(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .get(resourcePath1)
                .then()
                .body("results", notNullValue())
                .statusCode(200);
    }

    @Test
    public void Test_Get_One_Post(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .get(resourcePath2 + postId1)
                .then()
                .body("data", notNullValue())
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Get_One_Deleted_Post(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .get(resourcePath2 + deletedPost)
                .then()
                .body("message", equalTo("Post not found"))
                .statusCode(404);
    }


    @Test
    public void Test_Update_Post(){

        Post newPost = new Post(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .body(newPost)
                .when()
                .put(resourcePath2 + postId1)
                .then()
                .body("message", equalTo("Post updated"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Update_Invalid_Post(){
        Post newPost = new Post(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .body(newPost)
                .when()
                .delete(resourcePath2 + invalidPostId)
                .then()
                .body("message", equalTo("Post could not be updated"))
                .statusCode(406);
    }

    @Test
    public void Test_Delete_Post(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .delete(resourcePath2 + postId2)
                .then()
                .body("message", equalTo("Post deleted"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Delete_Invalid_Post(){

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .when()
                .delete(resourcePath2 + invalidPostId)
                .then()
                .body("message", equalTo("Post could not be deleted"))
                .statusCode(406);
    }
}
