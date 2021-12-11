import helpers.DataHelper;
import model.Comment;
import org.testng.annotations.Test;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CommentsTest extends BaseTest{

    String postId = "8004";
    String resourcePath3 = "/v1/comments";
    String resourcePath4 = "/v1/comment/";
    String invalidPostId = "1234";
    String commentId1 = "4680";
    String commentId2 = "4678";
    String invalidCommentId = "1111";

    @Test
    public void Test_Create_Comment(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + postId)
                .then()
                .body("message", equalTo("Comment created"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Create_Comment_from_Invalid_PostID(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + invalidPostId)
                .then()
                .body("message", equalTo("Comment could not be created"))
                .statusCode(406);
    }

    @Test
    public void Test_Get_All_Comments(){


        given()
                .spec(RequestSpecs.generateBasicAuth())
                .when()
                .post(resourcePath3 + postId)
                .then()
                .body("results", notNullValue())
                .statusCode(406);
    }

    @Test
    public void Test_Get_One_Comment(){

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .when()
                .post(resourcePath4 + postId + commentId1)
                .then()
                .body("data", notNullValue())
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Get_One_Comment_From_Invalid_CommentID(){

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .when()
                .post(resourcePath4 + postId + invalidCommentId)
                .then()
                .body("message", equalTo("Comment not found"))
                .statusCode(404);
    }

    @Test
    public void Test_Update_Comment(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + postId + commentId1)
                .then()
                .body("message", equalTo("Comment updated"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Update_Comment_From_Invalid_PostID(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + invalidPostId + commentId1)
                .then()
                .body("message", equalTo("Comment could not be updated"))
                .statusCode(406);
    }

    @Test
    public void Test_Delete_Comment(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + postId + commentId2)
                .then()
                .body("message", equalTo("Comment deleted"))
                .statusCode(200);
    }

    @Test
    public void Negative_Test_Delete_Comment_From_Invalid_PostID(){

        Comment newComment= new Comment(DataHelper.generateRandomName(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .when()
                .post(resourcePath4 + invalidPostId + commentId2)
                .then()
                .body("message", equalTo("Comment could not be deleted"))
                .statusCode(406);
    }

}
