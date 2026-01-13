package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;

//import javax.management.Query;
import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("shailesh"));
//        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//        query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN));
//        query.addCriteria(Criteria.where("userName").nin("Rajat","sahnd"));
//        query.addCriteria(Criteria.where("roles").nin("USER","ADMIN"));
//        Criteria criteria=new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("sentimentAnalysis").is(true),
//                Criteria.where("sentimentAnalysis").is(true)));
return mongoTemplate.find(query, User.class);
//        List<User> users = mongoTemplate.find(query, User.class);
//        return users;
    }
}
