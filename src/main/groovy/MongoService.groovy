import com.mongodb.rx.client.MongoCollection
import com.mongodb.rx.client.MongoClient
import com.mongodb.rx.client.MongoClients
import org.bson.Document
import rx.Observable

class MongoService {
  static Observable<Document> find() {
    MongoClient mongoClient = MongoClients.create()
    MongoCollection collection = mongoClient.getDatabase('redsky').getCollection('cartwheel_offers')
    collection.find().toObservable()
  }
}
