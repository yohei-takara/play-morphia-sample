package dto;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class HotelTest {

    public static void main(String[] args) throws Exception{
        createDatastore();
        findDatastore();
        findOneDatastore();
        deleteDatatore();
    }

    private static void findDatastore() {
        System.out.print("Start findAll");
        Query<Hotel> query = getDatasore().find(Hotel.class);
        viewConsole(query);
    }

    private static void findOneDatastore() {
        System.out.print("Start findOne");
        Query<Hotel> query = getDatasore().find(Hotel.class, "name", "Hotel2");
        viewConsole(query);
    }

    private static void viewConsole(Query<Hotel> query) {
        for (Hotel q : query) {
            System.out.println( q.getId() );
            System.out.println( q.getName() );
            System.out.println( q.getStarts() );
            System.out.println( q.getAddress() );
        }
    }

    private static void createDatastore() {

        Hotel hotel = new Hotel();
        hotel.setName("My Hotel");
        hotel.setStarts(4);

        Address address = new Address();
        address.setStreet("123 Some Street");
        address.setCity("Some Sity");
        address.setPostCode("123 4567");
        address.setCountry("Some Country");

        hotel.setAddress(address);

        getDatasore().save(hotel);

        Hotel hotel2 = new Hotel();
        hotel2.setName("Hotel2");
        hotel2.setStarts(4);
        hotel2.setAddress(address);

        getDatasore().save(hotel2);
    }

    private static void deleteDatatore() {
        Query<Hotel> query = getDatasore().find(Hotel.class);
        getDatasore().delete(query);
    }

    private static Datastore getDatasore() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();
        morphia.map(Hotel.class).map(Address.class);

        Datastore datastore = morphia.createDatastore(mongoClient, "hotel_database");
        return datastore;
    }

}