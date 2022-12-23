package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("user1", "lastName1","user1@gmail.com");
      User user2 = new User("user2", "lastName2", "user2@gmail.com");
      User user3 = new User("user3", "lastName3", "user3@gmail.com");
      User user4 = new User("user4", "lastName4", "user4@gmail.com");

      Car car1 = new Car("Mercedes", 500);
      Car car2 = new Car("BMW", 600);
      Car car3 = new Car("lamborghini", 650);
      Car car4 = new Car("Tesla", 700);



      userService.addUser(user1.setCar(car1).setUser(user1));
      userService.addUser(user2.setCar(car2).setUser(user2));
      userService.addUser(user3.setCar(car3).setUser(user3));
      userService.addUser(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsersOfTable()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }


      System.out.println(userService.getUserByCar("Mercedes", 600));
      System.out.println("2. _____________________________________________");


      try {
         User notFoundUser = userService.getUserByCar("Tesla", 3);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }

      context.close();
   }
}
