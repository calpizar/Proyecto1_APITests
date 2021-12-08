import helpers.AuthHelper;
import helpers.DataHelper;
import model.Article;
import org.testng.annotations.Test;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArticleTests extends BaseTest{

    String resourcePath = "/v1/article";
    @Test
    public void Test_Create_Article(){

        Article newArticle = new Article(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization", String.format("Bearer %s", AuthHelper.getUserToken()))
                .body(newArticle)
                .when()
                .post(resourcePath)
                .then()
                .body("message", equalTo("Article created"))
                .statusCode(200);
    }

    @Test
    public void Test_Create_Article_RequestSpecifications(){

        Article newArticle = new Article(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(newArticle)
                .when()
                .post(resourcePath)
                .then()
                .spec(ResponseSpecs.defaultSpecs())
                .body("message", equalTo("Article created"))
                .statusCode(200);
    }
}
