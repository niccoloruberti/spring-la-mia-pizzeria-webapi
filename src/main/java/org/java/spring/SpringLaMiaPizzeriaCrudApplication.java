package org.java.spring;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredienteService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
			Ingrediente i1 = new Ingrediente("pomodoro");
			Ingrediente i2 = new Ingrediente("mozzarella");
			Ingrediente i3 = new Ingrediente("olio");
			
			ingredienteService.save(i1);
			ingredienteService.save(i2);
			ingredienteService.save(i3);
			
			Ingrediente[] ingredientiPizza1 = {i1, i2};
			Ingrediente[] ingredientiPizza2 = {i1, i3};
			
			pizzaService.save(new Pizza("Margherita", "pomodoro e mozzarella", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3NFXJq5RkSlTIUyzeI5qdsMGuas67eganCCziHibUvZKPmLTUY5n58YMmO1phDPLY_l8&usqp=CAU", 6D, ingredientiPizza1));
			
			pizzaService.save(new Pizza("Salame Piccante", "pomodoro, mozzarella, salame piccante", "https://www.saporidellamurgia.com/prodotti/0302628/pizza_calabrese.jpg", 8D, ingredientiPizza2));
			
			pizzaService.save(new Pizza("Napoli", "pomodoro, mozzarella, acciughe e capperi", "https://www.donnamoderna.com/content/uploads/2021/08/pizza-napoli-830x625.jpg", 8.50, ingredientiPizza1));
			
			pizzaService.save(new Pizza("Quattro Stagioni", "pomodoro, mozzarella, olive, funghi, carciofi e prosciutto cotto", "https://i0.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2016/07/3017_Pizza.jpg?resize=895%2C616&ssl=1", 9D, ingredientiPizza1));
			
			pizzaService.save(new Pizza("Speck e Mascarpone", "pomodoro, mozzarella, speck e mascarpone" , "https://www.galbani.it/sites/default/files/styles/width_1920/public/speck-e-mascarpone-1_zoom.jpg?itok=ZzwiESCP", 9.50, ingredientiPizza2));
			
			pizzaService.save(new Pizza("Tonno e Cipolla", "pomodoro, mozzarella, tonno e cipolla", "https://blog.giallozafferano.it/cucinoperpassione/wp-content/uploads/2019/09/Pizza-rossa-tonno-e-cipolla.jpg", 6D, ingredientiPizza1));
			
			Role roleUser = new Role("USER");
			Role roleAdmin = new Role("ADMIN");
			
			roleService.save(roleUser);
			roleService.save(roleAdmin);
			
			String pws = AuthConf.passwordEncoder().encode("pws");
			
			User niccoUser = new User("niccoUser", pws, roleUser);
			User niccoAdmin = new User("niccoAdmin", pws, roleAdmin);

			
			userService.save(niccoUser);
			userService.save(niccoAdmin);
	}
	
	


}
