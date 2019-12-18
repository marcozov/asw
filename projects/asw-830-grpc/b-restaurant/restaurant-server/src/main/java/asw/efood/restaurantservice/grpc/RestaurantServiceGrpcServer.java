package asw.efood.restaurantservice.grpc;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import asw.efood.restaurantservice.domain.Restaurant;
import asw.efood.restaurantservice.domain.RestaurantService;
import asw.efood.restaurantservice.api.grpc.CreateRestaurantReply;
import asw.efood.restaurantservice.api.grpc.CreateRestaurantRequest;
import asw.efood.restaurantservice.api.grpc.GetAllRestaurantsReply;
import asw.efood.restaurantservice.api.grpc.GetAllRestaurantsRequest;
import asw.efood.restaurantservice.api.grpc.GetRestaurantReply;
import asw.efood.restaurantservice.api.grpc.GetRestaurantRequest;
import asw.efood.restaurantservice.api.grpc.RestaurantServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

@Component
public class RestaurantServiceGrpcServer {
	private static final Logger logger = Logger.getLogger(RestaurantServiceGrpcServer.class.toString());
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Value("${asw.efood.restaurantservice.grpc.port}")
	private int port;
	
	private Server server;
	
	@PostConstruct
	public void start() throws IOException {
		this.server = ServerBuilder.forPort(port)
				.addService(new RestaurantServiceImpl())
				.build()
				.start();
		logger.info("Server started, listening on " + port);
	}
	
	@PreDestroy
	public void stop() {
		if (this.server != null) {
			logger.info("*** shutting down gRPC server since JVM is shutting down");
			this.server.shutdown();
			logger.info("*** server shut down");
		}
	}
	
	private class RestaurantServiceImpl extends RestaurantServiceGrpc.RestaurantServiceImplBase {
		@Override
		public void createRestaurant(CreateRestaurantRequest req, StreamObserver<CreateRestaurantReply> responseObserver) {
			String name = req.getName();
			String location = req.getLocation();
			logger.info("createRestaurant(" + name + ", " + location + ")");
			Restaurant restaurant = restaurantService.createRestaurant(name, location);
			logger.info("createRestaurant(" + name + ", " + location + "): " + restaurant);
			
			CreateRestaurantReply reply = CreateRestaurantReply.newBuilder()
                    .setRestaurantId(restaurant.getId())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
		}
		
		@Override
		public void getRestaurant(GetRestaurantRequest req, StreamObserver<GetRestaurantReply> responseObserver) {
			long restaurantId = req.getRestaurantId();
			logger.info("getRestaurant(" + restaurantId + ")");
			Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
			logger.info("getRestaurant(" + restaurantId + "): " + restaurant);
			
			GetRestaurantReply reply = GetRestaurantReply.newBuilder()
					.setRestaurantId(restaurant.getId())
					.setName(restaurant.getName())
					.setLocation(restaurant.getLocation())
					.build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
		
		@Override
		public void getAllRestaurants(GetAllRestaurantsRequest req, StreamObserver<GetAllRestaurantsReply> responseObserver) {
			logger.info("getAllRestaurants()");
			Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
			logger.info("getAllRestaurants(): " + restaurants);
			
			List<GetRestaurantReply> rr = 
					restaurants.stream() 
						.map(restaurant -> GetRestaurantReply.newBuilder()
	                        .setRestaurantId(restaurant.getId())
	                        .setName(restaurant.getName())
	                        .setLocation(restaurant.getLocation())
	                        .build())
						.collect(Collectors.toList()); 
            GetAllRestaurantsReply reply = GetAllRestaurantsReply.newBuilder()
                        .addAllRestaurants(rr)
                        .build();
            responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}
