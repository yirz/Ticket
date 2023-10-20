package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);

		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}
	@Test
// S3 : Le ticket n'est pas imprimé si le montant inséré est insufisant
	void dontPrintTicketIfAmountNotEnought(){
		machine.insertMoney(10);

		assertEquals(false,machine.printTicket(),"montant inséré insufisant");
	}

	@Test
		// S4 :  Le ticket est imprimé si le montant inséré est sufisantt
	void printTicketIfAmountEnought(){
		machine.insertMoney(60);

		assertEquals(true,machine.printTicket(),"montant inséré suffisant");
	}

	@Test
		// S5 :  Si un ticket est imprimé, on enlève son prix de la balance
	void ticketBalance(){
		machine.insertMoney(100);
		machine.printTicket();

		assertEquals(100-PRICE,machine.getBalance(),"la balance n'est pas décrémentée");
	}

	@Test
		// S6 :  le montabingnt collecté est mis à jour quand on imprime un ticket (pas avant)
	void MoneyColectUpdate(){
		machine.insertMoney(100);
		assertEquals(0,machine.getTotal(),"le montant collecté est mis à jour avant l'impression du ticket");
		machine.printTicket();
		assertEquals(100,machine.getTotal(),"le montant collecté n'est pas misà jour");

	}
	@Test
		// S7 : rendre correctement la monnaie
	void GiveBackChange(){
		machine.insertMoney(100);
		machine.printTicket();

		assertEquals(100-PRICE,machine.refund(),"la fonction ne rend pas la monnaie");

	}


	@Test
		// S8 :  remet la balance à zéro
	void balance0(){
		machine.insertMoney(80);
		machine.refund();

		assertEquals(0,machine.getBalance(),"la fonction  ne remet pas la balance à zéro");

	}


	@Test
		// S9 :  on ne peut pas insérer un montant négatif
	void InsertnegativeValues(){
		try{
			machine.insertMoney(-80);
			fail("doit lever une exception");
		}
		catch (IllegalArgumentException e){

		}
	}


	@Test
		// S10 :  on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void createMachineTicketNegative(){
		try{
			TicketMachine machine = new TicketMachine(-15);
			fail("doit lever une exception");
		}
		catch (IllegalArgumentException e){

		}
	}



}
